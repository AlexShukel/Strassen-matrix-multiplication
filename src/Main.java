import java.util.Scanner;

public class Main {
    private static final int MAX_N = 2048;
    private static final int RUNS = 10;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("First argument \"mode\" expected. Choose one of \"benchmark\" or \"manual\".");
            return;
        }

        String mode = args[0];

        if (mode.equals("benchmark")) {
            System.out.println("Standard matrix multiplication:");

            for (int i = 2; i <= MAX_N; i *= 2) {
                double sum = 0;
                for (int j = 0; j < RUNS; ++j) {
                    sum += Standard.multiplyBench(i);
                }
                double duration = sum / RUNS;
                System.out.println("n = " + i + ": duration " + duration + " ms.");
            }

            System.out.println("Strassen matrix multiplication:");

            for (int i = 2; i <= MAX_N; i *= 2) {
                double sum = 0;
                for (int j = 0; j < RUNS; ++j) {
                    sum += Strassen.multiplyBench(i);
                }
                double duration = sum / RUNS;
                System.out.println("n = " + i + ": duration " + duration + " ms.");
            }
        } else if (mode.equals("manual")) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter matrix size n (must be a power of 2): ");
            int n = scanner.nextInt();

            if (!isPowerOfTwo(n)) {
                System.err.println("The size n must be a power of 2.");
                return;
            }

            System.out.println("Enter elements of matrix A (row by row):");
            Matrix A = new Matrix(n);
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    A.set(scanner.nextInt(), i, j);
                }
            }

            System.out.println("Enter elements of matrix B (row by row):");
            Matrix B = new Matrix(n);
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    B.set(scanner.nextInt(), i, j);
                }
            }

            System.out.println("Result of Standard multiplication:");
            Matrix resultStandard = Standard.multiply(A, B);
            resultStandard.print();

            System.out.println("Result of Strassen multiplication:");
            Matrix resultStrassen = Strassen.multiply(A, B);
            resultStrassen.print();
        } else {
            System.err.println("Invalid mode provided.");
        }
    }

    public static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}