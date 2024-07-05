package Models;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Order {
    private UUID orderNumber;
    private LocalDate orderDate;
    private ArrayList<Component> components;
    private Client client;
    private Supplier supplier;
    private String status;


    public Order(UUID orderNumber, LocalDate orderDate, ArrayList<Component> components, String status, Client client, Supplier supplier){
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.components = components;
        this.client = client;
        this.supplier = supplier;
        this.status = status;
    }

    public UUID getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(UUID orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public ArrayList<Component> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<Component> components) {
        this.components = components;
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    public Supplier getSupplier() {
        return supplier;
    }
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
