package main.logic;

import java.util.function.DoubleUnaryOperator;
import java.util.stream.IntStream;

public class IntegralCalculator {
    private double start;
    private double end;
    private int n;
    private DoubleUnaryOperator f;

    public IntegralCalculator(double start, double end, int n, DoubleUnaryOperator f) {
        this.start = start;
        this.end = end;
        this.n = n;
        this.f = f;
    }

    public double calculate() {
        double h = (end - start) / n;
        return IntStream.range(0, n).mapToDouble(i -> start + i * h).map(x -> f.applyAsDouble(x)).map(x->x*h).sum();
    }
}
