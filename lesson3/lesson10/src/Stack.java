import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class Stack<T> {
    private List<T> stack;

    Stack() {
        stack = new ArrayList<>();
    }

    int size() { return stack.size(); }
    boolean isEmpty() { return stack.isEmpty(); }
    void clear() { stack.clear(); }
    boolean equals(Stack<T> st) { return stack.equals(st.stack); }

    void push(T value) {
        stack.add(value);
    }

    void pop() throws EmptyStackException {
        if (isEmpty()) throw new EmptyStackException();
        stack.remove(size() - 1);
    }

    T top() {
        if (isEmpty()) throw new EmptyStackException();
        return stack.get(size() - 1);
    }

    public String toString() {
        Iterator<T> it = new Iterator<>();
        StringBuilder sb = new StringBuilder("stack: ");
        for (T value : stack) {
            sb.append(value).append(" ");
        }

        return sb.toString();
    }

    class Iterator<T> {
        int index = 0;

        boolean hasNext() {
            return index < size() - 1;
        }

        void next() throws IndexOutOfBoundsException {
            if (hasNext()) {
                index++;
            } else throw new IndexOutOfBoundsException();
        }
    }
}
