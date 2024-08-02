package strategy;

import mvc.Set;
import visitor.CalcCardinalityVisitor;

public class VisitorCalcCardinalityStrategy implements CalcCardinalityStrategy {
    @Override
    public long countCardinality(Set set) {
        CalcCardinalityVisitor visitor = new CalcCardinalityVisitor();
        set.accept(visitor);
        return visitor.getCount();
    }
}
