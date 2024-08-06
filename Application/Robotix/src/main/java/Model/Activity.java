package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

/**
 * The Activity class represents an activity.
 * It contains the name, robot, start date, end date, points, interests, creator ID, tasks, activity ID, description, and status of the activity.
 */
public class Activity {
    /**name of the activity*/
    private String name;
    /**The robot associated with the activity*/
    private String robot;
    /**Start date of the activity*/
    private LocalDate startDate;
    /**End date of the activity*/
    private LocalDate endDate;
    /**Points associated with the activity*/
    private String points;
    /**List of interest of an activity*/
    private ArrayList<String> Interests;
    /**Id of the creator associated with the activity*/
    private UUID creatorId;
    /**List of task associated with the activity*/
    private ArrayList<Task> tasks;
    /**Id of the activity*/
    private UUID activityId;
    /**Description of the activity*/
    private String description;
    /**Status of the activity*/
    private String status;

    /**
     * Constructs a new Activity object.
     */
    public Activity(String name, String robot, LocalDate startDate, LocalDate endDate, String points,
                    ArrayList<String> Interests, UUID creatorId,UUID activityId, ArrayList<Task> tasks, String description ,String status){
        this.name = name;
        this.robot = robot;
        this.startDate = startDate;
        this.endDate = endDate;
        this.points = points;
        this.Interests = Interests;
        this.creatorId = creatorId;
        this.activityId = activityId;
        this.tasks = tasks;
        this.description = description;
        this.status = status;
    }

    /**
     * Gets the status of the activity.
     * @return The status of the activity.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the activity.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the list of tasks
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }


    /**
     * Gets the ID of the activity.
     * @return The ID of the activity.
     */
    public UUID getActivityID(){
        return activityId;
    }

    /**
     * Gets the list of interests related to the activity.
     * @return The list of interests.
     */
    public ArrayList<String> getInterests() {
        return Interests;
    }


    /**
     * Gets the points of an activity.
     * @return points.
     */
    public String getPoints() {
        return points;
    }


    /**
     * Gets the end date of the activity.
     * @return The end date.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Gets the start date of the activity.
     * @return The start date.
     */
    public LocalDate getStartDate() {
        return startDate;
    }


    /**
     * Gets the robot of activity.
     * @return The robot.
     */
    public String getRobot() {
        return robot;
    }

    /**
     * Sets the robot associated with the activity.
     */
    public void setRobot(String robot) {
        this.robot = robot;
    }

    /**
     * Gets the name of the activity.
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the activity.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the activity.
     * @return The description.
     */
    public String getDescription() {
        return description;
    }



}
