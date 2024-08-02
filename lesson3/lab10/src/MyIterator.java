public interface MyIterator<T> {
    boolean hasNext();

    boolean hasPrevious();

    T next();

    T previous();

    T currentElement();
}
