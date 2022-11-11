package mandarinadevs.mathcompiler;

import lombok.extern.slf4j.Slf4j;
import mandarinadevs.mathcompiler.entities.Conditional;
import mandarinadevs.mathcompiler.entities.Expression;
import mandarinadevs.mathcompiler.services.MathService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static mandarinadevs.mathcompiler.enums.DecimalTreatment.*;
import static mandarinadevs.mathcompiler.enums.ExpressionType.*;
import static mandarinadevs.mathcompiler.enums.Operator.*;

@Slf4j
@SpringBootTest
class MathCompilerApplicationTests {
    @Autowired
    private MathService mathService;

    private static final Expression formula = new Expression(
        VARIABLE, ADDITION, false, 1.0, "A", List.of(
            new Expression(CONSTANT, ADDITION, false, 10.6, null, null, DECIMAL, null),
            new Expression(VARIABLE, SUBTRACTION, false, 1.0, "B", List.of(
                    new Expression(CONSTANT, ADDITION, false, 5.0, null, null, DECIMAL, null),
                    new Expression(CONSTANT, SUBTRACTION, false, 3.0, null, null, DECIMAL, null)
            ), DECIMAL, null)
        ), TRUNCATE, null
    );

    @Test
    void resolve() {
        Double result = mathService.resolve(formula);
        log.info(String.valueOf(result));

        Assertions.assertEquals(8.0, result);
    }

    private static final Expression formulaWithConditionals = new Expression(
        VARIABLE, ADDITION, false, 1.0, "A", List.of(
            new Expression(CONSTANT, ADDITION, false, 1.0, null, null, DECIMAL, new Conditional(
                    LESS_OR_EQUAL,
                    new Expression(CONSTANT, ADDITION, false, 2.45, null, null, DECIMAL, null),
                    new Expression(CONSTANT, ADDITION, false, 2.45, null, null, DECIMAL, null),
                    new Expression(VARIABLE, ADDITION, false, 1.0, "C", List.of(
                            new Expression(CONSTANT, ADDITION, false, 5.0, null, null, DECIMAL, null),
                            new Expression(CONSTANT, ADDITION, false, 3.0, null, null, DECIMAL, null)
                    ), DECIMAL, null),
                    new Expression(VARIABLE, ADDITION, false, 1.0, "D", List.of(
                            new Expression(CONSTANT, ADDITION, false, 5.0, null, null, DECIMAL, null),
                            new Expression(CONSTANT, SUBTRACTION, false, 3.0, null, null, DECIMAL, null)
                    ), DECIMAL, null)
            )),
            new Expression(VARIABLE, ADDITION, false, 2.0, "B", List.of(
                    new Expression(CONSTANT, ADDITION, false, 5.0, null, null, DECIMAL, null),
                    new Expression(CONSTANT, SUBTRACTION, false, 3.0, null, null, DECIMAL, null)
            ), DECIMAL, null)
        ), TRUNCATE, null
    );

    @Test
    void resolveWithConditionals() {
        Double result = mathService.resolve(formulaWithConditionals);
        log.info(String.valueOf(result));

        Assertions.assertEquals(12.0, result);
    }
}
