package org.example.solutions;

import org.example.fileHandling.FileHandling;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DayThree2023 {
    private static final Logger logger = Logger.getLogger(DayThree2023.class.getName());

    private static List<Integer> xCoordinates;
    private static int currentYIndex;
    private static int fileSum;

    private static List<String> fileInput;

    // Utilize border of min and max size when analyzing the text.

    private static void findWord(String line){
        // Add a flag to enable or disable "advanced gear (* multiplication settings)
        int currentXIndex = 0;
        while(currentXIndex < line.length()){
            char character = line.charAt(currentXIndex);
            if(Character.isDigit(character)){
                xCoordinates.add(currentXIndex);
            }
            if(!xCoordinates.isEmpty() && !Character.isDigit(character)){
                validateAndSum(line.substring(xCoordinates.getFirst(), currentXIndex));
               }
            currentXIndex++;
        }
        if( !xCoordinates.isEmpty()){
            validateAndSum(line.substring(xCoordinates.getFirst(), currentXIndex));
        }
    }

    private static void validateAndSum(String line){
        for(int coordinate : xCoordinates ){
            if(isNumberValid(coordinate, currentYIndex)){
                fileSum += Integer.parseInt(line);
                xCoordinates = new ArrayList<>();
                break;
            }
        }
        xCoordinates = new ArrayList<>();
    }
    private static void calculateLineSum(){
        currentYIndex= 0;
        for(String line : fileInput){
            findWord(line);
            currentYIndex++;
        }
        logger.info(String.format("The sum of the lines are: %d", fileSum));
    }

    private static boolean isNumberValid(int xValue, int yValue){
        for(int i = Math.max(0, xValue-1); i < Math.min(xValue +2, fileInput.getFirst().length()); i++){
            for(int j = Math.max(0, yValue-1); j < Math.min(yValue +2, fileInput.size()); j++){
                char character = fileInput.get(j).charAt(i);
                if(!Character.isDigit(character) && character != '.' ){
                    return true;
                }
            }
        }
        return  false;
    }

    public static void main(String[] args) throws IOException {

        xCoordinates = new ArrayList<>();
        InputStream inputStream = DayThree2023.class.getResourceAsStream("/informationFiles/input23Day3.txt");
        //InputStream inputStream = Day1.class.getResourceAsStream("/informationFiles/input23Day3Test.txt");
        fileInput = FileHandling.readFromInputAndMakeList(inputStream);
        long startTime = System.nanoTime();
        long endTime = System.nanoTime();
        //findWord(fileInput.getFirst());
        calculateLineSum();
        logger.info(String.format("Problem one took %d milliseconds", ((endTime-startTime)/100000)));
        startTime = System.nanoTime();
        endTime = System.nanoTime();
        logger.info(String.format("Problem two took %d milliseconds", ((endTime-startTime)/100000)));
    }
    // 517866 too low
}
