package iterator;

public interface Iterator<T> {
    T currentElement();

    boolean hasNext();

    T next();

    void first();
}
