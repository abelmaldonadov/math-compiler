package mandarinadevs.mathcompiler.services;

import mandarinadevs.mathcompiler.entities.Conditional;
import mandarinadevs.mathcompiler.entities.Expression;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static mandarinadevs.mathcompiler.enums.ExpressionType.VARIABLE;
import static mandarinadevs.mathcompiler.enums.Operator.SUBTRACTION;

@Service
public class MathService {
    public Expression evaluateConditional(Expression expression) {
        Conditional conditional = expression.getConditional();
        if (conditional == null) {
            return expression;
        }

        Double term_1 =  evaluateExpression(conditional.getConditionalTerm1());
        Double term_2 =  evaluateExpression(conditional.getConditionalTerm2());
        Expression success = conditional.getSuccess();
        Expression failure = conditional.getFailure();

        switch (conditional.getOperator()) {
            case LESS:
                return term_1 < term_2 ? success : failure;
            case GREATER:
                return term_1 > term_2 ? success : failure;
            case LESS_OR_EQUAL:
                return term_1 <= term_2 ? success : failure;
            case GREATER_OR_EQUAL:
                return term_1 >= term_2 ? success : failure;
            case EQUAL:
                return Objects.equals(term_1, term_2) ? success : failure;
            case NOT_EQUAL:
                return !Objects.equals(term_1, term_2) ? success : failure;
            default:
                return failure;
        }
    }

    public Double evaluateExpression(Expression expression) {
        expression = evaluateConditional(expression);
        Double operator = expression.getOperator() == SUBTRACTION ? -1.0 : 1.0;
        Double coefficient = expression.getCoefficient();
        Double variableValue = expression.getType() == VARIABLE
                ? expression
                    .getExpressions()
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
