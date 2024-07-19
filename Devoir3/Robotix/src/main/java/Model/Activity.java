package Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Activity {
    private String name;
    private String robot;
    private LocalDate startDate;
    private LocalDate endDate;
    private String points;
    private ArrayList<String> Interests;
    private User creator;
    private ArrayList<Task> tasks;
    private String status;

    public Activity(String name, String robot, LocalDate startDate, LocalDate endDate, String points,
                    ArrayList<String> Interests, User creator, ArrayList<Task> tasks, String status){
        this.name = name;
        this.robot = robot;
        this.startDate = startDate;
        this.endDate = endDate;
        this.points = points;
        this.Interests = Interests;
        this.creator = creator;
        this.tasks = tasks;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public ArrayList<String> getInterests() {
        return Interests;
    }

    public void setInterests(ArrayList<String> interests) {
        Interests = interests;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getRobot() {
        return robot;
    }

    public void setRobot(String robot) {
        this.robot = robot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
