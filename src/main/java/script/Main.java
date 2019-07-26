package script;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        File f = new File("data/input.dot");
        try{
            parse(f);
        } catch (Exception e){
            System.out.println("bad");
        }

    }

    public static void parse(File f)throws FileNotFoundException, IOException {

        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String st;
        while ((st = br.readLine()) != null)
            System.out.println(st);
    }
}

