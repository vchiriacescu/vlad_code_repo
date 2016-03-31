package main.com.simudyne.agent;

public class BreedAgent extends Agent {

    private final static String BREED_C = "Breed_C";
    private final static String BREED_NC = "Breed_NC";

    private String breed;
    private long policyId;
    private int age;
    private int socialGrade;
    private int paymentAtPurchase;
    private float attributeBrand;
    private float attributePrice;
    private float attributePromotion;
    private boolean autoRenewal;
    private int inertiaForSwitch;
    private int agentAttributeSize = 10;

    private float affinity;
    private double brandFactor;

    private boolean breedCGained;
    private boolean breedCLost;
    private boolean breedCRegained;

    /**
     * Constructor that performs BreedAgent initialization given
     * the input data structure
     * @param singleAgentEntry a String array representing the agent attributes
     * @throws AgentInitializationException
     */
    public BreedAgent(String[] singleAgentEntry) throws AgentInitializationException{
        if (singleAgentEntry.length != agentAttributeSize) {
            new AgentInitializationException("Agent couldn't be initialized!");
        } else {
            try {
                brandFactor = 0.1 + 2.8 * Math.random();
                breed = singleAgentEntry[0];
                policyId = Long.parseLong(singleAgentEntry[1]);
                age = Integer.parseInt(singleAgentEntry[2]);
                socialGrade = Integer.parseInt(singleAgentEntry[3]);
                paymentAtPurchase = Integer.parseInt(singleAgentEntry[4]);
                attributeBrand = Float.parseFloat(singleAgentEntry[5]);
                attributePrice = Float.parseFloat(singleAgentEntry[6]);
                attributePromotion = Float.parseFloat(singleAgentEntry[7]);
                autoRenewal = Boolean.parseBoolean(singleAgentEntry[8]);
                inertiaForSwitch = Integer.parseInt(singleAgentEntry[9]);
            } catch (Exception ex) {
                throw new AgentInitializationException(ex.getMessage(), ex);
            }
        }
    }

    /**
     * Method that performs the agent computations for a single simulation step
     */
    public void step () {
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

    /**
     * Method that writes the agent attributes into a String array
     * @return the String array containing the agent attributes
     */
    public String[] writeAttributes() {
        String[] attributes = new String[agentAttributeSize];
        attributes[0] = breed;
        attributes[1] = String.valueOf(policyId);
        attributes[2] = String.valueOf(age);
        attributes[3] = String.valueOf(socialGrade);
        attributes[4] = String.valueOf(paymentAtPurchase);
        attributes[5] = String.valueOf(attributeBrand);
        attributes[6] = String.valueOf(attributePrice);
        attributes[7] = String.valueOf(attributePromotion);
        attributes[8] = String.valueOf(autoRenewal);
        attributes[9] = String.valueOf(inertiaForSwitch);

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
