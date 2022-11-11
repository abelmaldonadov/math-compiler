package mandarinadevs.mathcompiler;

import lombok.extern.slf4j.Slf4j;
import mandarinadevs.mathcompiler.entities.Expression;
import mandarinadevs.mathcompiler.services.MathService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static mandarinadevs.mathcompiler.enums.DecimalTreatment.*;
import static mandarinadevs.mathcompiler.enums.ExpressionType.NUMBER;
import static mandarinadevs.mathcompiler.enums.ExpressionType.VARIABLE;
import static mandarinadevs.mathcompiler.enums.Operator.*;

@Slf4j
@SpringBootTest
class MathCompilerApplicationTests {
    @Autowired
    private MathService mathService;

    private static final Expression formula = new Expression(
        1, VARIABLE, ADDITION, 1.0, "A", List.of(
            new Expression(1, NUMBER, ADDITION, 10.6, null, null, DECIMAL, null),
            new Expression(2, VARIABLE, SUBTRACTION, 1.0, "B", List.of(
                    new Expression(1, NUMBER, ADDITION, 5.0, null, null, DECIMAL, null),
                    new Expression(2, NUMBER, SUBTRACTION, 3.0, null, null, DECIMAL, null)
            ), DECIMAL, null)
        ), TRUNCATE, null
    );

    @Test
    void contextLoads() {
        Double result = mathService.resolve(formula);
        log.info(String.valueOf(result));

        Assertions.assertEquals(8.0, result);
    }
}
