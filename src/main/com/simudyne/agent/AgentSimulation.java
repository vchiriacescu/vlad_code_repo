package main.com.simudyne.agent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlad on 3/30/2016.
 */
public class AgentSimulation {
    private int simulationDuration;
    private List<Agent> agentList;

    final private String BREED_C_GAINED_HEADER = "Agents that gained Breed C";
    final private String BREED_C_LOST_HEADER = "Agents that lost Breed C";
    final private String BREED_C_REGAINED_HEADER = "Agents that regained Breed C";

    public AgentSimulation (int simulationDuration, List<String[]> initialData) throws AgentInitializationException {
        this.simulationDuration = simulationDuration;
        setup(initialData);
    }

    private void setup(List<String[]> initialData) throws AgentInitializationException {
        String[] agentAttributes;
        Agent agent;
        List<Agent> agentList = new ArrayList<>();
        for (int i = 0; i < initialData.size(); i++) {
            agentAttributes = initialData.get(i);
            agent = new BreedAgent(agentAttributes);
            agentList.add(agent);
        }

        this.agentList = agentList;
    }

    /**
     * Method run() executes the simulation process for a number
     * of steps that is specified by simulationDuration field,
     * ie. the number of simulated years of breed switching.
     */
    public void run() {
        //The entire simulation process
        for (int i = 0; i < simulationDuration; i++) {
            step();
        }
    }

    /**
     * Method step() defines the computations done in one simulation
     * step for all the agents (a simulation step is a simulated year
     * of potential breed switching)
     *
     */
    public void step() {
        //The step computation for all agents
        for (int i = 0; i < agentList.size(); i++) {
            agentList.get(i).step();
        }
    }

    /**
     * Method filter() selects those agents that don't have auto renewal
     * @return List<Agent> the filtered list of agents
     */
    private List<Agent> filterNoAutoRenewal(List<Agent> toBeFilteredAgentList) {
        List<Agent> outputAgentList = new ArrayList<>();
        for (int i = 0; i < agentList.size(); i++) {
            BreedAgent agent = (BreedAgent) agentList.get(i);
            if (!agent.isAutoRenewal()) {
                outputAgentList.add(agent);
            }
        }
        return outputAgentList;
    }


    /**
     * Method filter() selects those agents that gained breed C
     * @return List<Agent> the filtered list of agents
     */
    private List<Agent> filterBreedCGained(List<Agent> toBeFilteredAgentList) {
        List<Agent> outputAgentList = new ArrayList<>();
        for (int i = 0; i < toBeFilteredAgentList.size(); i++) {
            BreedAgent agent = (BreedAgent) toBeFilteredAgentList.get(i);
            if (!agent.isBreedCGained()) {
                outputAgentList.add(agent);
            }
        }
        return outputAgentList;
    }


    /**
     * Method filter() selects those agents that lost breed C
     * @return List<Agent> the filtered list of agents
     */
    private List<Agent> filterBreedCLost(List<Agent> toBeFilteredAgentList) {
        List<Agent> outputAgentList = new ArrayList<>();
        for (int i = 0; i < toBeFilteredAgentList.size(); i++) {
            BreedAgent agent = (BreedAgent) toBeFilteredAgentList.get(i);
            if (!agent.isBreedCLost()) {
                outputAgentList.add(agent);
            }
        }
        return outputAgentList;
    }


    /**
     * Method filter() selects those agents that lost breed C and then regained it
     * @return List<Agent> the filtered list of agents
     */
    private List<Agent> filterBreedCRegained(List<Agent> toBeFilteredAgentList) {
        List<Agent> outputAgentList = new ArrayList<>();
        for (int i = 0; i < toBeFilteredAgentList.size(); i++) {
            BreedAgent agent = (BreedAgent) toBeFilteredAgentList.get(i);
            if (!agent.isBreedCRegained()) {
                outputAgentList.add(agent);
            }
        }
        return outputAgentList;
    }

    /**
     * Adds to the output list the agents that gained breed C
     * @param outputList the initial output list
     * @return the resulting output list
     */
    private List<String[]> addBreedCGained(List<String[]> outputList) {
        String[] agentAttributes;
        String[] breedCGainedHeader = new String[1];
        breedCGainedHeader[0] = BREED_C_GAINED_HEADER;
        outputList.add(breedCGainedHeader);

        List<Agent> noAutoRenewalAgentList = filterNoAutoRenewal(agentList);
        List<Agent> breedCGainedAgentList = filterBreedCGained(noAutoRenewalAgentList);
        for (int i = 0; i < breedCGainedAgentList.size(); i++) {
            agentAttributes = breedCGainedAgentList.get(i).writeAttributes();
            outputList.add(agentAttributes);
        }
        return outputList;
    }

    /**
     * Adds to the output list the agents that lost breed C
     * @param outputList the initial output list
     * @return the resulting output list
     */
    private List<String[]> addBreedCLost(List<String[]> outputList) {
        String[] agentAttributes;
        String[] breedCLostHeader = new String[1];
        breedCLostHeader[0] = BREED_C_LOST_HEADER;
        outputList.add(breedCLostHeader);

        List<Agent> noAutoRenewalAgentList = filterNoAutoRenewal(agentList);
        List<Agent> breedCLostAgentList = filterBreedCLost(noAutoRenewalAgentList);
        for (int i = 0; i < breedCLostAgentList.size(); i++) {
            agentAttributes = breedCLostAgentList.get(i).writeAttributes();
            outputList.add(agentAttributes);
        }
        return outputList;
    }

    /**
     * Adds to the output list the agents that regained breed C
     * @param outputList the initial output list
     * @return the resulting output list
     */
    private List<String[]> addBreedCRegained(List<String[]> outputList) {
        String[] agentAttributes;
        String[] breedCRegainedHeader = new String[1];
        breedCRegainedHeader[0] = BREED_C_REGAINED_HEADER;
        outputList.add(breedCRegainedHeader);

        List<Agent> noAutoRenewalAgentList = filterNoAutoRenewal(agentList);
        List<Agent> breedCRegainedAgentList = filterBreedCRegained(noAutoRenewalAgentList);
        for (int i = 0; i < breedCRegainedAgentList.size(); i++) {
            agentAttributes = breedCRegainedAgentList.get(i).writeAttributes();
            outputList.add(agentAttributes);
        }
        return outputList;
    }

    /**
     * Method that constructs the output model (a List<List<String>> structure)
     * based on the total agent list and filtered lists obtained by filtering
     * for breed C gained, breed C lost and breed C regained.
     * @return List<List<String>> the output data structure
     */
    public List<String[]> output(){
        String[] agentData;
        List<String[]> outputList = new ArrayList<>();
        String[] endOfRunAgentStatus = new String[1];
        endOfRunAgentStatus[0] = "All agents at the end of the simulation";
        outputList.add(endOfRunAgentStatus);
        for (int i = 0; i < agentList.size(); i++) {
            agentData = agentList.get(i).writeAttributes();
            outputList.add(agentData);
        }

        outputList = addBreedCGained(outputList);
        outputList = addBreedCLost(outputList);
        outputList = addBreedCRegained(outputList);

        return outputList;
    }
}
