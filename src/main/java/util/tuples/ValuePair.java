package util.tuples;

/**
 * Value based pair.
 * Its equality and its hash code is based on the values held.
 *
 * @param <T>
 */
public class ValuePair<T> implements Pair<T, T> {
    
    private final T left;
    private final T right;

    public ValuePair(T left, T right) {
        this.left = left;
        this.right = right;
    }

    public T getLeft() {
        return left;
    }

    public T getRight() {
        return right;
    }

    @Override public boolean equals(Object obj) {
        if (!(obj instanceof ValuePair)) {
            return false;
        }

        ValuePair pair = (ValuePair) obj;

        return left.equals(pair.getLeft()) && right.equals(pair.getRight());
    }

    @Override public int hashCode() {
        int hash = 1;
        hash = hash * 17 + left.hashCode();
        hash = hash * 13 + right.hashCode();

        return hash;
    }
}
