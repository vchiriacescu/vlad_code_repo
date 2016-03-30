package main.com.simudyne.agent;

import java.util.ArrayList;
import java.util.List;

public class BreedAgent extends Agent {

    private final static String BREED_C = "Breed_C";
    private final static String BREED_NC = "Breed_NC";

    private String breed;
    private int policyId;
    private int age;
    private int socialGrade;
    private int paymentAtPurchase;
    private float attributeBrand;
    private float attributePrice;
    private float attributePromotion;
    private boolean autoRenewal;
    private int inertiaForSwitch;
    private int agentAttributeSize = 10;

    private int simulationDuration = 15;

    private float affinity;
    private double brandFactor;

    private boolean breedCGained;
    private boolean breedCLost;
    private boolean breedCRegained;

    public BreedAgent(List<String> singleAgentEntry) throws AgentInitializationException{
        if (singleAgentEntry.size() != agentAttributeSize) {
            throw new AgentInitializationException("Agent couldn't be initialized.");
        } else {
            brandFactor = 0.1 + 2.8 * Math.random();
            breed = singleAgentEntry.get(0);
            policyId = Integer.parseInt(singleAgentEntry.get(1));
            age = Integer.parseInt(singleAgentEntry.get(2));
            socialGrade = Integer.parseInt(singleAgentEntry.get(3));
            paymentAtPurchase = Integer.parseInt(singleAgentEntry.get(4));
            attributeBrand = Float.parseFloat(singleAgentEntry.get(5));
            attributePrice = Float.parseFloat(singleAgentEntry.get(6));
            attributePromotion = Float.parseFloat(singleAgentEntry.get(7));
            autoRenewal = Boolean.parseBoolean(singleAgentEntry.get(8));
            inertiaForSwitch = Integer.parseInt(singleAgentEntry.get(9));
        }
    }

    public void step () {
        //Actual computations for a single agent in one simulation step
        age++;
        if (!isAutoRenewal()) {
            affinity = paymentAtPurchase / attributePrice + (2 * attributePromotion * inertiaForSwitch);
            if (BREED_C.equalsIgnoreCase(breed) && (affinity < socialGrade * attributeBrand)) {
                breed = BREED_NC;
                breedCLost = true;
            } else if (BREED_NC.equalsIgnoreCase(breed) && (affinity < socialGrade * attributeBrand * brandFactor)) {
                breed = BREED_C;
                breedCGained = true;
                if (breedCLost) breedCRegained = true;
            }
        }
    }

    public List<String> writeAttributes() {
        List<String> attributes = new ArrayList<>();
        attributes.add(breed);
        attributes.add(String.valueOf(policyId));
        attributes.add(String.valueOf(age));
        attributes.add(String.valueOf(socialGrade));
        attributes.add(String.valueOf(paymentAtPurchase));
        attributes.add(String.valueOf(attributeBrand));
        attributes.add(String.valueOf(attributePrice));
        attributes.add(String.valueOf(attributePromotion));
        attributes.add(String.valueOf(autoRenewal));
        attributes.add(String.valueOf(inertiaForSwitch));

        return attributes;
    }

    public boolean isAutoRenewal() {
        return autoRenewal;
    }

    public boolean isBreedCGained() {
        return breedCGained;
    }

    public boolean isBreedCLost() {
        return breedCLost;
    }

    public boolean isBreedCRegained() {
        return breedCRegained;
    }
}
