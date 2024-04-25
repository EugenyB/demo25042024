package main;

import main.adapter.ThreadIntegralCalculator;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private double totalResult;
    private int finished;

    private void run() {
        int k = 0;
        double start = 0;
        double end = Math.PI;
        int n = 1000_000_000;
        //IntegralCalculator calculator = new IntegralCalculator(start, end, n, Math::sin);

        long startTime = System.currentTimeMillis();
        //double v = calculator.calculate();

        totalResult = 0;
        int nThreads = Runtime.getRuntime().availableProcessors();
//        int nThreads = 20;
        double delta = (end - start) / nThreads;
        // start threads
        for (int i = 0; i < nThreads; i++) {
            ThreadIntegralCalculator calculator = new ThreadIntegralCalculator(start + i * delta, start + i * delta + delta, n / nThreads, Math::sin, this);
            new Thread(calculator).start();
        }
        // wait until all finished
        try {
            synchronized (this) {
                while (finished < nThreads) {
                    wait();
                    k++;
                }
            }
        } catch (InterruptedException ignored) {

        }

        long finishTime = System.currentTimeMillis();
        System.out.println("v = " + totalResult);
        System.out.println("time = " + (finishTime - startTime));
        System.out.println("nThreads = " + nThreads);
        System.out.println("k = " + k);
    }

    public synchronized void sendResult(double v) {
        totalResult += v;
        finished++;
        notify();
    }
}
