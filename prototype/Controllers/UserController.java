package Controllers;
import Models.User;
import Models.Activity;
import java.util.ArrayList;
import java.util.Scanner;

public class UserController {
    


    public static void manageFollowers (User user){

    }
    
    public static void manageInterests (User user){

    }

    public static void manageProfile (User user, Scanner scanner){
        System.out.println("choisissez ce que vous voulez faire\n"
        + "1. voir mes données \n2. changer mes données ");
        switch(scanner.nextLine()){
            case "1":
                user.userData();
                break;
            case "2":
                break;
            default:
                System.out.println("commande non connue");
                break;
        }
    }

    public static void manageFollowing (User user){

    }

    public static  void follow(User user){
        user.addFollower(user);
    }

    public static void unfollow(User user){
        user.unfollowFollower(user);
    }

    public static void removeFollower(User user){
        user.removeFollower(user);
    }

    public static void deleteInterest(String interest, User user){
        user.deleteInterest(interest);
    }

    public static void subscribeInterest(String interest, User user){
        user.addInterest(interest);
    }

    public static void removeActivity(Activity activity, User user){
        user.removeActivity(activity);
    }

    public static void modifyActivity(Activity activity){

    }

    public static void registerActivity(Activity activity){

    }

    public static void addActivity(Activity activity){

    }

    public static void verifyActivity(Activity activity){

    }

    public static void changePassword(String oldPass, String newPass, User user){
        user.changePassword(oldPass,newPass);
    }
}
