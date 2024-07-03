package Models;
import java.util.ArrayList;

public class Task {
    private String name;
    private ArrayList<Action> intructions;

    public Task(String name, ArrayList<Action> intructions){
        this.name = name;
        this.intructions = intructions;
    }
}
