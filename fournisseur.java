public class fournisseur {
    double capacity;
    public fournisseur(String name, String addresse, String email, String phone, double capacity){
        validate( name,  addresse,  email,  phone,  capacity);
        this.capacity = capacity;
    }

    public static void validate(String name, String addresse, String email, String phone, double capacity){
        return;
    }
}
