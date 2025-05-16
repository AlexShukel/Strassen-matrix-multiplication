public class Standard {
    public static double multiplyBench(int n) {
        Matrix m1 = new Matrix(n);
        Matrix m2 = new Matrix(n);
        long start = System.nanoTime();
        multiply(m1, m2);
        long end = System.nanoTime();
        return (end - start) / 1e6f;
    }

    public static Matrix multiply(Matrix m1, Matrix m2) {
        int n = m1.size();
        Matrix result = new Matrix(n, 0);

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < n; ++k) {
                    result.set(result.get(i, j) + m1.get(i, k) * m2.get(k, j), i, j);
                }
            }
        }

        return result;
    }
}
