package Models;
import java.util.ArrayList;
import java.util.UUID;
public class Component {
    private String name;
    private ArrayList<String> tags;
    private float price;
    private float width;
    private float length;
    private float weight;
    private Supplier supplier;
    private UUID serialNumber;
    
    public Component(String name, ArrayList<String> tags, float price, float width, float length, float weight, Supplier supplier, UUID serialNumber){
        this.name = name;
        this.tags = tags;
        this.price = price;
        this.width = width;
        this.length = length;
        this.weight = weight;
        this.supplier = supplier;
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public UUID getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(UUID serialNumber) {
        this.serialNumber = serialNumber;
    }

    

}
