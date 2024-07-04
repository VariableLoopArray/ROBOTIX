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

    public static void addInterest(User user){
        try{
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
        }catch(Exception e){
            System.out.println("Erreur: Entrez un numéro valide");
        }
    }

    public static void deleteInterest(User user){
        try{
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
    
        } catch(Exception e){
            System.out.println("Erreur: Entrez un numéro valide");
        }
    }
}
