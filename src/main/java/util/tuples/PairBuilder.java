package util.tuples;

public interface PairBuilder<L, R> {
    Pair<L, R> build(L left, R right);
}
