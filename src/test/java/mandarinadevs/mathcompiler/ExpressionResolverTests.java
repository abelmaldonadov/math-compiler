package mandarinadevs.mathcompiler;

import lombok.extern.slf4j.Slf4j;
import mandarinadevs.mathcompiler.entities.Elem;
import mandarinadevs.mathcompiler.entities.Expression;
import mandarinadevs.mathcompiler.services.ExpressionResolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static mandarinadevs.mathcompiler.enums.DecimalTreat.*;
import static mandarinadevs.mathcompiler.enums.Operator.*;

@Slf4j
@SpringBootTest
class ExpressionResolverTests {
    @Autowired
    private ExpressionResolver expressionResolver;

    private static final Expression formula = new Expression(
            TRUNCATE,
            ADDITION,
            new Elem(10.6),
            new Elem(5.0),
            new Elem("Something", new Expression(
                    MULTIPLICATION,
                    new Elem(2.0),
                    new Elem(4.0)
            ))
    );

    @Test
    void resolve() {
        Double result = expressionResolver.resolve(formula);
        log.info(String.valueOf(result));

        Assertions.assertEquals(23.0, result);
    }

    /**
     * El resultado de la condición reemplaza a la expresión original
     * Por lo tanto se debe evitar colocar las constantes de tratamiento
     * de decimales en la expresión original...
     * Esto permite la utilización de tratamiento de decimales en la expresion booleana
     * y en los posibles resultados del condicional...
     */
    private static final Expression formulaWithConditionals = new Expression(
            GREATER,
            new Elem(3.0),
            new Elem(4.0),
            new Elem("Something", new Expression(
                    SUBTRACTION,
                    new Elem(3.0),
                    new Elem(1.0)
            )),
            new Elem(1.0)
    );

    @Test
    void resolveWithConditionals() {
        Double result = expressionResolver.resolve(formulaWithConditionals);
        log.info(String.valueOf(result));

        Assertions.assertEquals(1.0, result);
    }

    /**
     * La potencia NO se aplica al signo, solo al valor...
     * Entiendase la potencia como: (a)^2
     * La potencia podria ser expresada tambien como una expresion
     */
    private static final Expression formulaWithPower = new Expression(
            POWER,
            new Elem("Something", new Expression(
                    ROOT,
                    new Elem(9.0),
                    new Elem(2.0)
            )),
            new Elem(2.0)
    );

    @Test
    void resolveWithPower() {
        Double result = expressionResolver.resolve(formulaWithPower);
        log.info(String.valueOf(result));

        Assertions.assertEquals(9.0, result);
    }
}
