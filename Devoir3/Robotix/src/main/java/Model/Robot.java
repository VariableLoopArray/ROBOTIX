package Model;

import java.util.ArrayList;
import java.util.UUID;

public class Robot {
    private String name;
    private String type;
    private ArrayList<Component> components;
    //private RobotFleet robotFleet;
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
        //this.robotFleet = robotFleet;
        this.serialNumber = UUID.randomUUID();
        this.battery = battery;
        this.location = location;
        this.speed = speed;
        this.cpuUsage = cpuUsage;
        this.memory = memory;
    }

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

//    public RobotFleet getRobotFleet() {
//        return robotFleet;
//    }
//
//    public void setRobotFleet(RobotFleet robotFleet) {
//        this.robotFleet = robotFleet;
//    }

    public UUID getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(UUID serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public float[] getLocation() {
        return location;
    }

    public void setLocation(float[] location) {
        this.location = location;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(float cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public float getMemory() {
        return memory;
    }

    public void setMemory(float memory) {
        this.memory = memory;
    }
}
