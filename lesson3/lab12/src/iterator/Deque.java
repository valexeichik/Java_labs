package iterator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Deque<T> {
    List<T> deque;

    public Deque() {
        deque = new ArrayList<>();
    }

    public int size() {
        return deque.size();
    }

    public boolean isEmpty() {
        return deque.isEmpty();
    }

    public void clear() {
        deque.clear();
    }

    public boolean equals(Deque<T> dq) {
        return deque.equals(dq.deque);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        MyIterator<T> it = createIterator();
        while (it.hasNext()) result.append(it.next().toString()).append("\n");
        return result.toString();
    }

    public DefaultListModel<T> getListModel() {
        DefaultListModel<T> model = new DefaultListModel<>();
        model.addAll(deque);
        return model;
    }


    public T front() throws IndexOutOfBoundsException {
        if (deque.isEmpty()) throw new IndexOutOfBoundsException();
        return deque.get(0);
    }

    public T back() throws IndexOutOfBoundsException {
        if (deque.isEmpty()) throw new IndexOutOfBoundsException();
        return deque.get(size() - 1);
    }

    public void pushFront(T value) {
        deque.add(0, value);
    }

    public void pushBack(T value) {
        deque.add(value);
    }

    public void popFront() throws IndexOutOfBoundsException {
        if (deque.isEmpty()) throw new IndexOutOfBoundsException();
        deque.remove(0);
    }

    public void popBack() throws IndexOutOfBoundsException {
        if (deque.isEmpty()) throw new IndexOutOfBoundsException();
        deque.remove(size() - 1);
    }

    public void pushBackAll(Deque<T> dq) {
        MyIterator<T> it = dq.createIterator();
        while (it.hasNext()) {
            pushBack(it.next());
        }
    }

    public void pushFrontAll(Deque<T> dq) {
        MyIterator<T> it = dq.createIterator();
        while (it.hasPrevious()) {
            pushFront(it.previous());
        }
    }


    public MyIterator<T> createIterator() {
        return new MyIterator<>() {
            private int currentIndex = 0;
            private int backIndex = deque.size() - 1;

            public boolean hasNext() {
                //return currentIndex < size() && deque.get(currentIndex) != null;
                return currentIndex < size();
            }

            public boolean hasPrevious() {
                //return backIndex >= 0 && deque.get(backIndex) != null;
                return backIndex >= 0;
            }

            public T next() {
                return deque.get(currentIndex++);
            }

            public T previous() {
                return deque.get(backIndex--);
            }

            public T currentElement() {
                return deque.get(currentIndex);
            }
        };
    }
}