package main.com.simudyne.agent;

import main.com.simudyne.util.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AgentApp {
    final private static String relativePath = "src\\main\\resources";

    final private static String inputFileName = "Agent_Data.csv";

    final private static String outputFileName = "Agent_Output.csv";

    final private static int simulationDuration = 15;

    public static void main(String args[]) {
        BufferedReader br = null;
        try {
            String userDirectory = System.getProperty("user.dir");
            String inputFilePath = userDirectory + relativePath + inputFileName;

            // Read the input file and obtain the input data structure
            List<List<String>> inputData = FileUtils.read(inputFilePath);

            //  Instantiate a new agent simulation using the given duration (number
            //  of steps) and the obtained input data structure

            AgentSimulation simulation = new AgentSimulation(simulationDuration, inputData);

            // Run the simulation
            simulation.run();

            // Output the unfiltered and filtered lists of agents into the
            // output data structure (the same as the input, List<List<String>>)
            List<List<String>> outputData = simulation.output();

            String outputFilePath = userDirectory + relativePath + outputFileName;

            // Write the output data structure to the output file
            FileUtils.write(outputData, outputFilePath);

        } catch (AgentInitializationException ex) {
            System.out.println (ex.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
