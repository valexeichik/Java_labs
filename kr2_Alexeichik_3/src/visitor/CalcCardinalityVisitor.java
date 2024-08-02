package visitor;

import iterator.Iterator;
import mvc.Set;

public class CalcCardinalityVisitor implements Visitor {
    private long count;
    public CalcCardinalityVisitor() { count = 0; }
    @Override
    public void visit(Integer element) { count += element; }
    public long getCount() {
        return count;
    }
}
