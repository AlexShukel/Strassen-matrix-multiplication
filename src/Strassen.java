public class Strassen {
    private static Matrix add(Matrix A, Matrix B) {
        int n = A.size();
        Matrix result = new Matrix(n, 0);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                result.set(A.get(i, j) + B.get(i, j), i, j);
            }
        }
        return result;
    }

    private static Matrix subtract(Matrix A, Matrix B) {
        int n = A.size();
        Matrix result = new Matrix(n, 0);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                result.set(A.get(i, j) - B.get(i, j), i, j);
            }
        }
        return result;
    }

    public static double multiplyBench(int n) {
        Matrix m1 = new Matrix(n);
        Matrix m2 = new Matrix(n);
        long start = System.nanoTime();
        Matrix result = multiply(m1, m2);
        long end = System.nanoTime();
        // Skip verification for faster benchmark execution time
//        StrassenVerifier.verify(m1, m2, result);
        return (end - start) / 1e6f;
    }

    public static Matrix multiply(Matrix A, Matrix B) {
        int n = A.size();

        // base case
        if (n <= 16) {
            return Standard.multiply(A, B);
        }

        Matrix[][] As = split(A);
        Matrix[][] Bs = split(B);

        Matrix M1 = multiply(subtract(As[0][1], As[1][1]), add(Bs[1][0], Bs[1][1]));
        Matrix M2 = multiply(add(As[0][0], As[1][1]), add(Bs[0][0], Bs[1][1]));
        Matrix M3 = multiply(subtract(As[0][0], As[1][0]), add(Bs[0][0], Bs[0][1]));
        Matrix M4 = multiply(add(As[0][0], As[0][1]), Bs[1][1]);
        Matrix M5 = multiply(As[0][0], subtract(Bs[0][1], Bs[1][1]));
        Matrix M6 = multiply(As[1][1], subtract(Bs[1][0], Bs[0][0]));
        Matrix M7 = multiply(add(As[1][0], As[1][1]), Bs[0][0]);

        Matrix C11 = subtract(add(M1, add(M2, M6)), M4);
        Matrix C12 = add(M4, M5);
        Matrix C21 = add(M6, M7);
        Matrix C22 = subtract(add(M2, M5), add(M3, M7));

        return join(C11, C12, C21, C22);
    }

    /**
     * Split matrices into 4 parts
     */
    private static Matrix[][] split(Matrix A) {
        int n = A.size() / 2;
        Matrix[][] parts = new Matrix[2][2];
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                parts[i][j] = new Matrix(n, 0);
            }
        }

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                parts[0][0].set(A.get(i, j), i, j); // left-top
                parts[0][1].set(A.get(i, j + n), i, j); // right-top
                parts[1][0].set(A.get(i + n, j), i, j); // left-bottom
                parts[1][1].set(A.get(i + n, j + n), i, j); // right-bottom
            }
        }

        return parts;
    }

    /**
     * Join split matrices
     */
    private static Matrix join(Matrix C11, Matrix C12, Matrix C21, Matrix C22) {
        int n = C11.size();
        Matrix result = new Matrix(n * 2, 0);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                result.set(C11.get(i, j), i, j);
                result.set(C12.get(i, j), i, j + n);
                result.set(C21.get(i, j), i + n, j);
                result.set(C22.get(i, j), i + n, j + n);
            }
        }
        return result;
    }
}
