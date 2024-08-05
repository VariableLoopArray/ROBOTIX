package Model.TypeOfUsers;

import Model.Component;
import Model.User;

import java.time.LocalDate;
import java.util.ArrayList;


public class Supplier extends User{
    private int productionCapacity;

    public Supplier(String firstName,String lastName,String username, String password, String email,
                    String companyName, String phoneNumber, int productionCapacity, boolean toggleEmail, String confirmationLink){
        super(firstName, lastName, username, password, email, companyName, phoneNumber, new ArrayList<Component>(), new ArrayList<String>(),
                new ArrayList<String>(), toggleEmail, confirmationLink);
        this.productionCapacity = productionCapacity;
    }
    public int getProductionCapacity() {
        return productionCapacity;
    }
    public void setProductionCapacity(int productionCapacity) {
        this.productionCapacity = productionCapacity;
    }

}
