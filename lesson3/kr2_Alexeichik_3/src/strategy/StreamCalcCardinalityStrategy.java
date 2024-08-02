package strategy;

import mvc.Set;

public class StreamCalcCardinalityStrategy implements CalcCardinalityStrategy {
    @Override
    public long countCardinality(Set set) {
        return set.getBinaryList().stream().filter(a -> a == 1).count();
    }
}
