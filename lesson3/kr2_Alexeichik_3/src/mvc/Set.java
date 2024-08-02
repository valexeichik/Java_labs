package mvc;

import iterator.ArrayListIterator;
import iterator.Iterable;
import iterator.Iterator;
import strategy.CalcCardinalityStrategy;
import strategy.StreamCalcCardinalityStrategy;
import visitor.Visitable;
import visitor.Visitor;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Set implements Iterable<Integer>, Visitable {
    private ArrayList<Integer> binaryList;
    private Integer min;
    private CalcCardinalityStrategy strategy = new StreamCalcCardinalityStrategy();

    public Integer getMin() { return min; }
    public ArrayList<Integer> getBinaryList() { return binaryList; }

    public Set(Integer min) {
        binaryList = new ArrayList<>();
        this.min = min;
    }

    public void setStrategy(CalcCardinalityStrategy strategy) {
        this.strategy = strategy;
    }

    public long cardinality() { return strategy.countCardinality(this);}

    private ArrayList<Integer> translate() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < binaryList.size(); i++) {
            if (binaryList.get(i) == 1) {
                arrayList.add(i + min);
            }
        }
        return arrayList;
    }

    public void add(Integer element) {
        if (element >= binaryList.size() + min) {
            while (binaryList.size() + min != element) {
                binaryList.add(0);
            }
            binaryList.add(1);
        }
        else {
            if (element < min) {
                ArrayList<Integer> addList = new ArrayList<>();
                addList.add(1);
                for (int i = 0; i < min - element - 1; i++) addList.add(0);
                addList.addAll(binaryList);
                binaryList = addList;
                min = element;
            }
            else {
                binaryList.set(element - min, 1);
            }
        }
    }

    public void save(String filename) throws IOException {
        if (isEmpty()) throw new IndexOutOfBoundsException();

        FileWriter writer = new FileWriter(filename);
        writer.write(binaryList.toString());
        writer.write('\n');
        writer.write(toString());
        writer.close();
    }
    public boolean isEmpty() { return binaryList.isEmpty(); }

    @Override
    public String toString() {
        return translate().toString();
    }

    @Override
    public void accept(Visitor visitor) { binaryList.forEach(visitor::visit);}

    public DefaultListModel<Integer> getBinaryListModel() {
        DefaultListModel<Integer> listModel = new DefaultListModel<>();
        Iterator<Integer> it = createIterator();
        while (it.hasNext()) listModel.addElement(it.next());
        return listModel;
    }
    @Override
    public Iterator<Integer> createIterator() { return new ArrayListIterator<>(binaryList); }
}

