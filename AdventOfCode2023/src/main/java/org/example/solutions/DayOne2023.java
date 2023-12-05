package org.example.solutions;

import org.example.fileHandling.FileHandling;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Logger;

public class DayOne2023 {
    private static final Logger logger = Logger.getLogger(DayOne2023.class.getName());
    private static List<String> fileOutput;
    private static String currentNumber;
    private static String convertedLine;
    private static boolean firstChar;
    private static String encodedValue;
    private static List<Integer> valueList;
    private static int numberIndex;
    private static final Map<String, String> numberStrings = new HashMap<>() {{
        put("one", "o1e");
        put("two", "2o");
        put("three", "3e");
        put("four", "4");
        put("five", "5e");
        put("six", "6");
        put("seven", "7");
        put("eight", "e8t");
        put("nine", "9e");
    }};

    private static final List<String> numberList = List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
    private static String convertNumbers(String line){
        convertedLine = line.toLowerCase();
            numberList.forEach(number -> {
                int index = -1;
                while ((index = convertedLine.indexOf(number, index + 1)) != -1) {
                    convertedLine = convertedLine.substring(0, index+1) + numberStrings.get(number)
                            + convertedLine.substring(index);
                    index += numberStrings.get(number).length()+1;
                }
                numberIndex = convertedLine.indexOf(number);
            });
        return convertedLine;
    }


    // Need to find all indexes of a word in String

    private static List<String> convertToNumberList(){
        List<String> convertedList = new ArrayList<>();
        fileOutput.forEach(line -> convertedList.add(convertNumbers(line)));
        return  convertedList;
    }

    public static void solution(List<String> encodedList){
        valueList = new ArrayList<>();
        encodedValue ="";
        firstChar = false;

        encodedList.forEach(line -> {
            for(int i = 0; i<line.length(); i++){
                if(Character.isDigit(line.charAt(i))) {
                    currentNumber = String.valueOf(line.charAt(i));
                    if(!firstChar){
                        firstChar = true;
                        encodedValue += currentNumber;
                    }
                }
            }
            encodedValue += String.valueOf(currentNumber);
            valueList.add(Integer.valueOf(encodedValue));
            firstChar = false;
            currentNumber = "";
            encodedValue = "";
        });
        logger.info(String.format("The sum of the encoded values is: %d", valueList.stream().mapToInt(Integer::intValue).sum()));

    }

    public static void main(String[] args) throws IOException {
        //InputStream inputStream = Day1.class.getResourceAsStream("/informationFiles/input23Day1Test.txt");
        InputStream inputStream = Day1.class.getResourceAsStream("/informationFiles/input23Day1.txt");
        fileOutput= FileHandling.readFromInputAndMakeList(inputStream);
        long startTime = System.nanoTime();
        solution(fileOutput);
        long endTime = System.nanoTime();
        logger.info(String.format("Problem one took %d milliseconds", ((endTime-startTime)/100000)));
        startTime = System.nanoTime();
        solution(convertToNumberList());
        endTime = System.nanoTime();
        logger.info(String.format("Problem two took %d milliseconds", ((endTime-startTime)/100000)));
    }
}
