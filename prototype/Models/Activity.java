package Models;
import java.util.ArrayList;

public class Activity {
    private String name;
    private Robot robot;
    private String startDate;
    private String endDate;
    private String interests;   
    private int points;
    private User creator;
    private ArrayList<Task> tasks;
    public Object getTasks;

    public Activity(String name, Robot robot, String startDate, String endDate, String interests, int points, User creator, ArrayList<Task> tasks){
        this.name = name;
        this.robot = robot;
        this.startDate = startDate;
        this.endDate = endDate;
        this.interests = interests;
        this.points = points;
        this.creator = creator;
        this.tasks = tasks;
    }
    public Activity(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    
}
