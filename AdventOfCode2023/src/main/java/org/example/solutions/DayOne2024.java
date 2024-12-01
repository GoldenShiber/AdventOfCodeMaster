package org.example.solutions;

import org.example.fileHandling.FileHandling;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Logger;

public class DayOne2024 {

    private static final Logger LOGGER = Logger.getLogger(DayOne2024.class.getName());

    private static final List<Integer> leftList = new ArrayList<>();
    private static final List<Integer> rightList = new ArrayList<>();
    private static final Map<Integer, Integer> rightOccureance = new HashMap<>();


    public static void solution(List<String> fileInput){
        int result = 0;
        String[] lineInformation = new String[2];
        for (String s : fileInput) {
            // we add each number in a seperate list
            lineInformation = s.split("\\s+");
            leftList.add(Integer.parseInt(lineInformation[0]));
            rightList.add(Integer.parseInt(lineInformation[1]));

        }
        // sort the lists
        Collections.sort(leftList);
        Collections.sort(rightList);

        // Calculate the distance
        for(int i = 0; i<leftList.size(); i++){
            result += Math.abs(leftList.get(i) - rightList.get(i));
        }

        LOGGER.info("The total distance in the list is: " + result);
    }

    // second solution is just a map solution...
    public static void solutionTwo(List<String> fileInput) {
        int similarityResult = 0;
        for(Integer number : rightList){
            rightOccureance.put(number,  rightOccureance.getOrDefault(number, 0) +1 );
        }
        for(Integer number : leftList){
            similarityResult += number * rightOccureance.getOrDefault(number, 0);
        }
        LOGGER.info("The similarity in the list is: " + similarityResult);
    }


    public static void main(String[] args) throws IOException {
        List<String> fileOutput;

        InputStream inputStream = DayOne2024.class.getResourceAsStream("/informationFiles/input24Day1.txt");
        //Object a = Day12024.class.getResource("/informationFiles/input24Day1.txt");
        fileOutput= FileHandling.readFromInputAndMakeList(inputStream);
        long startTime = System.nanoTime();
        //String testString = FileHandling.readFileFromFileSystem(filePath);
        solution(fileOutput);
        long endTime = System.nanoTime();
        LOGGER.info(String.format("Problem one took %d milliseconds", ((endTime-startTime)/100000)));
        startTime = System.nanoTime();
        solutionTwo(fileOutput);
        endTime = System.nanoTime();
        LOGGER.info(String.format("Problem two took %d milliseconds", ((endTime-startTime)/100000)));
    }
}