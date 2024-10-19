package assignment;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FlowTest {

    @Test
    public void testFlowCase1() {
        int[][] graph = readGraphFromFile("/assignment/testcase1.txt");
        Flow flowSolver = new Flow(graph);
        int maxFlow = flowSolver.solve();
        assertEquals(24, maxFlow);

        List<Integer> cut = flowSolver.cut();
        assertIterableEquals(Arrays.asList(0, 2), cut);
    }

    @Test
    public void testFlowCase2() {
        int[][] graph = readGraphFromFile("/assignment/testcase2.txt");
        Flow flowSolver = new Flow(graph);
        int maxFlow = flowSolver.solve();
        assertEquals(40, maxFlow);

        List<Integer> cut = flowSolver.cut();
        assertIterableEquals(Arrays.asList(0, 1, 4, 5, 6, 7, 8, 9, 10, 11, 12), cut);
    }

    @Test
    public void testFlowCase3() {
        int[][] graph = readGraphFromFile("/assignment/testcase3.txt");
        Flow flowSolver = new Flow(graph);
        int maxFlow = flowSolver.solve();
        assertEquals(98921, maxFlow);

        List<Integer> cut = flowSolver.cut();
        assertIterableEquals(Arrays.asList(0, 7, 10), cut);
    }

    @Test
    public void testFlowCase4() {
        int[][] graph = readGraphFromFile("/assignment/testcase4.txt");
        Flow flowSolver = new Flow(graph);
        int maxFlow = flowSolver.solve();
        assertEquals(84, maxFlow);

        List<Integer> cut = flowSolver.cut();
        assertIterableEquals(Arrays.asList(0, 4, 5, 6, 7, 8, 9, 11, 13, 14, 15, 22, 25, 26, 27, 29, 31, 32, 34, 36, 37), cut);
    }

    private int[][] readGraphFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(FlowTest.class.getResourceAsStream(filePath))))) {
            int N = Integer.parseInt(reader.readLine());
            int[][] graph = new int[N][N];

            for (int i = 0; i < N; i++) {
                String[] line = reader.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    graph[i][j] = Integer.parseInt(line[j]);
                }
            }
            return graph;
        } catch (Exception e) {
            throw new RuntimeException("Error reading graph from file", e);
        }
    }
}
