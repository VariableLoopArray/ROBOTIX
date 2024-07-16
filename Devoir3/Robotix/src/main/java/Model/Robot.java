package Model;

import java.util.ArrayList;
import java.util.UUID;

public class Robot {
    private String name;
    private String type;
    private ArrayList<Component> components;
    private RobotFleet robotFleet;
    private UUID serialNumber;
    private String battery;
    private float[] location;
    private float speed;
    private float cpuUsage;
    private float memory;

    public Robot(String name, String type, ArrayList<Component> components, RobotFleet robotFleet, UUID serialNumber, String battery, float[] location, float speed, float cpuUsage, float memory) {
        this.name = name;
        this.type = type;
        this.components = components;
        this.robotFleet = robotFleet;
        this.serialNumber = UUID.randomUUID();
        this.battery = battery;
        this.location = location;
        this.speed = speed;
        this.cpuUsage = cpuUsage;
        this.memory = memory;
    }
}
