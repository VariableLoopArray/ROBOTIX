package Controllers;

import Database.Database;
import java.util.Scanner;
import Models.User;
import Views.Menu;
import Models.Activity;


public class TimeController {
    
    public static void displayTimeMenu(User user){
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n\n\t\t*** Menu Temps ***\n");
        System.out.println("[0] Afficher le temps actuel");
        System.out.println("[1] Sauter d'une journée");
        System.out.println("[2] Quitter");
        String value = scanner.nextLine();
        switch (value) {
            case "0":
                System.out.println("Le temps actuel est: " + Database.getTime());
                displayTimeMenu(user);
                break;
            case "1":
                Database.setTime(Database.getTime().plusDays(1));
                System.out.println("Vous avez sauté une journée");
                for (User u : Database.getAllUsers()){
                    for (Activity activity : u.getActivities()){
                        if (activity.getEndDate().isBefore(Database.getTime())){
                            activity.setStatus("Terminée");
                        } else if (activity.getStartDate().isBefore(Database.getTime()) && activity.getEndDate().isAfter(Database.getTime())) {
                            activity.setStatus("En cours");
                        } else {
                            activity.setStatus("Non débutée");
                        }
                    }
                }
                displayTimeMenu(user);
                break;
            case "2":
                Menu.displayHomePage(user);
                break;
            default:
                System.out.println("Entrée invalide");
                displayTimeMenu(user);
                break;
        }    
    }
}
