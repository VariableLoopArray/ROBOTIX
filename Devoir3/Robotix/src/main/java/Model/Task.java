package Model;

import java.util.ArrayList;

public class Task {
    private String name;
    private ArrayList<String> Instructions;

    public Task(String name, String Instructions){
        this.name = name;
        this.Instructions = new ArrayList<String>();
    }

}
