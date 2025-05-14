public class Main {
    private static final int MAX_N = 1024;
    private static final int RUNS = 10;

    public static void main(String[] args) {
        System.out.println("Standard matrix multiplication:");

        for (int i = 2; i <= MAX_N; i *= 2) {
            long sum = 0;
            for (int j = 0; j < RUNS; ++j) {
                sum += Standard.multiplyBench(i);
            }
            long duration = sum / RUNS;
            System.out.println("n = " + i + ": duration " + duration / 1e6f + " ms.");
        }

        System.out.println("Strassen matrix multiplication:");

        for (int i = 2; i <= MAX_N; i *= 2) {
            long sum = 0;
            for (int j = 0; j < RUNS; ++j) {
                sum += Strassen.multiplyBench(i);
            }
            long duration = sum / RUNS;
            System.out.println("n = " + i + ": duration " + duration / 1e6f + " ms.");
        }
    }
}