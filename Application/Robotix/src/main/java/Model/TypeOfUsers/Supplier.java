package Model.TypeOfUsers;

import Model.Component;
import Model.User;

import java.time.LocalDate;
import java.util.ArrayList;


/**
 * The Supplier class represents a supplier user, extending the User class.
 * It contains the information and production capacity of the supplier.
 */
public class Supplier extends User{
    //production capacity of the supplier
    private int productionCapacity;

    /**
     * Constructs a new Supplier object.
     */
    public Supplier(String firstName,String lastName,String username, String password, String email,
                    String companyName, String phoneNumber, int productionCapacity, boolean toggleEmail, String confirmationLink){
        super(firstName, lastName, username, password, email, companyName, phoneNumber, new ArrayList<Component>(), new ArrayList<String>(),
                new ArrayList<String>(), toggleEmail, confirmationLink);
        this.productionCapacity = productionCapacity;
    }
    /**
     * Gets the production capacity.
     * @return The production capacity.
     */
    public int getProductionCapacity() {
        return productionCapacity;
    }

    /**
     * Sets the production capacity.
     */
    public void setProductionCapacity(int productionCapacity) {
        this.productionCapacity = productionCapacity;
    }

}
