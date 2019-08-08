import files.DotParser;
import algorhithm.AStar;
import graph.Graph;
import files.OutputCreator;
import org.apache.commons.cli.*;
import scheduler.State;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static boolean isStringIsNumericAndPositive(String str) {
        try {
            if (Integer.parseInt(str) > 0) return true;
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return false;
    }

    private static String[] cliParser(String[] args) {

        String[] result = new String[5];

        Options options = new Options(); //Adding option values, e.g. -a -f -g etc., which will be parsed
        options.addOption("p", true, "Number of cores");
        options.addOption("v", false, "Visualise the search");
        options.addOption("o", true, "Choose output file name");

        // parser is used for the parsing of the input, here args
        CommandLineParser parser = new DefaultParser();

        // Default values for parser
        String defaultFile = "data/input.dot";
        String defaultProcessors = "2";
        String defaultOutput = "output.dot";
        String defaultCores = "1";
        String defaultVisulize = "false";
        result[0] = defaultFile;
        result[1] = defaultProcessors;
        result[2] = defaultOutput;
        result[3] = defaultCores;
        result[4] = defaultVisulize;

        // Mandatory options
        if (args.length > 1) {
            result[0] = args[0]; // File path
            if (isStringIsNumericAndPositive(args[1])) result[1] = args[1]; // Number of processors
            else { System.err.println("Invalid value for number of processors, default value \"" + defaultProcessors + "\" chosen");}

        } else {
            System.out.println("Options for file path and number of processors missing. Default values (File path: \"" + defaultFile + "\", Num. processors: \"" +
                    defaultProcessors + ") chosen");
        }

        // Optional options
        try {
            CommandLine cmd = parser.parse(options, args);

            //This approach can be followed for Options with values
            if(cmd.hasOption("p")) {
                if (isStringIsNumericAndPositive(cmd.getOptionValue("P"))) { result[3] = cmd.getOptionValue("P"); } // handles -p (number of cores) option
            } else { System.out.println("Option -p not present or invalid, default value \"" + defaultCores + "\" chosen"); }

            if(cmd.hasOption("v")) { result[4] = "true"; } // handles -v flag (visualization) option
            else { System.out.println("Option -v not present, default value \"" + defaultVisulize + "\" chosen"); }

            if(cmd.hasOption("o")) { result[2] =  cmd.getOptionValue("o"); } // handles -o (output file name) option
            else { System.out.println("Option -o not present, default \"" + defaultOutput + "\" chosen"); }

        } catch (ParseException e) { //Will be thrown if no value is provided
            System.err.println(e);
            System.out.println("Default values (Num. cores: \"" + defaultCores + "\", visualise: \"" +
                    defaultVisulize + ", output file: \"" + defaultOutput + "\") chosen");
        }

        return result;
    }

    public static void main(String[] args) {
      //  System.out.println(g);


        String[] result = cliParser(args); // result[0]: file path, result[1]: num. processors, result[2]: output, result[3]: num. cores, result[4]: visualise
                                           // result[] vil be an array of Strings, remember to parse value to correct type

        DotParser dp = new DotParser(new File(result[0]));
        Graph g1 = null;
        try {
            g1 = dp.parseGraph();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        State solution = new AStar(Integer.parseInt(result[1]),g1).runAlgorhithm();

        //System.out.println(solution);
        OutputCreator out = new OutputCreator(solution);
        out.displayOutputOnConsole();


    }

}

