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

    public UUID getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(UUID orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public ArrayList<Component> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<Component> components) {
        this.components = components;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
