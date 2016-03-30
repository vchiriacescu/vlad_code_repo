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
            String inputFilePath = System.getProperty("user.dir") + relativePath + inputFileName;
            List<List> initialData = FileUtils.read(inputFilePath);
            AgentSimulation simulation = new AgentSimulation(simulationDuration);
            List<Agent> agentList = simulation.setup(initialData);
            List<Object> outputAgentList = new ArrayList<>();
            for (int i = 0; i < agentList.size(); i++) {
                simulation.process(agentList.get(i));
                BreedAgent processedAgent = (BreedAgent) agentList.get(i);
                if (processedAgent.isAutoRenewal()) {
                    outputAgentList.add(processedAgent);
                }
            }

            String outputFilePath = System.getProperty("user.dir") + relativePath + outputFileName;
            FileUtils.write(outputAgentList, outputFilePath);

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
