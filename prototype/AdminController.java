import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class AdminController {
    public static void createAccount(){
        try{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Souhaitez-vous vous enregistrer en tant que fournisseur ou client ?");
                String userType = scanner.nextLine();
                userType = userType.toLowerCase();
                if (!userType.equals("fournisseur" ) && !userType.equals("client") ){
                    System.out.println("erreur, svp réessayer \n");
                    Menu.displayLoginPage();
                }

                System.out.println("Indiquez votre prénom");
                String firstName = scanner.nextLine();

                System.out.println("Indiquez votre nom");
                String lastName = scanner.nextLine();

                System.out.println("Indiquez votre nom d'utilisateur");
                String userName = scanner.nextLine();

                System.out.println("Indiquez votre mot de passe");
                String passWord = scanner.nextLine();

                UUID userID = UUID.randomUUID();

                System.out.println("Indiquez votre adresse courriel electronique");
                String email = scanner.nextLine();

                System.out.println("Indiquez le nom de votre entreprise");
                String companyName = scanner.nextLine();
                
                System.out.println("Veuillez entrer votre numéro de téléphone (ex: 111-111-1111)");
                String phoneNumber = scanner.nextLine();

                RobotFleet robotFleet = new RobotFleet(new ArrayList<Robot>());

                float wallet = 0;

                ArrayList<String> interests = new ArrayList<String>();
                if(userType.equals("client")){
                    System.out.println("Quelles catégories parmi les suivantes vous intéresse?(selectionnez le numéro correspondant à la catégorie en les separant par des virgules) \n");
                    for (int i = 0; i < Database.getInterests().size(); i++){
                        System.out.println( "[" + i + "]" + Database.getInterests().get(i));
                    }
                    String[] interestsArray = scanner.nextLine().split(",");
                    for (int j = 0; j < interests.size(); j++){
                        interests.add(Database.getInterests().get(Integer.parseInt(interestsArray[j])));
                    }

                }

                ArrayList<Activity> activities = new ArrayList<Activity>();

                ArrayList<User> Followers = new ArrayList<User>();

                ArrayList<User> Following = new ArrayList<User>();

                ArrayList<Order> orders = new ArrayList<Order>();

                if (userType.equals("fournisseur")){
                    Supplier newUser = new Supplier(firstName, lastName, userName, passWord, userID,
                    email, companyName, phoneNumber,wallet, robotFleet, activities,
                    Followers, Following,);
                    Database.getAllUsers().add(newUser);
                    Database.getAllSuppliers().add(newUser);
                    System.out.println("user successfully created");
                    Menu.displayLoginPage();
                }

                else if (userType.equals("utilisateur")){
                    Client newUser = new Client(firstName, lastName, userName, passWord, userID,
                    email, companyName, phoneNumber,wallet, robotFleet,interests, activities,
                    Followers, Following, orders);
                    Database.getAllUsers().add(newUser);
                    System.out.println("user successfully created");
                    Menu.displayLoginPage();
                }
    }
        catch (InputMismatchException e){
            System.out.println("wrong type of input please try again \n");
        }
}
        
    
}