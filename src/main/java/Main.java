import files.DotParser;
import algorithm.AStar;
import graph.Graph;
import files.OutputCreator;

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

    public static boolean isWindows() {
        if (System.getProperty("os.name").toLowerCase().startsWith("windows")) return true;
        return false;
    }

    public static void printHelp() {
        System.out.println("Java -jar schdeuler.jar INPUT.dot P [OPTION]");
        System.out.println("INPUT.dot\ta task graph with integer weigh;ts in dot format.");
        System.out.println("P\t\tnumber of processors to schedule the INPUT graph on");

        System.out.println("\nOptional:");
        System.out.println("-p N\t\tuse N cores for execution in parallel default is sequential)(not yet implemented)");
        System.out.println("-v\t\tvisualise the serach");
        System.out.println("-o OUTPUT\toutput file is named OUTPUT (default is INPUT-output.dot)");
        System.out.println();
        System.out.println("Type \"-h\" to show this help menu");
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("-- SOFTENG 306 : Project 1 --");
        System.out.println();
        if (args.length == 0) {
            System.err.println("No arguments found, please try again. Please use the flag -h for help");
        } else if (args[0].equals("-h")) {
            printHelp();
        } // Checks for help command
        else {
            String[] result = cliParser(args); // result[0]: file path, result[1]: num. processors, result[2]: output
            // file, result[3]: num. cores, result[4]: visualize
            if (result != null) {
                try { // This is where the calculation is done
                    Graph g1 = new DotParser(new File(result[0])).parseGraph();
                    OutputCreator out = new OutputCreator(new AStar(Integer.parseInt(result[1]), g1).runAlgorithm());
                    out.createOutputFile(result[2]);
                    out.displayOutputOnConsole();
                } catch (FileNotFoundException e) { // If the file is not found, the error will be caught here
                    System.err.println("The file was not found. Please check your inputs again. Type -h for help");
                }
            }
        }
    }

    private static String getFileName(String path) {
        File f = new File(path);

        String fileNameWithOutExt = f.getName().replaceFirst("[.][^.]+$", "");

        return fileNameWithOutExt;

    }

    private static String[] cliParser(String[] args) {

        String[] result = new String[5];

        // Default values for parser
        String defaultOutput = "output.dot";
        String defaultCores = "1";
        String defaultVisualize = "false";
        result[2] = defaultOutput;
        result[3] = defaultCores;
        result[4] = defaultVisualize;

        // Mandatory options
        if (args.length > 1) { // If both file path and number of processors are entered
            result[0] = args[0]; // File path
            if (result[0].endsWith(".dot")) {
                defaultOutput = getFileName(result[0]) + "-" + defaultOutput;
                result[2] = defaultOutput;
            } else {
                System.err.println("Invalid file path ending, needs to be a \".dot\" file. Please type your inputs " +
                        "again. Type -h for help.");
                result = null;
            }

            if (result == null) { /* do nothing */ } else if (isStringIsNumericAndPositive(args[1]))
                result[1] = args[1]; // Number of processors
            else {
                System.err.println("Invalid value for number of processors, please type your inputs again. Type -h " +
                        "for help.");
                result = null;
            }

        } else { // If no arguments are provided
            System.err.println("Missing mandatory argument file path and/or number of processors. Type -h for help.");
            result = null;
        }
        // Optional options
        if (result != null) {
            for (int i = 2; i < args.length; i += 2) {
                String cmd = args[i];
                String value = "";
                //System.out.println(args.length);
                //System.out.println(i);

                //This approach can be followed for Options with values
                if (cmd.equals("-p")) {
                    if (isStringIsNumericAndPositive(value)) {
                        result[3] = checkPossible(i, args, cmd);
                    } // handles -p (number of cores) option
                } else if (cmd.equals("-o")) {
                    result[2] = checkPossible(i, args, cmd);
                    // handles -o (output file name) option
//                    System.out.println("Option -o not present, default \"" + defaultOutput + "\" chosen");
                }
                //This approach can be followed for Options without values (flags)
                else if (cmd.equals("-v")) {
                    result[4] = "true";
                } // handles -v flag (visualization) option
                else {
                    System.err.println("Unknown optional parameter " + cmd);
                    System.err.println("Please type -h for help");
                    System.exit(1);
                }
            }
            System.out.println("The graph will be processed with " + defaultCores + " processor(s)");
            System.out.println("The graph will be stored as " + defaultOutput);
            System.out.println();
        }

        return result;
    }

    public static String checkPossible(int i, String[] args, String cmd) {
        if (i + 1 < args.length) {
            return args[i + 1];
        } else {
            System.err.println("Missing value for parameter " + cmd);
            System.err.println("Please type -h for help");
            System.exit(1);
        }

        return null;
    }

}

