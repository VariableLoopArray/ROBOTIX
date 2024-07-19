package Model;

import java.util.ArrayList;

public class RobotFleet {
    private ArrayList<Robot> robots;
    public RobotFleet(){
        robots = new ArrayList<Robot>();
    }

    public ArrayList<Robot> getRobots() {
        return robots;
    }

    public void setRobots(ArrayList<Robot> robots) {
        this.robots = robots;
    }
}
