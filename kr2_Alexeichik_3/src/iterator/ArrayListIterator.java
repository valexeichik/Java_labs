package iterator;

import java.util.ArrayList;

public class ArrayListIterator<T> implements Iterator<T> {
    ArrayList<T> arrayList;
    private int currentIndex = 0;

    public ArrayListIterator(ArrayList<T> arrayList) { this.arrayList = arrayList; }
    @Override
    public T currentElement() { return arrayList.get(currentIndex); }
    @Override
    public T next() { return arrayList.get(currentIndex++); }
    @Override
    public boolean hasNext() {  return currentIndex < arrayList.size(); }
    @Override
    public void first() { currentIndex = 0; }
}
