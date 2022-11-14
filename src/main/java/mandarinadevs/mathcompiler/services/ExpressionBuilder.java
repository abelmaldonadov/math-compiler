package mandarinadevs.mathcompiler.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import mandarinadevs.mathcompiler.entities.Expression;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ExpressionBuilder {
    private String findVariables (String formula) {
//        formula.indexOf();

        return formula;
    }

    private Expression buildExpression (String formula) throws JsonProcessingException {
        formula = findVariables(formula);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(formula, Expression.class);
    }

    public Expression build (String formula) throws JsonProcessingException {
        return buildExpression(formula);
    }
}
