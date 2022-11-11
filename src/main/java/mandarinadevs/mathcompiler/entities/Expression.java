package mandarinadevs.mathcompiler.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mandarinadevs.mathcompiler.enums.DecimalTreatment;
import mandarinadevs.mathcompiler.enums.ExpressionType;
import mandarinadevs.mathcompiler.enums.Operator;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expression {
    private ExpressionType type;
    private Operator operator;
    private Boolean isInverse;
    private Double coefficient;
    private String variable;
    private List<Expression> expressions;
    private DecimalTreatment decimalTreatment;
    private Conditional conditional;
}
