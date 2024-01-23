package org.example.solutions;

import org.example.fileHandling.FileHandling;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Logger;

public class DayTwo2023 {
    private static final Logger logger = Logger.getLogger(DayTwo2023.class.getName());
    private static HashMap<Integer, String> gameMap;
    private static HashMap<String, Integer> colorMap;
    private static final int RED = 12;
    private static final int BLUE = 14;
    private static final int GREEN = 13;

    // The idea of the game is to check if the amount of cubes taken each time is lower than the highest possible amount
    private static void getGameIndexSum(List<String> bricksList){
        int gameIndex = 1;
        int sumOfGameIndex = 0;
        for(String bricks : bricksList){
            sumOfGameIndex += isBrickSetPossible(bricks)? gameIndex : 0;
            gameIndex++;
        }
        logger.info(String.format("The sum of the game index is: %d", sumOfGameIndex));
    }

    private static void getMultipleValue( List<String> brickList){
        int sumOfGame = 0;
        for(String bricks : brickList){
            colorMap = new HashMap<>();
            colorMap.put("red", 0);
            colorMap.put("blue", 0);
            colorMap.put("green", 0);
            sumOfGame += getValueForBrickSet( bricks);
        }
        logger.info(String.format("The sum of the game is: %d", sumOfGame));

    }

    private static void updateColorValues( String bricks){
        List<String> bricksInfo = Arrays.stream(bricks.trim().split(" ")).toList();
        String color = bricksInfo.get(1);
        switch (color) {
            case "blue":
                    colorMap.put("blue", Math.max(colorMap.get("blue"), Integer.parseInt(bricksInfo.getFirst())));
                break;
            case "red":
                    colorMap.put("red", Math.max(colorMap.get("red"), Integer.parseInt(bricksInfo.getFirst())));
                break;
            case "green":
                    colorMap.put("green", Math.max(colorMap.get("green"), Integer.parseInt(bricksInfo.getFirst())));
                break;
            default:
                break;
        }
    }

    private static boolean isBrickSetPossible(String brickSet){
        String[] a = brickSet.replace(";", ",").split(",");
        List<Boolean> logicList = new ArrayList<>();
        Arrays.stream(a).toList().forEach(string -> logicList.add(isBrickColorPossible(string)));

        return !logicList.contains(false);
    }

    private static int getValueForBrickSet( String brickSet){
        String[] a = brickSet.replace(";", ",").split(",");
        Arrays.stream(a).forEach(DayTwo2023::updateColorValues);
        return colorMap.get("red")*colorMap.get("blue")*colorMap.get("green");
    }

    private static boolean isBrickColorPossible(String bricks){
        int brickLimit = 0;
        List<String> bricksInfo = Arrays.stream(bricks.trim().split(" ")).toList();
        String color = bricksInfo.get(1);
        switch (color) {
            case "blue":
                brickLimit = BLUE;
                break;
            case "red":
                brickLimit = RED;
                break;
            case "green":
                brickLimit = GREEN;
                break;
            default:
                break;
        }
        return  Integer.parseInt(bricksInfo.get(0)) <= brickLimit;
    }

    public static void main(String[] args) throws IOException {
        InputStream inputStream = Day1.class.getResourceAsStream("/informationFiles/input23Day2.txt");
        //InputStream inputStream = Day1.class.getResourceAsStream("/informationFiles/input23Day2Test.txt");
        List<String> fileOutput = FileHandling.readFromInputAndMakeList(inputStream);
        gameMap = new HashMap<>();
        fileOutput.forEach(line -> gameMap.put(Integer.valueOf(Arrays.stream(line.split(":")).findFirst().get().replace("Game", "").trim()), Arrays.stream(line.split(":")).toList().get(1)));
        List<String> gameList = gameMap.values().stream().toList();
        long startTime = System.nanoTime();
        getGameIndexSum(gameList);
        long endTime = System.nanoTime();
        logger.info(String.format("Problem one took %d milliseconds", ((endTime-startTime)/100000)));
        startTime = System.nanoTime();
        getMultipleValue(gameList);
        endTime = System.nanoTime();
        logger.info(String.format("Problem two took %d milliseconds", ((endTime-startTime)/100000)));
    }
}
