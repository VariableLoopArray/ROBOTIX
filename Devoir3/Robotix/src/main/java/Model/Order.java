package Model;

import Model.TypeOfUsers.Supplier;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Order {
    private UUID orderNumber;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private ArrayList<Component> components;
    private String status;
    private UUID supplierID;
    private UUID customerID;


    public Order(LocalDate orderDate, LocalDate deliveryDate, ArrayList<Component> components, String status, UUID supplierID, UUID customerID) {
        this.orderNumber = UUID.randomUUID();
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.components = components;
        this.status = status;
        this.supplierID = supplierID;
        this.customerID = customerID;
    }
}
