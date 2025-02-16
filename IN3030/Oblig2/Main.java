import java.util.Random;


public class Main {

	public static void main(String[] args) {

		// Process these from the command line
		int seed = 42;
		int n = 5;

        MatrixGenerator mg = new MatrixGenerator();

		// Get the matrices
		double[][] a = mg.generateMatrixA(seed, n);
		double[][] b = mg.generateMatrixB(seed, n);

		Seq s = new Seq();
        SeqA sa = new SeqA();

        double[][] c = s.multiply(a, b);

		// Save the result
		mg.saveResult(seed, MatrixGenerator.Mode.SEQ_NOT_TRANSPOSED, c);

        c = sa.transponseMatrix(c);

        mg.saveResult(seed, MatrixGenerator.Mode.SEQ_A_TRANSPOSED, c);
	}

}