public class utilisateur {
    String userName;
    public utilisateur(String userName, String name, String lastName, String email, String phone, String companyName){
        validate(name, lastName, email, phone, companyName);
        this.userName = userName;
    }

    public void validate(String name, String lastName, String email, String phone, String companyName){
        return;
    }
}
