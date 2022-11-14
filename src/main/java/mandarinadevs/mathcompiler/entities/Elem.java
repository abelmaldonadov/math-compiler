package mandarinadevs.mathcompiler.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mandarinadevs.mathcompiler.enums.ElemType;
import mandarinadevs.mathcompiler.enums.Operator;

import static mandarinadevs.mathcompiler.enums.ElemType.CONSTANT;
import static mandarinadevs.mathcompiler.enums.ElemType.VARIABLE;
import static mandarinadevs.mathcompiler.enums.Operator.MULTIPLICATION;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Elem {
    private ElemType type = CONSTANT;
    private Double power = 1.0;
    private String name = null;
    private Operator operator = MULTIPLICATION;
    private Double coefficient = 1.0;
    private Expression variable = null;

    public Elem (Double coefficient) {
        this.coefficient = coefficient;
    }
    public Elem (Operator operator, Double coefficient) {
        this.operator = operator;
        this.coefficient = coefficient;
    }
    public Elem (String name, Expression variable) {
        this.type = VARIABLE;
        this.name = name;
        this.variable = variable;
    }
    public Elem (Operator operator, String name, Expression variable) {
        this.type = VARIABLE;
        this.operator = operator;
        this.name = name;
        this.variable = variable;
    }
    public Elem (Double power, Double coefficient) {
        this.power = power;
        this.coefficient = coefficient;
    }
    public Elem (Operator operator, Double power, Double coefficient) {
        this.operator = operator;
        this.power = power;
        this.coefficient = coefficient;
    }
    public Elem (Double power, String name, Expression variable) {
        this.type = VARIABLE;
        this.power = power;
        this.name = name;
        this.variable = variable;
    }
    public Elem (Operator operator, Double power, String name, Expression variable) {
        this.type = VARIABLE;
        this.power = power;
        this.operator = operator;
        this.name = name;
        this.variable = variable;
    }
}
