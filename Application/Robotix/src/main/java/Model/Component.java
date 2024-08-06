package Model;

import Model.TypeOfUsers.Supplier;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents a component with attributes name, type, price, dimensions,
 * supplier ID, and serial number.
 */
public class Component {
    /** Name of the component */
    private String name;

    /** Type of the component */
    private ArrayList<String> type;

    /** The price of the component */
    private float price;

    /** The width of the component */
    private float width;

    /** The length of the component */
    private float length;

    /** The height of the component */
    private float height;

    /** The ID of the supplier of the component */
    private UUID supplierID;

    /** The serial number of the component */
    private UUID serialNumber;

    // Constructors of component
    public Component(String name, ArrayList<String> type, float price, float width, float length,float height, UUID supplierID, UUID serialNumber) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.width = width;
        this.length = length;
        this.height = height;
        this.supplierID = supplierID;
        this.serialNumber = UUID.randomUUID();
    }
    //getters, setters

    /**
     * Gets the name of the component.
     * @return The name of the component
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the type(s) of the component.
     * @return The type(s) of the component
     */
    public ArrayList<String> getType() {
        return type;
    }

    /**
     * Gets the price of the component.
     * @return The price of the component
     */
    public float getPrice() {
        return price;
    }

    /**
     * Gets the width of the component.
     * @return The width of the component
     */
    public float getWidth() {
        return width;
    }

    /**
     * Gets the length of the component.
     * @return The length of the component
     */
    public float getLength() {
        return length;
    }

    /**
     * Gets the height of the component.
     * @return The height of the component
     */
    public float getHeight() {
        return height;
    }

    /**
     * Gets the supplier ID of the component.
     * @return The supplier ID of the component
     */
    public UUID getSupplierID() {
        return supplierID;
    }


    /**
     * Sets the name of the component.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the type of the component.
     */
    public void setTypes(ArrayList<String> type) {
        this.type = type;
    }

    /**
     * Sets the price of the component.
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Sets the width of the component.
     */
    public void setWidth(float width) {
        this.width = width;
    }

    /**
     * Sets the length of the component.
     */
    public void setLength(float length) {
        this.length = length;
    }

    /**
     * Sets the height of the component.
     */
    public void setHeight(float height) {
        this.height = height;
    }

}