package org.example.solutions;

import org.example.fileHandling.FileHandling;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Logger;

public class DayTwo2023 {
    private static final Logger logger = Logger.getLogger(DayTwo2023.class.getName());
    private static List<String> fileOutput;

    // Need to find all indexes of a word in String
    private static HashMap<Integer, String> gameMap;

    public static void solution(){
        gameMap.forEach((k,v) -> System.out.printf("hej"));
    }

    // The idea of the game is to check if the amount of cubes taken each time is lower than the highest possible amount
    private static void getGameIndexSum(int red, int green, int blue,List<String> bricksList){
        int gameIndex = 1;
        int sumOfGameIndex = 0;
        for(String bricks : bricksList){
            sumOfGameIndex += isBrickSetPossible(red, green, blue, bricks)? gameIndex : 0;
            gameIndex++;
        }
        logger.info(String.format("The sum of the game index is: %d", sumOfGameIndex));
    }

    private static boolean isBrickSetPossible(int red, int green, int blue, String brickSet){
        String[] a = brickSet.replace(";", ",").split(",");
        List<Boolean> logicList = new ArrayList<>();
        Arrays.stream(a).toList().forEach(string -> logicList.add(isBrickColorPossible(string, red, green, blue)));

        return !logicList.contains(false);
    }

    private static boolean isBrickColorPossible(String bricks, int red, int green, int blue){
        int brickLimit = 0;
        List<String> bricksInfo = Arrays.stream(bricks.trim().split(" ")).toList();
        String color = bricksInfo.get(1);
        switch (color) {
            case "blue":
                brickLimit = blue;
                break;
            case "red":
                brickLimit = red;
                break;
            case "green":
                brickLimit = green;
                break;
            default:
                break;
        }
        return  Integer.valueOf(bricksInfo.get(0)) <= brickLimit;
    }

    public static void main(String[] args) throws IOException {
        InputStream inputStream = Day1.class.getResourceAsStream("/informationFiles/input23Day2.txt");
        //InputStream inputStream = Day1.class.getResourceAsStream("/informationFiles/input23Day2Test.txt");
        fileOutput= FileHandling.readFromInputAndMakeList(inputStream);
        gameMap = new HashMap<>();
        fileOutput.forEach(line -> gameMap.put(Integer.valueOf(Arrays.stream(line.split(":")).findFirst().get().replace("Game", "").trim()), Arrays.stream(line.split(":")).toList().get(1)));
        long startTime = System.nanoTime();
        getGameIndexSum(12, 13, 14,gameMap.values().stream().toList());
        long endTime = System.nanoTime();
        logger.info(String.format("Problem one took %d milliseconds", ((endTime-startTime)/100000)));
        startTime = System.nanoTime();
        //solution(convertToNumberList());
        endTime = System.nanoTime();
        logger.info(String.format("Problem two took %d milliseconds", ((endTime-startTime)/100000)));
    }
}
