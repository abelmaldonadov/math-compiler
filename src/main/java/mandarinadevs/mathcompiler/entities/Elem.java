package mandarinadevs.mathcompiler.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mandarinadevs.mathcompiler.enums.ElemType;

import static mandarinadevs.mathcompiler.enums.ElemType.CONSTANT;
import static mandarinadevs.mathcompiler.enums.ElemType.VARIABLE;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Elem {
    private ElemType type = CONSTANT;
    private Double coefficient = 1.0;
    private Expression variable = null;
    private String name = "";

    public Elem (Double coefficient) {
        this.coefficient = coefficient;
    }
    public Elem (String name, Expression variable) {
        this.type = VARIABLE;
        this.name = name;
        this.variable = variable;
    }
}
