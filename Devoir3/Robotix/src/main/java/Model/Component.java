package Model;

import Model.TypeOfUsers.Supplier;

import java.util.UUID;

public class Component {
    private String name;
    private String tag;
    private String price;
    private String width;
    private String length;
    private Supplier supplier;
    private UUID serialNumber;

    public Component(String name, String tag, String price, String width, String length, Supplier supplier, UUID serialNumber) {
        this.name = name;
        this.tag = tag;
        this.price = price;
        this.width = width;
        this.length = length;
        this.supplier = supplier;
        this.serialNumber = UUID.randomUUID();
    }
}
