package Model;

import java.util.ArrayList;

public class Task {
    private String name;
    private ArrayList<String> Instructions;

    public Task(String name, ArrayList<String> Instructions){
        this.name = name;
        this.Instructions = Instructions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getInstructions() {
        return Instructions;
    }

    public void setInstructions(ArrayList<String> instructions) {
        this.Instructions = instructions;
    }
}
