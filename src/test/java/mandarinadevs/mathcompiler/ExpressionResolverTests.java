package mandarinadevs.mathcompiler;

import lombok.extern.slf4j.Slf4j;
import mandarinadevs.mathcompiler.entities.Conditional;
import mandarinadevs.mathcompiler.entities.Elem;
import mandarinadevs.mathcompiler.entities.Expression;
import mandarinadevs.mathcompiler.entities.Term;
import mandarinadevs.mathcompiler.services.ExpressionResolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static mandarinadevs.mathcompiler.enums.DecimalTreatment.*;
import static mandarinadevs.mathcompiler.enums.Operator.*;

@Slf4j
@SpringBootTest
class ExpressionResolverTests {
    @Autowired
    private ExpressionResolver expressionResolver;

    private static final Expression formula = new Expression(
            TRUNCATE,
            new Term(
                    new Elem(MULTIPLICATION, 10.6),
                    new Elem(MULTIPLICATION, 5.0),
                    new Elem(DIVISION, "A", new Expression(
                            new Term(ADDITION, new Elem(2.0)),
                            new Term(ADDITION, new Elem(2.0)),
                            new Term(SUBTRACTION,  new Elem(2.0))
                    ))
            )
    );

    @Test
    void resolve() {
        Double result = expressionResolver.resolve(formula);
        log.info(String.valueOf(result));

        Assertions.assertEquals(26.0, result);
    }

    /**
     * El resultado de la condición reemplaza a la expresión original
     * Por lo tanto se debe evitar colocar las constantes de tratamiento
     * de decimales en la expresión original...
     * Esto permite la utilización de tratamiento de decimales en la expresion booleana
     * y en los posibles resultados del condicional...
     */
    private static final Expression formulaWithConditionals = new Expression(
            new Conditional(
                    EQUAL,
                    new Expression(new Term(new Elem(2.5))),
                    new Expression(new Term(new Elem(2.6))),
                    new Expression(ROUND, new Term(new Elem(8.84445))),
                    new Expression(ROUND, new Term(new Elem(-3.84445)))
            )
    );

    @Test
    void resolveWithConditionals() {
        Double result = expressionResolver.resolve(formulaWithConditionals);
        log.info(String.valueOf(result));

        Assertions.assertEquals(-4, result);
    }

    /**
     * La potencia NO se aplica al signo, solo al valor...
     * Entiendase la potencia como: -(abc)^2
     * La potencia podria ser expresada tambien como una expresion
     * Sin embargo se paso por alto por revasar los limites de la utilidad del algoritmo...
     */
    private static final Expression formulaWithPower = new Expression(
            0.5,
            new Term(
                    2.0,
                    new Elem(MULTIPLICATION, 0.5, 9.0),
                    new Elem(MULTIPLICATION, 4.0),
                    new Elem(DIVISION, "A", new Expression(
                            new Term(ADDITION, 2.0, new Elem(2.0)),
                            new Term(SUBTRACTION, new Elem(2.0))
                    ))
            )
    );

    @Test
    void resolveWithPower() {
        Double result = expressionResolver.resolve(formulaWithPower);
        log.info(String.valueOf(result));

        Assertions.assertEquals(6.0, result);
    }
}
