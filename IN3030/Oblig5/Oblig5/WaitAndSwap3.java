
import java.util.concurrent.*;

class WaitAndSwap3 {

    static Semaphore coach;
    static Semaphore course;
    Semaphore benchOne;
    Semaphore benchTwo;
    Semaphore benchThree;
    int waiting = 0;
    boolean first = true;

    static int debuglevel = 9;  // 4: varispeed resumption; 3: varispeed delay time; 2: sems values; 1, : (not implemented) 

    static boolean variableSpeedThreads = true;
    static double variableSpeedRate = 0.0; // threads sleep for a random time between 0 and this rate in milliseconds
    static int extraSlowThreads = 0; // number of threads that sleep 10x variableSpeedRate

    public WaitAndSwap3() {
        coach = new Semaphore(1, true);
        course = new Semaphore(3, true);
        benchOne = new Semaphore(0, true);
        benchTwo = new Semaphore(0, true);
        benchThree = new Semaphore(0, true);

    }

    public static void printSems(int id, int iteration) {
        if (debuglevel > 1) {
            System.out.println("Thread " + id + ", " + iteration + "    Sems: KICK " + coach.availablePermits()
                    + " Q: " + coach.getQueueLength()
                    + "; ENTER " + course.availablePermits() + " Q: " + course.getQueueLength());
        }
    }

    public static void debugPrintln(int id, int iteration, int buglevel, String msg) {
        if (debuglevel >= buglevel) {  // then print the message
            System.out.println("Thread " + id + ", " + iteration + msg);
            printSems(id, iteration);
        }
    }

    public static void variSpeed(int id, int iteration) { // let the calling thread sleep a random time
        long myWait = (long) (Math.random() * variableSpeedRate);
        if (variableSpeedRate == 0.0) {
            return;
        }
        if (id < extraSlowThreads) {
            myWait = (long) (variableSpeedRate * 10.0);
        }
        // make the first <extraSlowThreads> always wait 10xvariableSpeedRate
        debugPrintln(id, iteration, 3, "         variSpeed delay: " + myWait + " ms");
        if (variableSpeedThreads) 
         try {
            TimeUnit.MILLISECONDS.sleep(myWait);
        } catch (Exception e) {
            return;
        };
        debugPrintln(id, iteration, 4, "         resuming after variSpeed delay");
    }

    public void waitAndSwap3(int id) {
        try {
            variSpeed(id, 0);

            // System.out.println(String.format("THREAD %s COACH TRY", id));
            coach.acquire(); // coach lets runner to course
            System.out.println(String.format("THREAD %s STARTED", id));
            // System.out.println(String.format("THREAD %s COACH GOT", id));            
            
            variSpeed(id, 0);
            // System.out.println(String.format("THREAD %s BENCH RELEASE", id));
            if (first) {
                if (waiting == 2) {
                    first = false;
                }
            } else if (waiting % 3 == 0) {
                variSpeed(id, 0);
                
                System.out.println("RELEASE ONE");
                benchOne.release(); // the next runner must leave the bench, kind of
                
                variSpeed(id, 0);
                
                System.out.println("RELEASE TWO");
                benchTwo.release();
                
                variSpeed(id, 0);
                
                System.out.println("RELEASE THREE");
                benchThree.release();
                
                variSpeed(id, 0);
                
                waiting = 0;
                
                variSpeed(id, 0);
                
                // System.out.println(String.format("THREAD %s BENCH RELEASED", id));
            }
            
            variSpeed(id, 0);
            
            waiting += 1;

            variSpeed(id, 0);

            // System.out.println(String.format("THREAD %s COURSE TRY", id));
            course.acquire(); // start running on the course
            // System.out.println(String.format("THREAD %s COURSE GOT", id));

            variSpeed(id, 0);


            // System.out.println(String.format("THREAD %s COACH RELEASE", id));
            coach.release(); // let the coach coach another person
            // System.out.println(String.format("THREAD %s COACH RELEASED", id));

            variSpeed(id, 0);

            switch (waiting) {
                case 1 -> {
                    System.out.println(String.format("THREAD %s WAITING", id));
                    benchOne.acquire(); // finished, sit on the bench
                    // System.out.println(String.format("THREAD %s BENCH ONE GOT", id));
                }
                case 2 -> {
                    System.out.println(String.format("THREAD %s WAITING", id));
                    benchTwo.acquire(); // finished, sit on the bench
                    // System.out.println(String.format("THREAD %s BENCH TWO GOT", id));
                }
                case 3 -> {
                    System.out.println(String.format("THREAD %s WAITING", id));
                    benchThree.acquire(); // finished, sit on the bench
                    // System.out.println(String.format("THREAD %s BENCH THREE GOT", id));
                }
            }

            variSpeed(id, 0);

            System.out.println(String.format("THREAD %s FINISHED", id));
            course.release();

        } catch (InterruptedException ex) {
        }
    }

    public void runWaitAndSwap3(int k) {
        Thread[] threads = new Thread[k];

        for (int i = 1; i <= k; i++) {
            Runner runner = new Runner(i, this);
            threads[i-1] = new Thread(runner);
        }

        for (Thread thread : threads) {
            thread.start();
            try {
                Thread.sleep(10); // Add a delay of 100 milliseconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore the interrupted status
            }
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (Exception e) {
        }
    }

    class Runner implements Runnable {

        int id;
        WaitAndSwap3 was3;

        public Runner(int id, WaitAndSwap3 was3) {
            this.id = id;
            this.was3 = was3;
        }

        @Override
        public void run() {
            was3.waitAndSwap3(id);
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Command must take 1 argument k. java WaitAndSwap3 <k>");
        } 
        int k = Integer.parseInt(args[0]);
        
        WaitAndSwap3 was = new WaitAndSwap3();
        was.runWaitAndSwap3(k);
    }
}
