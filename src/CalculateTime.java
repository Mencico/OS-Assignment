import java.util.Arrays;
import java.util.Random;

public class CalculateTime {
    static double[] values = new double[80000000];
    static double[] portionSum = new double[4];
    static int portionSize = values.length / 4;

    public static void main(String[] args) throws InterruptedException {
        addRandoms();

        long start = System.currentTimeMillis();
        double sum = 0;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
        }
        long end = System.currentTimeMillis();

        sum = Math.round(sum * 10.0) / 10.0;
        System.out.println("Serial sum: " + sum);
        System.out.println("Serial time: " + (end - start) + " ms");

        // Multi-threaded speedup
        Thread[] threads = new Thread[portionSum.length];
        start = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            final int j = i;
            threads[i] = new Thread(() -> calculateSum(j));
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

        // Adding up each portion's value together
        double bigSum = 0;
        for (int i = 0; i < portionSum.length; i++) {
            bigSum += portionSum[i];
        }
        end = System.currentTimeMillis();

        bigSum = Math.round(bigSum * 10.0) / 10.0;
        System.out.println("Parallel sum: " + bigSum);
        System.out.println("Parallel time: " + (end - start) + " ms");
    }

    // Calculate every portion's sum
    static void calculateSum(int portion) {
        double sum = 0;
        for (int i = portion * portionSize; i < portion * portionSize + portionSize; i++) {
            sum += values[i];
        }
        portionSum[portion] = sum;
    }

    // Generating a random value and multiply it with a random number between 0.1 ~ 0.9
    static void addRandoms() {
        Random rand = new Random();
        for (int i = 0; i < values.length; i++) {
            double randomD = getRandDouble();
            values[i] = Math.round(rand.nextInt(10) * randomD * 10.0) / 10.0;
        }
    }

    static double getRandDouble() {
        Random random = new Random();
        return Math.round(random.nextDouble() * 0.8 + 0.1 * 10.0) / 10.0;
    }
    }

