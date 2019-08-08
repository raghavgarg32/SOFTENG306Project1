import files.DotParser;
import algorhithm.AStar;
import graph.Graph;
import files.OutputCreator;
import org.apache.commons.cli.*;
import scheduler.State;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    private static String[] cliParser(String[] args) {

        String[] result = new String[3];

        Options options = new Options(); //Adding option values, e.g. -a -f -g etc., which will be parsed
        options.addOption("f", true, "Choose input file");
        options.addOption("p", true, "Number of processors");
        options.addOption("o", true, "Choose output file name");

        // parser is used for the parsing of the input, here args
        CommandLineParser parser = new DefaultParser();

        // Default values for parser
        String defaultFile = "data/input.dot";
        String defaultProcessors = "2";
        String defaultOutput = "output.dot";
        result[0] = defaultFile;
        result[1] = defaultProcessors;
        result[2] = defaultOutput;

        try {
            CommandLine cmd = parser.parse(options, args);

            //This approach can be followed for Options with values
            if(cmd.hasOption("f")) { result[0] = cmd.getOptionValue("f"); } // handles -f (file path) option
            else { System.out.println("Option -f not present, default \"" + defaultFile + "\" chosen"); }

            if(cmd.hasOption("p")) { result[1] =  cmd.getOptionValue("p"); } // handles -p (number of processors) option
            else { System.out.println("Option -p not present, default \"" + defaultProcessors + "\" chosen"); }

            if(cmd.hasOption("o")) { result[2] =  cmd.getOptionValue("o"); } // handles -o (output file name) option
            else { System.out.println("Option -o not present, default \"" + defaultOutput + "\" chosen"); }

        } catch (ParseException e) { //Will be thrown if no value is provided
            System.err.println(e);
            System.out.println("Default values (File path: \"" + defaultFile + "\", Num. processors: \"" +
                    defaultProcessors + ", Num. processors: \"" + defaultOutput + "\") chosen");
        }

        return result;
    }

    public static void main(String[] args) {
      //  System.out.println(g);

        String[] result = cliParser(args); // result[0]: file path, result[1]: num. processors, result[2]: output

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

