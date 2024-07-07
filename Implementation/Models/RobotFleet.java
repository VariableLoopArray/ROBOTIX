package Models;

import java.util.ArrayList;
public class RobotFleet {
    private ArrayList<Robot> robots;

    public RobotFleet(ArrayList<Robot> robots) {
        this.robots = robots;
    }

    public void add(Robot robot){
        this.robots.add(robot);
    }

    public ArrayList<Robot> getRobots() {
        return robots;
    }

    public void setRobots(ArrayList<Robot> robots) {
        this.robots = robots;
    }

    
}
