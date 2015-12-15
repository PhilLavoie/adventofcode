package util;

public class Pair<T> {
    
    private final T left;
    private final T right;

    public Pair(T left, T right) {
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
        if (!(obj instanceof Pair)) {
            return false;
        }

        Pair pair = (Pair) obj;

        return left.equals(pair.getLeft()) && right.equals(pair.getRight());
    }
}
