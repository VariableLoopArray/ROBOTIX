package Model;

import Model.TypeOfUsers.Supplier;

import java.util.ArrayList;
import java.util.UUID;

public class Component {
    private String name;
    private ArrayList<String> tag;
    private float price;
    private float width;
    private float length;
    private float height;
    private UUID supplierID;
    private UUID serialNumber;

    public Component(String name, ArrayList<String> tag, float price, float width, float length,float height, UUID supplierID, UUID serialNumber) {
        this.name = name;
        this.tag = tag;
        this.price = price;
        this.width = width;
        this.length = length;
        this.height = height;
        this.supplierID = supplierID;
        this.serialNumber = UUID.randomUUID();
    }
    public String getName() {
        return name;
    }
    public ArrayList<String> getTag() {
        return tag;
    }
    public float getPrice() {
        return price;
    }
    public float getWidth() {
        return width;
    }
    public float getLength() {
        return length;
    }
    public float getHeight() {
        return height;
    }
    public UUID getSupplierID() {
        return supplierID;
    }
    public UUID getSerialNumber() {
        return serialNumber;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setTags(ArrayList<String> tag) {
        this.tag = tag;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public void setWidth(float width) {
        this.width = width;
    }
    public void setLength(float length) {
        this.length = length;
    }
    public void setHeight(float height) {
        this.height = height;
    }
    public void setSupplierID(UUID supplierID) {
        this.supplierID = supplierID;
    }
    public void setSerialNumber(UUID serialNumber) {
        this.serialNumber = serialNumber;
    }
}
