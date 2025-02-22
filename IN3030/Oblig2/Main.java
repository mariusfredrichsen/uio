import java.util.Random;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		
		int seed = 42;
		int[] sizes = {100, 200, 500, 1000, 2500};


		for (int n : sizes) {
			// Process these from the command line

			double[][] times = new double[6][7];
			System.out.println("\nSize of matrix: " + n + " x " + n);
			
			for (int q = 0; q < 7; q++) {

				
				MatrixGenerator mg = new MatrixGenerator();
				
				// Get the matrices
				double[][] a = mg.generateMatrixA(seed, n);
				double[][] b = mg.generateMatrixB(seed, n);

				Seq s = new Seq();
				Par p = new Par();

				double start;
				double end;
				double time;

				// Assume normal seq algorithm is the answer

				start = System.nanoTime();
				double[][] seq = s.seq(a, b);
				double[][] ans = seq;
				end = System.nanoTime();
				time = ((end - start) / 1000000.0);
				times[0][q] = time;
				// System.out.println("Time for seqA: " + time +  "ms");
				
				mg.saveResult(seed, MatrixGenerator.Mode.SEQ_NOT_TRANSPOSED, seq);



				start = System.nanoTime();
				double[][] seqA = s.seqA(a, b);
				checkCorrectness(ans, seqA);
				end = System.nanoTime();
				time = ((end - start) / 1000000.0);
				times[1][q] = time;
				// System.out.println("Time for seqA: " + time +  "ms");
				
				mg.saveResult(seed, MatrixGenerator.Mode.SEQ_A_TRANSPOSED, seqA);



				start = System.nanoTime();
				double[][] seqB = s.seqB(a, b);
				checkCorrectness(ans, seqB);
				end = System.nanoTime();
				time = ((end - start) / 1000000.0);
				times[2][q] = time;
				// System.out.println("Time for seqA: " + time +  "ms");

				mg.saveResult(seed, MatrixGenerator.Mode.SEQ_B_TRANSPOSED, seqB);



				start = System.nanoTime();
				double[][] par = p.par(a, b);
				checkCorrectness(ans, par);
				end = System.nanoTime();
				time = ((end - start) / 1000000.0);
				times[3][q] = time;
				// System.out.println("Time for seqA: " + time +  "ms");

				mg.saveResult(seed, MatrixGenerator.Mode.PARA_NOT_TRANSPOSED, par);



				start = System.nanoTime();
				double[][] parA = p.parA(a, b);
				checkCorrectness(ans, parA);
				end = System.nanoTime();
				time = ((end - start) / 1000000.0);
				times[4][q] = time;
				// System.out.println("Time for seqA: " + time +  "ms");

				mg.saveResult(seed, MatrixGenerator.Mode.PARA_A_TRANSPOSED, parA);



				start = System.nanoTime();
				double[][] parB  = p.parB(a, b);
				checkCorrectness(ans, parB);
				end = System.nanoTime();
				time = ((end - start) / 1000000.0);
				times[5][q] = time;
				// System.out.println("Time for seqA: " + time +  "ms");

				mg.saveResult(seed, MatrixGenerator.Mode.PARA_B_TRANSPOSED, parB);
			}

			// get the median of results
			for (double[] results : times) {
				Arrays.sort(results);
			}
			System.out.println(String.format("Seq time: %s\nSeqA time: %s\nSeqB time: %s\nPar time: %s\nParA time: %s\nParB time: %s", times[0][3], times[1][3], times[2][3], times[3][3], times[4][3], times[5][3]));
			System.out.println(String.format("%s\n%s\n%s\n%s\n%s\n%s\n", times[0][3], times[1][3], times[2][3], times[3][3], times[4][3], times[5][3]));
		}
	}

	public static void checkCorrectness(double[][] ans, double[][] out) {
		int n = ans.length;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				assert ans[i][j] == out[i][j];
			}
		}
	}

}