package main.com.simudyne.agent;

import main.com.simudyne.util.FileUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * This is simulation application for breed agents.
 * In order to use the application:
 * 1) Make sure that the raw input data (the agent data)
 * is stored into a CSV file in the src\main\resources relative
 * to the github repo path.
 * 2) The input CSV file is named Agent_Data.csv
 *
 * NOTES:
 * 1) All the results are written in the CSV file Agent_Output.csv
 * located in the same src\main\resources folder.
 * 2) First batch of lines represent the end status of all agents
 * 3) Second batch of lines represent the end status of all agents that gained breed C
 * 4) Third batch of lines represent the end status of all agents that lost breed C
 * 5) Fourth batch of lines represent the end status of all agents that regained breed C
 */
public class AgentApp {
    final private static String relativePath = "\\src\\main\\resources\\";

    final private static String inputFileName = "Agent_Data.csv";

    final private static String outputFileName = "Agent_Output.csv";

    final private static int simulationDuration = 15;

    public static void main(String args[]) {
        BufferedReader br = null;
        try {
            String userDirectory = System.getProperty("user.dir");
            String inputFilePath = userDirectory + relativePath + inputFileName;

            // Read the input file and obtain the input data structure
            List<String[]> inputData = FileUtils.read(inputFilePath);

            //  Instantiate a new agent simulation using the given duration (number
            //  of steps) and the obtained input data structure
            AgentSimulation simulation = new AgentSimulation(simulationDuration, inputData);

            // Run the simulation
            simulation.run();

            // Output the unfiltered and filtered lists of agents into the
            // output data structure (the same as the input, List<List<String>>)
            List<String[]> outputData = simulation.output();

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
