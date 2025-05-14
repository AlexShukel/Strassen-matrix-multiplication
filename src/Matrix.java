import java.util.Random;

public class Matrix {
    private final int[][] data;
    private final int n;

    private void randomize() {
        Random rand = new Random();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                data[i][j] = rand.nextInt(101);
            }
        }
    }

    public Matrix(int n) {
        this.data = new int[n][n];
        this.n = n;

        this.randomize();
    }

    public Matrix(int n, int initialValue) {
        this.data = new int[n][n];
        this.n = n;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                data[i][j] = initialValue;
            }
        }
    }

    public int get(int i, int j) {
        return data[i][j];
    }

    public void set(int value, int i, int j) {
        data[i][j] = value;
    }

    public int size() {
        return n;
    }

    public void print() {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.print(data[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}
