package Controllers;
import Models.User;
import Models.Activity;
import java.util.ArrayList;
import java.util.Scanner;

import Database.Database;

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

    public static void addInterest(User user){
        Scanner scanner = new Scanner(System.in);
        int interestCount = 0;
                    int interestToAdd;
                    ArrayList<String> availableInterests = new ArrayList<String>(Database.getInterests());
                    while(true){
                        System.out.println("Choisissez l'intérêt que vous voulez ajouter");
                        for (String interest : Database.getInterests()){
                            if (!user.getInterests().contains(interest)){
                                System.out.println(interestCount + " " + interest);
                                interestCount ++;
                            }
                        }


                        interestToAdd = Integer.parseInt(scanner.nextLine());
                        availableInterests.remove(interestToAdd);
                        if (interestToAdd > interestCount || interestToAdd < 0){
                            System.out.println("Vous ne pouvez pas choisir ce numéro, svp réessayez");
                        }
                        else{
                            user.addInterest(availableInterests.get(interestToAdd));
                            break;
                        }
        }
    }

    public static void deleteInterest(User user){
        Scanner scanner = new Scanner(System.in);
        int userInterestCount = 0;
        for (String userInterest : user.getInterests()){
            System.out.println(userInterestCount + " " + userInterest);
            userInterestCount++;
        }
        while (true){
            System.out.println("Choisissez le numéro de l'intérêt que vous voulez enlever");
            int interestToDelete = Integer.parseInt(scanner.nextLine()); 

            if (interestToDelete > userInterestCount - 1 || interestToDelete < 0){
                System.out.println("Vous ne pouvez pas choisir ce numéro, svp réessayez");
            }
            else{
                user.deleteInterest(user.getInterests().get(interestToDelete));
                break;
            }
        }
    }
}
