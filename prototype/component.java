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


}
