package org.example.solutions;

import org.example.fileHandling.FileHandling;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Logger;

import static org.apache.commons.lang3.math.NumberUtils.isParsable;

public class Day1 {
    private static final Logger logger = Logger.getLogger(Day1.class.getName());
    private static List<String> fileOutput;
    private static int partSum;

        public static void solution() {
            List<Integer> calorieList = new ArrayList<>();
            fileOutput.forEach(line -> {
                if (isParsable(line)) {
                    partSum += Integer.parseInt(line);
                } else {
                    calorieList.add(partSum);
                    partSum = 0;
                }
            });

            logger.info(String.format("The max value of the gnomes calorie values is %d", Collections.max(calorieList)));

            Collections.sort(calorieList);
            Collections.reverse(calorieList);
            logger.info(String.format("The top 3 contributors weight is in total %d",
                    calorieList.stream().limit(3).mapToInt(Integer::intValue).sum()));
        }

    public static void main(String[] args) throws IOException {
        InputStream inputStream = Day1.class.getResourceAsStream("/informationFiles/inputDay1.txt");
        fileOutput= FileHandling.readFromInputAndMakeList(inputStream);
        long startTime = System.nanoTime();
        solution();
        long endTime = System.nanoTime();
        logger.info(String.format("The problem took %d milliseconds", ((endTime-startTime)/100000)));

    }
}
