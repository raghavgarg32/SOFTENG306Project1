package script;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        FileParser fr = new FileParser("data/input.dot");

        try {
            fr.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

