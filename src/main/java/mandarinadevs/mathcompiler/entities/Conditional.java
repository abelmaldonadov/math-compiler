package mandarinadevs.mathcompiler.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mandarinadevs.mathcompiler.enums.Operator;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Conditional {
    private Operator operator;
    private List<Expression> conditionalTerm1;
    private List<Expression> conditionalTerm2;
    private List<Expression> success;
    private List<Expression> failure;
}
