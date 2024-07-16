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
}
