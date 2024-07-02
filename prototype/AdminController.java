import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class AdminController {
    public static void createAccount(Menu menu, Scanner scanner){
        try{
        System.out.println("vous enregistrez comme fournisseur ou utilisateur?");
                String userType = scanner.nextLine();
                if (!userType.equals("fournisseur" ) && !userType.equals("utilisateur") ){
                    System.out.println("erreur, svp réessayer \n");
                    menu.displayLoginPage();
                }

                System.out.println("enter first name");
                String firstName = scanner.nextLine();

                System.out.println("enter last name");
                String lastName = scanner.nextLine();

                System.out.println("enter username");
                String userName = scanner.nextLine();

                System.out.println("enter password");
                String passWord = scanner.nextLine();

                UUID userID = UUID.randomUUID();

                System.out.println("enter email");
                String email = scanner.nextLine();

                System.out.println("enter company");
                String companyName = scanner.nextLine();
                
                System.out.println("enter phone number with dashes for example: 111-111-1111");
                String phoneNumber = scanner.nextLine();

                RobotFleet robotFleet = new RobotFleet();

                ArrayList<String> interests = new ArrayList<String>();

                ArrayList<Activity> activities = new ArrayList<Activity>();

                ArrayList<User> Followers = new ArrayList<User>();

                ArrayList<User> Following = new ArrayList<User>();

                if (userType.equals("fournisseur")){
                    User newUser = new User(firstName, lastName, userName, passWord, userID,
                    email, companyName, phoneNumber, robotFleet, activities,
                    Followers, Following);
                    menu.allUsers.add(newUser);
                    System.out.println("user successfully created");
                    menu.displayLoginPage();
                }
                else if (userType.equals("utilisateur")){
                    User newUser = new User(firstName, lastName, userName, passWord, userID,
                    email, companyName, phoneNumber, robotFleet, activities,
                    Followers, Following);
                    menu.allUsers.add(newUser);
                    System.out.println("user successfully created");
                    for (User element : menu.allUsers){
                        element.printUserDetails();
                    }
                    menu.displayLoginPage();
                }
    }
        catch (InputMismatchException e){
            System.out.println("wrong type of input please try again \n");
        }
}
        
    
}