package Models;
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

    public Robot(String name, String type, ArrayList<Component> components, UUID serialNumber, int battery, float [] position,
    float speed, float CPUUsage, float memory){
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

    public float[] getPosition() {
        return position;
    }

    public void setPosition(float[] position) {
        this.position = position;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getCPUUsage() {
        return CPUUsage;
    }

    public void setCPUUsage(float cPUUsage) {
        CPUUsage = cPUUsage;
    }

    public float getMemory() {
        return memory;
    }

    public void setMemory(float memory) {
        this.memory = memory;
    }

    
}
