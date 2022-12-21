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
    private String name = "";
    private Expression value = null;

    public Elem (Double coefficient) {
        this.coefficient = coefficient;
    }
    public Elem (String name, Expression value) {
        this.type = VARIABLE;
        this.name = name;
        this.value = value;
    }
}
