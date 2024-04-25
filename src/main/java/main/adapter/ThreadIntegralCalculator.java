package main.adapter;

import main.Main;
import main.logic.IntegralCalculator;

import java.util.function.DoubleUnaryOperator;

public class ThreadIntegralCalculator implements Runnable {
    private final IntegralCalculator calculator;
    private final Main main;

    public ThreadIntegralCalculator(double start, double end, int n, DoubleUnaryOperator f, Main main) {
        calculator = new IntegralCalculator(start, end, n, f);
        this.main = main;
    }

    @Override
    public void run() {
        double v = calculator.calculate();
        main.sendResult(v);
    }
}
