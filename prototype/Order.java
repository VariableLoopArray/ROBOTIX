import java.util.ArrayList;
import java.util.UUID;

public class Order {
    private UUID orderNumber;
    private String orderDate;
    private ArrayList<Component> components;
    private String status;

    public Order(UUID orderNumber, String orderDate, ArrayList<Component> components, String status){
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.components = components;
    }

}
