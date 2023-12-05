package org.example.fileHandling;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandling {
    public String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    /*
    public static List<String> readFromInputAndMakeList( String filePath) throws IOException {
        List<String> newList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String currentLine = reader.readLine();
        while(currentLine != null){
            newList.add(currentLine);
            currentLine = reader.readLine();
        }
        reader.close();
        return newList;
    } */

    public static List<String> readFromInputAndMakeList( InputStream inputStream) throws IOException {
        List<String> listFromInput = new ArrayList<>();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                listFromInput.add(line);
            }
        }
        return listFromInput;
    }
}
