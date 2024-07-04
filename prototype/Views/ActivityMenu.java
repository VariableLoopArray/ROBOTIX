package Views;

import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.crypto.Data;

import Controllers.ActivityController;
import Controllers.UserController;
import Database.Database;
import Models.Activity;
import Models.Client;
import Models.User;

public class ActivityMenu {
    public static void displayManageActivities(User user){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n\n\t\t*** Menu Activité *** \n");
        
        ArrayList<Activity> availableActivities = new ArrayList<Activity>();
        for (Activity activity : Database.getAllActivities()){
            if (!user.getActivities().contains(activity)){
                availableActivities.add(activity);
            }
        }

        System.out.printf("%-30s %s%n", "Mes Activités", "Les Activités Disponibles \n");
        int count = 0;
        for (int i = 0; i < Math.min(user.getActivities().size(),availableActivities.size()); i++){

            System.out.printf("%-30s %s%n","["+ i +"] "+ user.getActivities().get(i).getName(), "["+ i +"] " +
           availableActivities.get(i).getName());
            count ++;
        }

        int bigger = Math.max(user.getActivities().size(), availableActivities.size());
        if(user.getActivities().size() > availableActivities.size()){
            for (int i = count; i < bigger; i++){
                System.out.printf("%-30s %s%n","["+ i +"] "+ user.getActivities().get(i).getName(), "" );
            }
        }else{
            for (int i = count; i < bigger; i++){
                System.out.printf("%-30s %s%n","" , "["+ i +"] " + availableActivities.get(i).getName());
            }
        }

        System.out.println("\nQue voulez-vous faire ?");
        System.out.println("[0] Ajouter une activité");
        System.out.println("[1] Modifier une activité");
        System.out.println("[2] Supprimer une activité");
        System.out.println("[3] Créer une activité");
        if(user instanceof Client){
            System.out.println("[4] Ajouter un interet");
            System.out.println("[5] Supprimer un interet");
            System.out.println("[6] Affichier mes intérêts");
            System.out.println("[7] Retour");
        }else{
            System.out.println("[4] Retour");
        }
        String value = scanner.nextLine();

        switch(value){
            case "0":
                ActivityController.addActivity(user, availableActivities);
                displayManageActivities(user);
                break;

            case "1":
                ActivityController.modifyActivity(user);
                displayManageActivities(user);
                break;
            case "2":
                ActivityController.deleteActivity(user);
                displayManageActivities(user);
                break;
            case "3":                
                //ActivityController.createActivity(user);
                displayManageActivities(user);
                break;
            case "4":         
                UserController.addInterest(user);
                displayManageActivities(user);
                break;
            case "5":
                UserController.deleteInterest(user);
                displayManageActivities(user);
                break;
            case "6":
                for (String interestToShow : user.getInterests()){
                    System.out.println(interestToShow);
                }
                displayManageActivities(user);
                break;
            case "7":
                Menu.displayHomePage(user);
                break;
            default:
                break;
        }
        scanner.close();
    }

}
