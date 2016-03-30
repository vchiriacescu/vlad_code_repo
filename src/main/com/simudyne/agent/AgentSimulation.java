package main.com.simudyne.agent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlad on 3/30/2016.
 */
public class AgentSimulation {
    private int simulationDuration;
    private List<Agent> agentList;

    final private String BREED_C_GAINED_HEADER = "Breed C Gained";
    final private String BREED_C_LOST_HEADER = "Breed C Lost";
    final private String BREED_C_REGAINED_HEADER = "Breed C Regained";

    public AgentSimulation (int simulationDuration, List<List<String>> initialData) throws AgentInitializationException {
        this.simulationDuration = simulationDuration;
        setup(initialData);
    }

    private void setup(List<List<String>> initialData) throws AgentInitializationException {
        List<String> agentAttributes;
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

    private List<List<String>> outputBreedCGained() {
        List<List<String>> outputList = new ArrayList<>();
        List<String> agentAttributes;
        List<String> breedCGainedHeader = new ArrayList<>();
        breedCGainedHeader.add(BREED_C_GAINED_HEADER);
        outputList.add(breedCGainedHeader);

        List<Agent> noAutoRenewalAgentList = filterNoAutoRenewal(agentList);
        List<Agent> breedCGainedAgentList = filterBreedCGained(noAutoRenewalAgentList);
        for (int i = 0; i < breedCGainedAgentList.size(); i++) {
            agentAttributes = breedCGainedAgentList.get(i).writeAttributes();
            outputList.add(agentAttributes);
        }
        return outputList;
    }

    private List<List<String>> outputBreedCLost() {
        List<List<String>> outputList = new ArrayList<>();
        List<String> agentAttributes;
        List<String> breedCLostHeader = new ArrayList<>();
        breedCLostHeader.add(BREED_C_LOST_HEADER);
        outputList.add(breedCLostHeader);

        List<Agent> noAutoRenewalAgentList = filterNoAutoRenewal(agentList);
        List<Agent> breedCLostAgentList = filterBreedCLost(noAutoRenewalAgentList);
        for (int i = 0; i < breedCLostAgentList.size(); i++) {
            agentAttributes = breedCLostAgentList.get(i).writeAttributes();
            outputList.add(agentAttributes);
        }
        return outputList;
    }

    private List<List<String>> outputBreedCRegained() {
        List<List<String>> outputList = new ArrayList<>();
        List<String> agentAttributes;
        List<String> breedCRegainedHeader = new ArrayList<>();
        breedCRegainedHeader.add(BREED_C_REGAINED_HEADER);
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
     * Method output() constructs the output model (a List<List<String>> structure)
     * based on the total agent list and filtered lists obtained by filtering
     * for breed C gained, breed C lost and breed C regained.
     * @return List<List<String>> the output data structure
     */
    public List<List<String>> output(){
        List<String> agentData;
        List<List<String>> outputList = new ArrayList<>();
        for (int i = 0; i < agentList.size(); i++) {
            agentData = agentList.get(i).writeAttributes();
            outputList.add(agentData);
        }

        List<List<String>> breedCGainedData = outputBreedCGained();
        for (int i = 0; i < breedCGainedData.size(); i++) {
            agentData = breedCGainedData.get(i);
            outputList.add(agentData);
        }

        List<List<String>> breedCLostData = outputBreedCLost();
        for (int i = 0; i < breedCLostData.size(); i++) {
            agentData = breedCLostData.get(i);
            outputList.add(agentData);
        }

        List<List<String>> breedCRegainedData = outputBreedCRegained();
        for (int i = 0; i < breedCRegainedData.size(); i++) {
            agentData = breedCRegainedData.get(i);
            outputList.add(agentData);
        }

        return outputList;
    }
}
