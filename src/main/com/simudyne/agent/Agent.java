package main.com.simudyne.agent;

import java.util.List;

public abstract class Agent {
    public abstract void step();
    public abstract List<String> writeAttributes();
}
