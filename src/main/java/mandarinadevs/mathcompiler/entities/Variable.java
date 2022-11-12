package mandarinadevs.mathcompiler.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mandarinadevs.mathcompiler.enums.Operator;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Variable {
    private String name;
    private Operator operator;
    private Expression value;
}
