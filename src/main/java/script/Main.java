package script;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        File f = new File("data/input.dot");
        try{
            parse(f);
        } catch (Exception e){
            System.out.println("Some Error Occurred");
        }
    }

    /**
     * Takes the file input of correct syntax and reads the text of the file to be formatted
     * @param f
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void parse(File f) throws FileNotFoundException, IOException {

        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        Graph.createGraph(br);

    }

}

