package main.com.simudyne.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public final static String DEFAULT_SEPARATOR = ",";

    /**
     * Utility method that reads a CSV file and creates a simulation data structure
     * @param fileName the name of the CSV file where the data is read from
     * @return List<String[]> representing the simulation data structure
     * @throws IOException
     */
    public static List<String[]> read (String fileName) throws IOException {
        List<String[]> inputList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String fileLine;
        reader.readLine();
        String[] inputLine;
        while ((fileLine = reader.readLine()) != null) {
            inputLine = fileLine.split(DEFAULT_SEPARATOR);
            inputList.add(inputLine);
        }
        return inputList;
    }

    /**
     * Utility method that writes a simulation data structure into a CSV file
     * @param data the data structure that is to be written
     * @param fileName the name of the CSV file where the data is written
     */
    public static void write (List<String[]> data, String fileName) throws IOException {
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
        String fileLine;
        for (int i = 0; i < data.size(); i++) {
            fileLine = String.join(DEFAULT_SEPARATOR, data.get(i));
            writer.println(fileLine);
        }
        writer.close();
    }
}
