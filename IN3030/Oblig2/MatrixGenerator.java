import java.io.PrintWriter;
import java.util.Random;



class MatrixGenerator {

	/**
	 * The modes of operation your program should execute and measure.
	 *
	 */
	public enum Mode {
		SEQ_NOT_TRANSPOSED,
		SEQ_A_TRANSPOSED,
		SEQ_B_TRANSPOSED,
		PARA_NOT_TRANSPOSED,
		PARA_A_TRANSPOSED,
		PARA_B_TRANSPOSED
	}

	/**
	 * This method will generate the A matrix you are supposed to multiply with B.
	 *
	 * @param seed The RNG seed
	 * @param n    Size of matrix (NxN)
	 * @return Matrix A
	 */
	public static double[][] generateMatrixA(int seed, int n) {
		return generateMatrix(seed, n);
	}

	/**
	 * This method will generate the B matrix you are supposed to multiply with A.
	 *
	 * @param seed The RNG seed
	 * @param n    Size of matrix (NxN)
	 * @return Matrix B
	 */
	public static double[][] generateMatrixB(int seed, int n) {
		return generateMatrix(seed + 1, n);
	}

	/**
	 * For internal use, actually generating the matrix
	 */
	private static double[][] generateMatrix(int seed, int n) {
		double[][] m = new double[n][n];

		Random rnd = new Random(seed);

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				m[i][j] = rnd.nextDouble();

		return m;
	}

	/**
	 * Method for saving your result to a file.
	 *
	 * Modes are:
	 * SEQ_NOT_TRANSPOSED
	 * SEQ_A_TRANSPOSED
	 * SEQ_B_TRANSPOSED
	 * PARA_NOT_TRANSPOSED
	 * PARA_A_TRANSPOSED
	 * PARA_B_TRANSPOSED
	 *
	 *
	 * @param seed The seed used in generateMatrix
	 * @param mode Which mode is this result from?
	 * @param m    The result of your matrix multiplication using this mode
	 */
	public static void saveResult(int seed, Mode mode, double[][] m) {

		if (m.length > 100)
			return;

		String filename = String.format("O2Result_%d_%s_%d.txt", seed, mode, m.length);

		try (PrintWriter writer = new PrintWriter(filename)) {
			writer.printf("seed=%d mode=%s n=%d\n\n", seed, mode, m.length);

			for (int i = 0; i < m.length; i++) {
				for (int j = 0; j < m[0].length; j++) {

					writer.printf("%.2f ", m[i][j]);
				}

				writer.println();
			}

			writer.flush();
			writer.close();

		} catch (Exception e) {
			System.out.printf("Got exception when trying to write file %s : %s", filename, e.getMessage());
		}

	}

}