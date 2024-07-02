import java.util.ArrayList;

public class Activity {
    private String name;
    private Robot robot;
    private String startDate;
    private String endDate;
    private ArrayList<String> interests;   
    private int points;
    private User creator;
    private ArrayList<Task> tasks;

    public Activity(String name, Robot robot, String startDate, String endDate, ArrayList<String> interests, int points, User creator, ArrayList<Task> tasks){
        this.name = name;
        this.robot = robot;
        this.startDate = startDate;
        this.endDate = endDate;
        this.interests = interests;
        this.points = points;
        this.creator = creator;
        this.tasks = tasks;
    }

}
