package mandarinadevs.mathcompiler;

import lombok.extern.slf4j.Slf4j;
import mandarinadevs.mathcompiler.entities.Expression;
import mandarinadevs.mathcompiler.exceptions.MathCompilerException;
import mandarinadevs.mathcompiler.services.MathService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static mandarinadevs.mathcompiler.entities.DecimalTreatment.NONE;
import static mandarinadevs.mathcompiler.entities.ExpressionType.NUMBER;
import static mandarinadevs.mathcompiler.entities.ExpressionType.VARIABLE;
import static mandarinadevs.mathcompiler.entities.Operator.*;

@Slf4j
@SpringBootTest
class MathCompilerApplicationTests {
    @Autowired
    private MathService mathService;

    private static final Expression formula = new Expression(
        1, VARIABLE, ADDITION, 1.0, "A", List.of(
            new Expression(1, NUMBER, ADDITION, 10.0, null, null, NONE),
            new Expression(2, VARIABLE, SUBTRACTION, 1.0, "B", List.of(
                    new Expression(1, NUMBER, ADDITION, 5.0, null, null, NONE),
                    new Expression(2, NUMBER, SUBTRACTION, 3.0, null, null, NONE)
            ), NONE)
        ), NONE
    );

    @Test
    void contextLoads() {
        Double result = mathService.resolve(formula);
        log.info(String.valueOf(result));

        Assertions.assertEquals(12.0, result);
    }
}
