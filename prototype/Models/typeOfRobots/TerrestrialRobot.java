package Models.typeOfRobots;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import Models.Component;
import Models.Robot;

public class TerrestrialRobot extends Robot{
    private double range;
    public TerrestrialRobot(String name, String type, ArrayList<Component> components, UUID serialNumber,
     int battery, double [] position, double speed, double CPUUsage, double memory, double range) {

        super(name, type, components, serialNumber, battery, position, speed, CPUUsage, memory);
        this.range = range;
    }

    @Override
    public void robotData(){
        System.out.println("\nNom " + this.getName());
        System.out.println("Type " + this.getType());
        System.out.println("Les composants " + Arrays.toString(this.getPosition()));
        System.out.println("Numéro de série " + this.getSerialNumber());
        System.out.println("Battery " + this.getBattery());
        System.out.println("Position " + Arrays.toString(this.getPosition()));
        System.out.println("Speed " + this.getSpeed());
        System.out.println("L'utilisation de CPU " + this.getCPUUsage());
        System.out.println("Utilisation de mémoire " + this.getMemory());
        System.out.println("Portée " + this.range + "\n");
    }
}