package Implementation;
public class utilisateur {
    String userName;
    String password;
    String name;
    String lastName;
    String email;
    String phone;
    
    public utilisateur(String userName, String password, String name, String lastName, String email, String phone){
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;

    }


    public void validate(String name, String lastName, String email, String phone, String companyName){
        return;
    }
    public void createUser(String userName, String name, String lastName, String email, String phone){
        return;
    }
    public boolean login(String userName, String password){
        if (userName.equals(this.userName) && password.equals("password")){ // hard coded password
            System.out.println("Bienvenue de nouveau " + this.name + ". Appuyez sur 'Enter' pour continuer.");
            return true;
        }
        else{
            System.out.println("Utilisateur non trouvé. Appuyez sur 'Enter' pour continuer.");
            return false;
        }
    }
}
