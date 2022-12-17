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

    private static final String elem1 = "{\"type\":\"CONSTANT\", \"coefficient\":3.0, \"variable\":null, \"name\":\"\"}";
    private static final String elem2 = "{\"type\":\"CONSTANT\", \"coefficient\":2.5, \"variable\":null, \"name\":\"\"}";
    private static final String formula = "{\"decimalTreatment\":\"TRUNCATE\", \"operator\":\"ADDITION\", \"elems\":[" + elem1 + ", " + elem2 + "]}";

    @Test
    void build() throws JsonProcessingException {
        Expression expression = expressionBuilder.build(formula);
        Double result = expressionResolver.resolve(expression);
        log.info(String.valueOf(result));

        Assertions.assertEquals(6.0, result);
    }
}
