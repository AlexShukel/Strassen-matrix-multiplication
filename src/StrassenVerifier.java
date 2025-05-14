public class StrassenVerifier {
    /**
     * Verifies that a * b = c
     */
    public static void verify(Matrix A, Matrix B, Matrix C) {
        Matrix expected = Standard.multiply(A, B);
        int n = A.size();

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (C.get(i, j) != expected.get(i, j)) {
                    System.out.println("Got:");
                    C.print();
                    System.out.println("Expected:");
                    expected.print();
                    throw new RuntimeException("Strassen matrix multiplication is not correct.");
                }
            }
        }
    }
}
