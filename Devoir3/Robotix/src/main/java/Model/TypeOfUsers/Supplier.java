package Model.TypeOfUsers;

import Model.Component;
import Model.User;

import java.util.ArrayList;


public class Supplier extends User{
    private int productionCapacity;
    private ArrayList<Component> storage;

    public Supplier(String firstName,String lastName,String username, String password, String email,
                    String companyName, String phoneNumber, int productionCapacity, ArrayList<Component> storage){
        super(firstName, lastName, username, password, email, companyName, phoneNumber);
        this.productionCapacity = productionCapacity;
        this.storage = new ArrayList<Component>();
    }

}
