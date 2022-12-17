package mandarinadevs.mathcompiler.services;

import lombok.extern.slf4j.Slf4j;
import mandarinadevs.mathcompiler.entities.Elem;
import mandarinadevs.mathcompiler.entities.Expression;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static mandarinadevs.mathcompiler.enums.ElemType.VARIABLE;
import static mandarinadevs.mathcompiler.utils.MathUtil.truncate;

@Slf4j
@Service
public class ExpressionResolver {
    private Expression evaluateConditional(Expression expression) {
        if (expression.getElems().size() != 4) {
            return expression;
        }

        Double ce1 =  resolveElem(expression.getElems().get(0));
        Double ce2 =  resolveElem(expression.getElems().get(1));
        Expression success = new Expression(expression.getElems().get(2));
        Expression failure = new Expression(expression.getElems().get(3));

        switch (expression.getOperator()) {
            case LESS:
                return ce1 < ce2 ? success : failure;
            case GREATER:
                return ce1 > ce2 ? success : failure;
            case LESS_OR_EQUAL:
                return ce1 <= ce2 ? success : failure;
            case GREATER_OR_EQUAL:
                return ce1 >= ce2 ? success : failure;
            case EQUAL:
                return Objects.equals(ce1, ce2) ? success : failure;
            case NOT_EQUAL:
                return !Objects.equals(ce1, ce2) ? success : failure;
            default:
                return expression;
        }
    }

    private Double resolveElem(Elem elem) {
        return elem.getType() == VARIABLE
                ? resolveExpression(elem.getVariable())
                : elem.getCoefficient();
    }

    private Double resolveExpression(Expression expression) {
        expression = this.evaluateConditional(expression);
        double result;

        switch (expression.getOperator()) {
            case ADDITION:
                 result = expression.getElems().stream()
                        .map(this::resolveElem)
                        .reduce(0.0, Double::sum);
                break;
            case SUBTRACTION:
                Double s1 = this.resolveElem(expression.getElems().get(0));
                Double s2 = this.resolveElem(expression.getElems().get(1));
                result = s1 - s2;
                break;
            case MULTIPLICATION:
                result = expression.getElems().stream()
                        .map(this::resolveElem)
                        .reduce(1.0, (acc, cur) -> acc * cur);
                break;
            case DIVISION:
                Double d1 = this.resolveElem(expression.getElems().get(0));
                Double d2 = this.resolveElem(expression.getElems().get(1));
                result = d1 / d2;
                break;
            case POWER:
                double p1 = this.resolveElem(expression.getElems().get(0));
                double p2 = this.resolveElem(expression.getElems().get(1));
                result = Math.pow(p1, p2);
                break;
            case ROOT:
                Double r1 = this.resolveElem(expression.getElems().get(0));
                Double r2 = this.resolveElem(expression.getElems().get(1));
                result = Math.pow(r1, 1.0 / r2);
                break;
            default:
                result = 0.0;
        }

        switch (expression.getDecimalTreatment()) {
            case ROUND:
                return (double) Math.round(result);
            case TRUNCATE:
                return truncate(result);
            default:
                return result;
        }
    }

    public Double resolve(Expression expression) {
        return resolveExpression(expression);
    }
}
