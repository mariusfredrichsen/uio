
import java.io.PrintWriter;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int seed = 42;
        int[] sizes = {100, 200, 500, 1000};

        double[][][] times = new double[sizes.length][6][7];
        for (int q = 0; q < 7; q++) {
            // Process these from the command line

            System.out.println(String.format("Starting iteration %s with matrix sizes %s to %s", q, sizes[0], sizes[sizes.length - 1]));
            for (int i = 0; i < sizes.length; i++) {
				int n = sizes[i];

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
                times[i][0][q] = time;
                // System.out.println("Time for seqA: " + time + "ms");

                mg.saveResult(seed, MatrixGenerator.Mode.SEQ_NOT_TRANSPOSED, seq);

                start = System.nanoTime();
                double[][] seqA = s.seqA(a, b);
                checkCorrectness(ans, seqA);
                end = System.nanoTime();
                time = ((end - start) / 1000000.0);
                times[i][1][q] = time;
                // System.out.println("Time for seqA: " + time + "ms");

                mg.saveResult(seed, MatrixGenerator.Mode.SEQ_A_TRANSPOSED, seqA);

                start = System.nanoTime();
                double[][] seqB = s.seqB(a, b);
                checkCorrectness(ans, seqB);
                end = System.nanoTime();
                time = ((end - start) / 1000000.0);
                times[i][2][q] = time;
                // System.out.println("Time for seqA: " + time + "ms");

                mg.saveResult(seed, MatrixGenerator.Mode.SEQ_B_TRANSPOSED, seqB);

                start = System.nanoTime();
                double[][] par = p.par(a, b);
                checkCorrectness(ans, par);
                end = System.nanoTime();
                time = ((end - start) / 1000000.0);
                times[i][3][q] = time;
                // System.out.println("Time for seqA: " + time + "ms");

                mg.saveResult(seed, MatrixGenerator.Mode.PARA_NOT_TRANSPOSED, par);

                start = System.nanoTime();
                double[][] parA = p.parA(a, b);
                checkCorrectness(ans, parA);
                end = System.nanoTime();
                time = ((end - start) / 1000000.0);
                times[i][4][q] = time;
                // System.out.println("Time for seqA: " + time + "ms");

                mg.saveResult(seed, MatrixGenerator.Mode.PARA_A_TRANSPOSED, parA);

                start = System.nanoTime();
                double[][] parB = p.parB(a, b);
                checkCorrectness(ans, parB);
                end = System.nanoTime();
                time = ((end - start) / 1000000.0);
                times[i][5][q] = time;
                // System.out.println("Time for seqA: " + time + "ms");

                mg.saveResult(seed, MatrixGenerator.Mode.PARA_B_TRANSPOSED, parB);
            }

        }
        // get the median of results
        for (double[][] results : times) {
			for (double[] result : results) {
				Arrays.sort(result);
			}
        }
		

		for (int i = 0; i < sizes.length; i++) {
			int n = sizes[i];
			System.out.println(String.format(
				"Median values for N = %s:\nSeq time: %sms\nSeqA time: %sms\nSeqB time: %sms\nPar time: %sms\nParA time: %sms\nParB time: %sms\n",
				n, times[i][0][3], times[i][1][3], times[i][2][3], times[i][3][3], times[i][4][3], times[i][5][3]));
			System.out.println(String.format("Raw time in ms:\n%s\n%s\n%s\n%s\n%s\n%s\n", 
				times[i][0][3], times[i][1][3], times[i][2][3], times[i][3][3], times[i][4][3], times[i][5][3]));
			try (PrintWriter writer = new PrintWriter(String.format("%s_times.csv", n))) {
				writer.println("Method;Seq;SeqA;SeqB;Par;ParA;ParB");
				writer.println(String.format("Time(ms);%f;%f;%f;%f;%f;%f", 
					times[i][0][3], times[i][1][3], times[i][2][3], times[i][3][3], times[i][4][3], times[i][5][3]));

				writer.flush();
			} catch (Exception e) {
				System.out.printf("Got exception when trying to write file %s : %s", n, e.getMessage());
			}
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
