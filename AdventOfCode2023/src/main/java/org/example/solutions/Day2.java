package org.example.solutions;

import org.example.fileHandling.FileHandling;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Day2 {

    private static final Logger logger = Logger.getLogger(Day2.class.getName());
    private static List<String> fileOutput;

    private static List<String> match;

    private static Map<String, String> handMap = new HashMap<>() {{
        put("x", "Rock");
        put("y", "Paper");
        put("z", "Scissors");
        put("a", "Rock");
        put("b", "Paper");
        put("c", "Scissors");
    }};

    private static Map<String, Integer> handToValueMap = new HashMap<>() {{
        put("Rock", 1);
        put("Paper", 2);
        put("Scissors", 3);
    }};

    private static int rPSMatchOutcome(String your, String opponent){
        int matchResult = 0;
        if(your.equals(opponent)){
            matchResult = 3;
        } else if((your.equals("Paper") && opponent.equals("Rock")) ||
                (your.equals("Scissors") && opponent.equals("Paper")) ||
                (your.equals("Rock") && opponent.equals("Scissors"))){
            matchResult = 6;
        } else {
            return 0 + handToValueMap.get(your);
        }
        return matchResult + handToValueMap.get(your);
    }

    /*
        private static int runRPSMatch(List<String> hands){
        rPSMatchOutcome(hands.get(0), hands.get(1));
    }
     */

/*
    public static void solution(){
        fileOutput.stream().forEach(line ->
        //runRPSMatch(line.split(" "));

    }
 */


    public static void main(String[] args) throws IOException {
        InputStream inputStream = Day1.class.getResourceAsStream("/informationFiles/inputDay1.txt");
        fileOutput= FileHandling.readFromInputAndMakeList(inputStream);
        /*
        long startTime = System.nanoTime();
        solution();
        long endTime = System.nanoTime();
        logger.info(String.format("The problem took %d milliseconds", ((endTime-startTime)/100000)));
        */

    }
}
