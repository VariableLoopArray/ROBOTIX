package Model;

import java.util.ArrayList;

public class Task {
    private String name;
    private ArrayList<String> Instructions;

    public Task(String name, String Instructions){
        this.name = name;
        this.Instructions = new ArrayList<String>();
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
        Instructions = instructions;
    }
}
