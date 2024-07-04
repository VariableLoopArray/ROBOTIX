package Controllers;
import java.util.ArrayList;
import java.util.Scanner;

import Models.Activity;
import Models.User;
import Views.ActivityMenu;

public class ActivityController {
    public static void addActivity(User user, ArrayList<Activity> availableActivities){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le numéro de l'activité que vous voulez ajouter (Colonne de droite)");
        Activity toAdd = new Activity();
        boolean isInt = true;
        while(isInt)
        try {
            String activityToAdd = scanner.nextLine();
            toAdd = availableActivities.get(Integer.parseInt(activityToAdd));
            isInt = false;
        } catch (Exception e) {
            System.out.println("Erreur: Entrez un numéro valide");
        }
        
        user.addActivity(toAdd);

    }

    public static void deleteActivity(User user){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le numéro de l'activité que vous voulez supprimer (Colonne de gauche)");

        Activity toDelete = new Activity();
        boolean isInt2 = true;
        while(isInt2){
            try {
                String activityToDelete = scanner.nextLine();
                toDelete = user.getActivities().get(Integer.parseInt(activityToDelete));
                isInt2 = false;
            } catch (Exception e) {
                System.out.println("Erreur: Entrez un numéro valide");
            }
        }
        user.removeActivity(toDelete);
    }

}