package h11.fibs;

public record FibonacciPair(int a, int b) {

    FibonacciPair() {
        this(1, 2);
    }

    public FibonacciPair next() {
        return new FibonacciPair(b, a+b);
    }
}
