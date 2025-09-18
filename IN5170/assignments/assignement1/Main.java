package assignement1;

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
        for (int i = 1; i <= n; i++) {
            final int num = i;
            inputExc.submit(() -> inputQueue.insert(num));
        }

        Mapper<Integer, Boolean> mapper1 = new Mapper<Integer, Boolean>(layer) {
            @Override
            void transform(Integer input) {
                /* TODO: take number and put it into the right queue */
                this.layer.get(input % 2 == 0).insert(input * input);
                this.count++;
            }
        };
        Mapper<Integer, Boolean> mapper2 = new Mapper<Integer, Boolean>(layer) {
            @Override
            void transform(Integer input) {
                this.layer.get(input % 2 == 0).insert(input * input);
                this.count++;
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

        for (int i = 0; i < n; i++) {
            final int idx = i;
            distribute.submit(() -> {
                Integer number = null;
                while (number == null) {
                    number = inputQueue.delfront();
                    if (number != null)
                        if (idx % 2 == 0) {
                            mapper1.transform(number);
                        } else {
                            mapper2.transform(number);
                        }
                }
            });
        }

        Reducer<Integer> reducer1 = new Reducer<Integer>() {
            @Override
            synchronized protected void reduce(Integer input) {
                this.current += input;
                this.count++;
                /* implement me */
            }
        };
        Reducer<Integer> reducer2 = new Reducer<Integer>() {

            @Override
            synchronized protected void reduce(Integer input) {
                this.current += input;
                this.count++;
                /* implement me */
            }
        };

        ExecutorService reduce = Executors.newCachedThreadPool();

        /*
         * TODO: start n threads, each taking one number from either queue and giving it
         * to a reducer.
         * Reducer 1 will only add even numbers, reducer 2 will only add off numbers
         */

        for (int i = 0; i < n; i++) {
            final int idx = i;
            reduce.submit(() -> {
                Integer number = null;
                while (number == null) {
                    number = layer.get(idx % 2 == 0).delfront();
                    if (number != null) {
                        if (number % 2 == 0) {
                            reducer1.reduce(number);
                        } else {
                            reducer2.reduce(number);
                        }
                    }
                }
            });
        }

        Thread.sleep(2000);

        inputExc.shutdown();
        distribute.shutdown();
        reduce.shutdown();

        System.out.println("Sum even: " + reducer1.current);
        System.out.println("Sum odd: " + reducer2.current);

        int total = 0;
        for (int i = 1; i <= n; i++) {
            total += i * i;
        }
        System.out.println("Mapper1 count: " + mapper1.count);
        System.out.println("Mapper2 count: " + mapper2.count);
        System.out.println("Reducer1 count: " + reducer1.count);
        System.out.println("Reducer2 count: " + reducer2.count);
        System.out.println("Total: " + total);
        System.out.println(total - (reducer1.current + reducer2.current));
    }
}