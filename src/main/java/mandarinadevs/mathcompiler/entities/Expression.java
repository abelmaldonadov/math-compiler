package mandarinadevs.mathcompiler.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mandarinadevs.mathcompiler.enums.DecimalTreatment;

import java.util.List;

import static mandarinadevs.mathcompiler.enums.DecimalTreatment.DECIMAL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expression {
    private DecimalTreatment decimalTreatment = DECIMAL;
    private Double power = 1.0;
    private Conditional conditional = null;
    private List<Term> terms;

    public Expression (Term... terms) {
        this.terms = List.of(terms);
    }
    public Expression (Conditional conditional) {
        this.conditional = conditional;
    }
    public Expression (DecimalTreatment decimalTreatment, Term... terms) {
        this.decimalTreatment = decimalTreatment;
        this.terms = List.of(terms);
    }
    public Expression (Double power, Term... terms) {
        this.power = power;
        this.terms = List.of(terms);
    }
    public Expression (DecimalTreatment decimalTreatment, Double power, Term... terms) {
        this.decimalTreatment = decimalTreatment;
        this.power = power;
        this.terms = List.of(terms);
    }
}
