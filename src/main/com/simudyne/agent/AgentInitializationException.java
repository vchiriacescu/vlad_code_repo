package main.com.simudyne.agent;

/**
 * Created by Vlad on 3/30/2016.
 */
public class AgentInitializationException extends Exception {
    public AgentInitializationException(String message)
    {
        super(message);
    }
    public AgentInitializationException(String message, Throwable exception)
    {
        super(message, exception);
    }
}
