package main.com.simudyne.agent;

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

    public BreedAgent(List<String> singleAgentEntry) throws AgentInitializationException{
        if (singleAgentEntry.size() != agentAttributeSize) {
            throw new AgentInitializationException("Agent couldn't be initialized.");
        } else {
            brandFactor = 0.1 + 2.8 * Math.random();
            setBreed(singleAgentEntry.get(0));
            policyId = Integer.parseInt(singleAgentEntry.get(1));
            age = Integer.parseInt(singleAgentEntry.get(2));
            socialGrade = Integer.parseInt(singleAgentEntry.get(3));
            paymentAtPurchase = Integer.parseInt(singleAgentEntry.get(4));
            attributeBrand = Float.parseFloat(singleAgentEntry.get(5));
            attributePrice = Float.parseFloat(singleAgentEntry.get(6));
            attributePromotion = Float.parseFloat(singleAgentEntry.get(7));
            setAutoRenewal(Boolean.parseBoolean(singleAgentEntry.get(8)));
            inertiaForSwitch = Integer.parseInt(singleAgentEntry.get(9));
        }
    }

    public void step () {
        //Actual computations for a single agent in one simulation step
        if (!isAutoRenewal()) {
            affinity = paymentAtPurchase / attributePrice + (2 * attributePromotion * inertiaForSwitch);
            if (BREED_C.equalsIgnoreCase(getBreed()) && (affinity < socialGrade * attributeBrand)) {
                setBreed(BREED_NC);
            } else if (BREED_NC.equalsIgnoreCase(getBreed()) && (affinity < socialGrade * attributeBrand * brandFactor)) {
                setBreed(BREED_C);
            }
        }
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public boolean isAutoRenewal() {
        return autoRenewal;
    }

    public void setAutoRenewal(boolean autoRenewal) {
        this.autoRenewal = autoRenewal;
    }
}
