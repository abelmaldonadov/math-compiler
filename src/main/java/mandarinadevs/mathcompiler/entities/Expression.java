package mandarinadevs.mathcompiler.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mandarinadevs.mathcompiler.enums.DecimalTreat;
import mandarinadevs.mathcompiler.enums.Operator;

import java.util.List;

import static mandarinadevs.mathcompiler.enums.DecimalTreat.DECIMAL;
import static mandarinadevs.mathcompiler.enums.Operator.ADDITION;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expression {
    private DecimalTreat decimalTreat = DECIMAL;
    private Operator operator = ADDITION;
    private List<Elem> elems = List.of();

    public Expression (DecimalTreat decimalTreat, Operator operator, Elem... elems) {
        this.decimalTreat = decimalTreat;
        this.operator = operator;
        this.elems = List.of(elems);
    }
    public Expression (DecimalTreat decimalTreat, Elem... elems) {
        this.decimalTreat = decimalTreat;
        this.elems = List.of(elems);
    }
    public Expression (Operator operator, Elem... elems) {
        this.operator = operator;
        this.elems = List.of(elems);
    }
    public Expression (Elem... elems) {
        this.elems = List.of(elems);
    }
}
