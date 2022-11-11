package mandarinadevs.mathcompiler.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mandarinadevs.mathcompiler.enums.Operator;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Conditional {
    private Operator operator;
    private Expression conditionalTerm1;
    private Expression conditionalTerm2;
    private Expression success;
    private Expression failure;
}
