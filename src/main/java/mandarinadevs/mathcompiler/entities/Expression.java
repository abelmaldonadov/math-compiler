package mandarinadevs.mathcompiler.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mandarinadevs.mathcompiler.enums.DecimalTreatment;
import mandarinadevs.mathcompiler.enums.Operator;

import java.util.List;

import static mandarinadevs.mathcompiler.enums.DecimalTreatment.DECIMAL;
import static mandarinadevs.mathcompiler.enums.Operator.ADDITION;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expression {
    private DecimalTreatment decimalTreatment = DECIMAL;
    private Operator operator = ADDITION;
    private List<Elem> elems = List.of();

    public Expression (DecimalTreatment decimalTreatment, Operator operator, Elem... elems) {
        this.decimalTreatment = decimalTreatment;
        this.operator = operator;
        this.elems = List.of(elems);
    }
    public Expression (DecimalTreatment decimalTreatment, Elem... elems) {
        this.decimalTreatment = decimalTreatment;
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
