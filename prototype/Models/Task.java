package Models;
import java.util.ArrayList;

public class Task {
    private String name;
    private ArrayList<Action> intructions;

    public Task(String name, ArrayList<Action> intructions){
        this.name = name;
        this.intructions = intructions;
    }
    public ArrayList<Action> getIntructions(){
        return intructions;
    }
    public void setIntructions(ArrayList<Action> intructions){
        this.intructions = intructions;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
