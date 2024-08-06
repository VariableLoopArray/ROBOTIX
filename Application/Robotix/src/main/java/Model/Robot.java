package Model;

import java.util.ArrayList;
import java.util.UUID;

public class Robot {
    /**The name of the robot.*/
    private String name;
    /**Type of the robot.*/
    private String type;
    /**List of componenents of the robot*/
    private ArrayList<String> components;
    /**Serial number of the robot*/
    private UUID serialNumber;
    /**Battery type of the robot*/
    private String battery;
    /**Location of the robot in the array*/
    private float[] location;
    /**Speed of the robot*/
    private float speed;
    /**Cpu usage of the robot.*/
    private float cpuUsage;
    /**Memory of the robot*/
    private float memory;

    /**Constructor of the robot with name, type, component, serial
     * number, battery, location, speed, cpu usage and momory of the robot
     * */
    public Robot(String name, String type, ArrayList<String> components, String battery, float[] location, float speed, float cpuUsage, float memory) {
        this.name = name;
        this.type = type;
        this.components = components;
        this.serialNumber = UUID.randomUUID();
        this.battery = battery;
        this.location = location;
        this.speed = speed;
        this.cpuUsage = cpuUsage;
        this.memory = memory;
    }

    /**
     * Gets the name of the robot.
     * @return the name of the robot
     */
    public String getName() {
        return name;
    }

    /**
     *  Sets the name of the robot.
     * @return the name of the robot
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the type of the robot
     * @return the type of the robot
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the robot.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets list of components of the robot.
     * @return the list of components
     */
    public ArrayList<String> getComponents() {
        return components;
    }

    /**
     * Sets the list of components of the robot.
     */
    public void setComponents(ArrayList<String> components) {
        this.components = components;
    }

    /**
     * Gets the serial number of the robot.
     * @return the serial number of the robot
     */
    public UUID getSerialNumber() {
        return serialNumber;
    }

    /**
     * Sets the serial number of the robot.
     */
    public void setSerialNumber(UUID serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * Gets  type of battery of the robot.
     * @return  type of battery of the robot
     */
    public String getBattery() {
        return battery;
    }

    /**
     * Sets the type of battery of the robot.
     */
    public void setBattery(String battery) {
        this.battery = battery;
    }

    /**
     * Gets the location of the robot.
     * @return the location of the robot
     */
    public float[] getLocation() {
        return location;
    }

    /**
     * Sets the location of the robot.
     */
    public void setLocation(float[] location) {
        this.location = location;
    }

    /**
     * Gets the speed of the robot.
     * @return the speed of the robot
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Sets the speed of the robot.
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * Gets the CPU usage of the robot.
     * @return the CPU usage of the robot
     */
    public float getCpuUsage() {
        return cpuUsage;
    }

    /**
     * Sets the CPU usage of the robot.
     */
    public void setCpuUsage(float cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    /**
     * Gets the memory of the robot.
     * @return the memory of the robot
     */
    public float getMemory() {
        return memory;
    }

    /**
     * Sets the memory of the robot.
     */
    public void setMemory(float memory) {
        this.memory = memory;
    }



}
