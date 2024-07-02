import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
public class Menu {
    ArrayList<User> allUsers;
    ArrayList<Activity> allActivities;
    public Menu(ArrayList<User> allUsers, ArrayList<Activity> allActivities){
        this.allUsers = allUsers;
        this.allActivities = allActivities;
    }
    public void displayLoginPage(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to ROBOTIX");
        System.out.println("[1] Login");
        System.out.println("[2] Create Account");
        String value = scanner.nextLine();
        switch (value) {
            case "1":
                System.out.println("Enter Username:");
                String username = scanner.nextLine();
                System.out.println("Enter Password:");
                String password = scanner.nextLine();
                LoginController.login(username,password,this.allUsers);
                break;
            case "2":
                

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
                
                System.out.println("enter phone number");
                String phoneNumber = scanner.nextLine();

                RobotFleet robotFleet = new RobotFleet();

                ArrayList<String> interests = new ArrayList<String>();

                ArrayList<Activity> activities = new ArrayList<Activity>();

                ArrayList<User> Followers = new ArrayList<User>();

                ArrayList<User> Following = new ArrayList<User>();

                User newUser = new User(firstName, lastName, userName, passWord, userID,
                email, companyName, phoneNumber, robotFleet, interests, activities,
                Followers, Following);

                allUsers.add(newUser);

                break;
            default:
                break;
        }

    }

    
}
