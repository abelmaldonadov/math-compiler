package mandarinadevs.mathcompiler.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mandarinadevs.mathcompiler.enums.Operator;

import java.util.List;

import static mandarinadevs.mathcompiler.enums.Operator.ADDITION;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Term {
    private Operator operator = ADDITION;
    private Double power = 1.0;
    private List<Elem> elems;

    public Term (Elem... elems) {
        this.elems = List.of(elems);
    }
    public Term (Operator operator, Elem... elems) {
        this.operator = operator;
        this.elems = List.of(elems);
    }
    public Term (Double power, Elem... elems) {
        this.power = power;
        this.elems = List.of(elems);
    }
    public Term (Operator operator, Double power, Elem... elems) {
        this.operator = operator;
        this.power = power;
        this.elems = List.of(elems);
    }
}
