package Models;
import java.util.ArrayList;
import java.util.UUID;


public abstract class Robot {

    private String name;
    private String type;
    private ArrayList<Component> components;
    private RobotFleet fleet;
    private UUID serialNumber;
    private int battery;
    private double [] position;
    private double speed;
    private double CPUUsage;
    private double memory;

    public Robot(String name, String type, ArrayList<Component> components, UUID serialNumber, int battery, double [] position,
    double speed, double CPUUsage, double memory){
        this.name = name;
        this.type = type;
        this.components = components;
        this.serialNumber = serialNumber;
        this.battery = battery;
        this.position = position;
        this.speed = speed;
        this.CPUUsage = CPUUsage;
        this.memory = memory;
    }

    public abstract void robotData();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Component> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<Component> components) {
        this.components = components;
    }

    public UUID getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(UUID serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public double[] getPosition() {
        return position;
    }

    public void setPosition(double[] position) {
        this.position = position;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getCPUUsage() {
        return CPUUsage;
    }

    public void setCPUUsage(double cPUUsage) {
        CPUUsage = cPUUsage;
    }

    public double getMemory() {
        return memory;
    }

    public void setMemory(double memory) {
        this.memory = memory;
    }

    
}
