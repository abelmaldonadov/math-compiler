package mandarinadevs.mathcompiler.services;

import mandarinadevs.mathcompiler.entities.Expression;
import org.springframework.stereotype.Service;

import static mandarinadevs.mathcompiler.enums.ExpressionType.VARIABLE;
import static mandarinadevs.mathcompiler.enums.Operator.SUBTRACTION;

@Service
public class MathService {

    public Double evaluateExpression(Expression expression) {
        Double operator = expression.getOperator() == SUBTRACTION ? -1.0 : 1.0;
        Double coefficient = expression.getCoefficient();
        Double variableValue = expression.getType() == VARIABLE
                ? expression
                    .getChildExpressions()
                    .stream()
                    .reduce(0.0, (acc, cur) -> acc + evaluateExpression(cur), Double::sum)
                : 1.0;

        switch (expression.getDecimalTreatment()) {
            case ROUND:
                return (double) Math.round(operator * coefficient * variableValue);
            case TRUNCATE:
                return (double) (int) (operator * coefficient * variableValue);
            default:
                return operator * coefficient * variableValue;
        }
    }

    public Double resolve(Expression formula) {
        return evaluateExpression(formula);
    }
}
