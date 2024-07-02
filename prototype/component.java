import java.util.ArrayList;
import java.util.UUID;
public class Component {
    private String name;
    private ArrayList<String> tags;
    private float price;
    private float width;
    private float height;
    private float weight;
    private Supplier supplier;
    private UUID serialNumber;
    
    public Component(String name, ArrayList<String> tags, float price, float width, float height, float weight, Supplier supplier, UUID serialNumber){
        this.name = name;
        this.tags = tags;
        this.price = price;
        this.width = width;
        this.height = height;
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

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
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
