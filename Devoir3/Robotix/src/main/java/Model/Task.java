package Model;

import java.util.ArrayList;

/**
 * The Task class represents the task with a name and a list of instructions.
 */
public class Task {
    /**Name of the instructions*/
    private String name;
    /**List of the instructions*/
    private ArrayList<String> Instructions;

    /**Constructor of Task with name and list of instructions*/
    public Task(String name, ArrayList<String> Instructions){
        this.name = name;
        this.Instructions = Instructions;
    }

    /**
     * Gets the name of the task.
     * @return the name of the task
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the task.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the list of instructions.
     * @return the name of the task
     */
    public ArrayList<String> getInstructions() {
        return Instructions;
    }

    /**
     * Sets the list of instructions.
     */
    public void setInstructions(ArrayList<String> instructions) {
        this.Instructions = instructions;
    }
}
