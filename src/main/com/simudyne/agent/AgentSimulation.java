package main.com.simudyne.agent;

import java.util.List;

/**
 * Created by Vlad on 3/30/2016.
 */
public class AgentSimulation {
    List<Agent> agentList;
    private int simulationDuration;

    public AgentSimulation (int simulationDuration) {
        this.simulationDuration = simulationDuration;
    }

    public List<Agent> setup(List<List> initialData) throws AgentInitializationException {
        List<String> agentAttributes;
        Agent agent;

        for (int i = 0; i < initialData.size(); i++) {
            agentAttributes = initialData.get(i);
            agent = new BreedAgent(agentAttributes);
            agentList.add(agent);
        }

        return agentList;
    }

    public void process (Agent processedAgent) {
        //The total simulation process for a single agent
        for (int i = 0; i < simulationDuration; i++) {
            processedAgent.step();
        }
    }
}
