
import java.util.concurrent.*;

class WaitNextC {

    static Semaphore okToKick = new Semaphore(1, true);
    static Semaphore okToEnterHolding = new Semaphore(1, true);
    static Semaphore holdingArea = new Semaphore(0, true);
    static int N = 4; // number of iterations
    static boolean first = true;
    static int debuglevel = 9;  // 4: varispeed resumption; 3: varispeed delay time; 2: sems values; 1, : (not implemented) 
    static boolean variableSpeedThreads = true;
    static double variableSpeedRate = 0.0; // threads sleep for a random time between 0 and this rate in milliseconds
    static int extraSlowThreads = 0; // number of threads that sleep 10x variableSpeedRate

    public static void printSems(int id, int iteration) {
        if (debuglevel > 1) {
            System.out.println("Thread " + id + ", " + iteration + "    Sems: KICK " + okToKick.availablePermits()
                    + " Q: " + okToKick.getQueueLength()
                    + "; ENTER " + okToEnterHolding.availablePermits() + " Q: " + okToEnterHolding.getQueueLength()
                    + "; HOLD:" + holdingArea.availablePermits() + " Q: " + holdingArea.getQueueLength());
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

    public static void debugPrintln(int id, int iteration, int buglevel, String msg) {
        if (debuglevel >= buglevel) {  // then print the message
            System.out.println("Thread " + id + ", " + iteration + msg);
            printSems(id, iteration);
        }
    }

    public static void waitNext(int id, int iteration) {
        try {
            variSpeed(id, iteration); // There is a variSpeed delay before EVERY action

            okToKick.acquire(); // right to kick the previous thread out

            variSpeed(id, iteration);

            if (first) { // kick previous one out - except first time
                first = false;
            } else {
                holdingArea.release(); // Kick out the previous thread! Well actually, if the thread is slow and has as yet NOT
                // entered the holding area, then give it the right to exit immediately

                variSpeed(id, iteration);
            }

            okToEnterHolding.acquire(); // Normally, the right to enter is available, BUT, if the KICKED thread is SLOOOW to exit
            // we need to wait for it to get out of the HOLDING area.
            // It will signal us once it has gotten its act together and exited the holding area.

            variSpeed(id, iteration);

            okToKick.release(); // we have done our kicking, so let the next one enter

            variSpeed(id, iteration);

            holdingArea.acquire(); // my turn to wait - will be released by a future kicker
            // well, actually, if we are slow and the kicker is
            // FAST then the kicker will ALREADY have kicked us
            // this info is reflected in the semaphore (it being positive)
            // debugPrintln(id, iteration, 1, " GOT KICKED OUT of holding area :-)");

            variSpeed(id, iteration);

            okToEnterHolding.release(); // I am out of the holding area, let the next one get access rights to the holding area

            variSpeed(id, iteration);

        } catch (Exception e) {
            return;
        }
    }

    public static void main(String[] args) {
        int numberofthreads = 3;

        if (args.length < 1) {
            System.out.println("use: java WaitNextC <number of threads> <num iterations> <debug level> <varispeed> <num of extra slow threads>");
            System.out.println("   only the first arguement, number of threads, is required; defaults are:");
            System.out.println("   iterations: " + N);
            System.out.println("   debugLevel: " + debuglevel);
            System.out.println("   variableSpeedRate: " + variableSpeedRate);
            System.out.println("   extraSlowThreads: " + extraSlowThreads);
            System.exit(0);
        }
        if (args.length >= 1) {
            numberofthreads = Integer.parseInt(args[0]);
            System.out.println("   threads: " + N);
        }
        if (args.length >= 2) {
            N = Integer.parseInt(args[1]);
            System.out.println("   iterations " + N);
        }
        if (args.length >= 3) {
            debuglevel = Integer.parseInt(args[2]);
            System.out.println("   debugLevel: " + debuglevel);
        }
        if (args.length >= 4) {
            variableSpeedRate = (double) Integer.parseInt(args[3]);
            System.out.println("   variableSpeedRate: " + variableSpeedRate);
        }
        if (args.length >= 5) {
            extraSlowThreads = Integer.parseInt(args[4]);
            System.out.println("   extraSlowThreads: " + extraSlowThreads);
        }
        if (variableSpeedRate <= 0.0) {
            variableSpeedThreads = false;
        }

        System.out.println("*******************************************************************************************");

        System.out.println("NOTE: after the first thread has entered HOLDING, there will always be ONE thread either WAITING or about to WAIT, so the program will NEVER terminate!");

        Thread[] t = new Thread[numberofthreads];

        System.out.println("Number of threads: " + numberofthreads + ";  iterations: " + N + ";  debug: " + debuglevel + ";  varispeed: " + ((long) variableSpeedRate) + " ms;  extra slow: " + extraSlowThreads);

        for (int j = 0; j < numberofthreads; j++) {
            (t[j] = new Thread(new Worker(j))).start();
        }

    }

    static class Worker implements Runnable {

        int myId;

        public Worker(int in) {
            myId = in;
        }

        ;

      public void run() {
            debugPrintln(myId, 0, 1, " START thread");
            try {
                TimeUnit.MILLISECONDS.sleep((long) myId * 1000); // let them start in order.
            } catch (Exception e) {
                return;
            };

            for (int i = 0; i < N; i++) {
                debugPrintln(myId, i, 2, " START iteration");
                waitNext(myId, i);
                debugPrintln(myId, i, 2, " END iteration");
            }
            debugPrintln(myId, 0, 1, " END thread");
        }

    }

}
