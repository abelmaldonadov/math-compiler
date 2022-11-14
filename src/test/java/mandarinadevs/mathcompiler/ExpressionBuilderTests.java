package mandarinadevs.mathcompiler;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import mandarinadevs.mathcompiler.entities.Expression;
import mandarinadevs.mathcompiler.services.ExpressionBuilder;
import mandarinadevs.mathcompiler.services.ExpressionResolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class ExpressionBuilderTests {
    @Autowired
    private ExpressionBuilder expressionBuilder;
    @Autowired
    private ExpressionResolver expressionResolver;

    private static final String elem = "{\"type\":\"CONSTANT\", \"power\":1.0, \"name\":null, \"operator\":\"MULTIPLICATION\", \"coefficient\":2.0, \"variable\":null}";
    private static final String term = "{\"operator\":\"ADDITION\", \"power\":2.0, \"elems\":[" + elem + "]}";
    private static final String formula = "{\"decimalTreatment\":\"TRUNCATE\", \"power\":2.0, \"conditional\":null, \"terms\":[" + term + "]}";

    @Test
    void build() throws JsonProcessingException {
        Expression expression = expressionBuilder.build(formula);
        Double result = expressionResolver.resolve(expression);
        log.info(String.valueOf(result));

        Assertions.assertEquals(16.0, result);
    }
}
