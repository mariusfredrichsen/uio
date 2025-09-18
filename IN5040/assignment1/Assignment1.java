import com.espertech.esper.common.client.EPCompiled;
import com.espertech.esper.common.client.EventBean;
import com.espertech.esper.common.client.configuration.Configuration;
import com.espertech.esper.compiler.client.CompilerArguments;
import com.espertech.esper.compiler.client.EPCompileException;
import com.espertech.esper.compiler.client.EPCompiler;
import com.espertech.esper.compiler.client.EPCompilerProvider;
import com.espertech.esper.runtime.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Assignment1 {
    private static final long SLEEP_TIME = 5; /*
                                               * This variable determines the output rate of tuples sent to Esper to
                                               * "simulate" a data stream.
                                               */
    private static Scanner scanner;

    /* This class is used by Esper to output the query results to standard output */
    public static class EventListener implements UpdateListener {
        @Override
        public void update(EventBean[] newData, EventBean[] oldData, EPStatement epStatement, EPRuntime epRuntime) {
            System.out.println(System.currentTimeMillis() + " " + newData[0].getUnderlying());
        }
    }

    public static void main(String[] args)
            throws InterruptedException, FileNotFoundException, EPCompileException, EPDeployException {
        Logger logger = LoggerFactory.getLogger(Assignment1.class); /* Set up the logger to avoid Esper warnings.. */

        EPCompiler compiler = EPCompilerProvider.getCompiler();
        Configuration configuration = new Configuration();
        configuration.getCommon().getTimeSource().setTimeUnit(TimeUnit.MILLISECONDS); /*
                                                                                       * Enable Esper to understand the
                                                                                       * timestamps from the .csv files.
                                                                                       * Hint: ext_timed..
                                                                                       */

        configuration.getCommon().addEventType("jfk", WeatherTuple.class);
        configuration.getCommon().addEventType("san_francisco", WeatherTuple.class);

        EPRuntime runtime = EPRuntimeProvider.getDefaultRuntime(configuration);

        /*
         * This loop will loop through a set of queries. Note that the corresponding
         * .epl files are needed
         */

        scanner = new Scanner(new File(args[0])); /* Reads the filename from program argument */
        String query = scanner.useDelimiter("\\Z").next();

        CompilerArguments compilerArgs = new CompilerArguments(configuration);
        EPCompiled epCompiled = compiler.compile("@name('esper-statement') " + query, compilerArgs);
        EPDeployment deployment = runtime.getDeploymentService().deploy(epCompiled);
        EPStatement statement = runtime.getDeploymentService().getStatement(deployment.getDeploymentId(),
                "esper-statement");
        statement.addListener(new EventListener());

        /*
         * Define the files that holds the data that we will use to simulate the input
         * streams
         */
        List<String> filenames = Arrays.asList("jfk.csv", "san_francisco.csv");
        List<TupleInputJob> inputJobs = new ArrayList<>();

        /* Make one thread for each file, and have them run in parallel */
        for (String filename : filenames) {
            TupleInputJob tupleInputJob = new TupleInputJob(runtime, filename);
            tupleInputJob.start();
            inputJobs.add(tupleInputJob);
        }

        /* Wait for each input thread to finish */
        for (TupleInputJob inputJob : inputJobs)
            inputJob.join();
    }

    /* This class represents the thread that (in parallel) send tuples to Esper */
    private static class TupleInputJob extends Thread {
        private final EPRuntime ePRuntime;
        private String filename;

        public TupleInputJob(EPRuntime ePRuntime, String filename) {
            this.ePRuntime = ePRuntime;
            this.filename = filename;
        }

        @Override
        public void run() {
            File file = new File(filename);
            String streamName = filename.split("\\.")[0];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = null;

                while ((line = reader.readLine()) != null) {
                    WeatherTuple tuple = new WeatherTuple();
                    StringTokenizer tokenizer = new StringTokenizer(line, ";");

                    tuple.setTimestamp(
                            LocalDate.parse(tokenizer.nextToken()).atStartOfDay(ZoneId.systemDefault()).toEpochSecond()
                                    * 1000); // Esper expect milliseconds since epoch for timestamps!
                    tuple.setWeatherStation(tokenizer.nextToken());
                    tuple.setStationName(tokenizer.nextToken());
                    tuple.setAverageTemperature(Double.parseDouble(tokenizer.nextToken()));
                    tuple.setMinimumTemperature(Double.parseDouble(tokenizer.nextToken()));
                    tuple.setMaximumTemperature(Double.parseDouble(tokenizer.nextToken()));
                    tuple.setAverageWindSpeed(Double.parseDouble(tokenizer.nextToken()));
                    tuple.setPrecipitation(Double.parseDouble(tokenizer.nextToken()));
                    tuple.setWeather(tokenizer.nextToken());
                    ePRuntime.getEventService().sendEventBean(tuple, streamName);
                    Thread.sleep(SLEEP_TIME); /*
                                               * This will slow things a bit down, just to get a bit more "stream-like"
                                               * behaviour from our program
                                               */
                }
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error during tuple transmission from file " + filename + ": " + e);
            }
        }
    } // End of inner static class TupleInputJob

} // End of class Assignment1.java
