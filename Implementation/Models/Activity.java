package Models;
import java.time.LocalDate;
import java.util.ArrayList;

public class Activity {
    private String name;
    private Robot robot;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private String interests;   
    private int points;
    private User creator;
    private ArrayList<Task> tasks;
    public Object getTasks;

    public Activity(String name, Robot robot, LocalDate startDate, LocalDate endDate, String status, String interests, int points, User creator, ArrayList<Task> tasks){
        this.name = name;
        this.robot = robot;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
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
