import java.util.ArrayList;
import java.util.UUID;

public class Robot {

    private String name;
    private String type;
    private ArrayList<Component> components;
    private RobotFleet fleet;
    private UUID serialNumber;
    private int battery;
    private float [] position;
    private float speed;
    private float CPUUsage;
    private float memory;

    public Robot(String name, String type, ArrayList<Component> components,RobotFleet robotFleet, UUID serialNumber, int battery, float [] position,
    float speed, float CPUUsage, float memory){
        this.name = name;
        this.type = type;
        this.components = components;
        this.fleet = robotFleet;
        this.serialNumber = serialNumber;
        this.battery = battery;
        this.position = position;
        this.speed = speed;
        this.CPUUsage = CPUUsage;
        this.memory = memory;
    }
}
