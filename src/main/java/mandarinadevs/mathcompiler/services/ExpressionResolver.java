package mandarinadevs.mathcompiler.services;

import lombok.extern.slf4j.Slf4j;
import mandarinadevs.mathcompiler.entities.Conditional;
import mandarinadevs.mathcompiler.entities.Elem;
import mandarinadevs.mathcompiler.entities.Expression;
import mandarinadevs.mathcompiler.entities.Term;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static mandarinadevs.mathcompiler.enums.ElemType.VARIABLE;
import static mandarinadevs.mathcompiler.enums.Operator.*;
import static mandarinadevs.mathcompiler.utils.MathUtil.truncate;

@Slf4j
@Service
public class ExpressionResolver {
    private Expression evaluateConditional(Expression expression) {
        Conditional conditional = expression.getConditional();
        if (conditional == null) {
            return expression;
        }

        Double ce1 =  resolveExpression(conditional.getConditionalExpression1());
        Double ce2 =  resolveExpression(conditional.getConditionalExpression2());
        Expression success = conditional.getSuccess();
        Expression failure = conditional.getFailure();

        switch (conditional.getOperator()) {
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
                return failure;
        }
    }

    private Double resolveElem(Elem elem) {
        double power = elem.getOperator() == DIVISION ? -1.0 : 1.0;
        Double value = elem.getType() == VARIABLE
                ? resolveExpression(elem.getVariable())
                : elem.getCoefficient();

        value = Math.pow(value, elem.getPower());

        return Math.pow(value, power);
    }

    private Double resolveTerm(Term term) {
        double operator = term.getOperator() == SUBTRACTION ? -1.0 : 1.0;
        double value = term.getElems()
                .stream()
                .map(this::resolveElem)
                .reduce(1.0, (acc, cur) -> acc * cur);

        value = Math.pow(value, term.getPower());

        return operator * value;
    }

    private Double resolveExpression(Expression expression) {
        expression = evaluateConditional(expression);
        double result = expression.getTerms()
                .stream()
                .map(this::resolveTerm)
                .reduce(0.0, Double::sum);

        result = Math.pow(result, expression.getPower());

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
