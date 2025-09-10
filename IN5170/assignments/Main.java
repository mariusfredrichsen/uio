import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* You are allowed to 1. add modifiers to fields and method signatures of subclasses, and 2. add code at the marked places, including removing the following return */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        LinkedQueue<Integer> inputQueue = new LinkedQueue<>();
        LinkedQueue<Integer> evenQueue = new LinkedQueue<>();
        LinkedQueue<Integer> oddQueue = new LinkedQueue<>();

        HashMap<Boolean, LinkedQueue<Integer>> layer = new HashMap<>();
        layer.put(true, evenQueue);
        layer.put(false, oddQueue);

        int n = 1000;

        ExecutorService inputExc = Executors.newCachedThreadPool();
        /* TODO: start n threads, each adding a single number to inputQueue */

        Mapper<Integer, Boolean> mapper1 = new Mapper<Integer, Boolean>(layer) {
            @Override
            void transform(Integer input) {
                /* TODO: take number and put it into the right queue */
            }
        };
        Mapper<Integer, Boolean> mapper2 = new Mapper<Integer, Boolean>(layer) {
            @Override
            void transform(Integer input) {
                /* TODO: take number and put it into the right queue */
            }
        };

        ExecutorService distribute = Executors.newCachedThreadPool();
        /*
         * TODO: start n threads, each taking a single number from inputQueue to either
         * mapper1 or mapper2
         * each mapper must have the same amount of work
         * the mapper must add its number to the correct queue
         */

        Reducer<Integer> reducer1 = new Reducer<Integer>() {
            @Override
            protected void reduce(Integer input) {
                /* implement me */
            }
        };
        Reducer<Integer> reducer2 = new Reducer<Integer>() {

            @Override
            protected void reduce(Integer input) {
                /* implement me */
            }
        };

        ExecutorService reduce = Executors.newCachedThreadPool();

        /*
         * TODO: start n threads, each taking one number from either queue and giving it
         * to a reducer.
         * Reducer 1 will only add even numbers, reducer 2 will only add off numbers
         */

        Thread.sleep(2000);
        System.out.println("Sum even: " + reducer1.current);
        System.out.println("Sum odd: " + reducer2.current);

        int total = 0;
        for (int i = 1; i <= n; i++) {
            total += i * i;
        }
        System.out.println(total - (reducer1.current + reducer2.current));
    }
}