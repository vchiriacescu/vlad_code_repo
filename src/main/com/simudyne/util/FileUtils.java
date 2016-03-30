package main.com.simudyne.util;

import main.com.simudyne.agent.Agent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static List<List> read (String fileName) throws IOException {
        String currentLine;
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        while ((currentLine = br.readLine()) != null) {
            System.out.println(currentLine);
        }
        List<List> list = new ArrayList<>();
        return list;
    }

    public static void write (List<Object> dataToWrite, String fileName) {
        //Writing agent attributes to csv file
    }
}