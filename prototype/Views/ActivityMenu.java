package Views;

import java.util.ArrayList;
import java.util.Scanner;


import Controllers.ActivityController;
import Controllers.UserController;
import Database.Database;
import Models.Activity;
import Models.Client;
import Models.Supplier;
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

        if (user instanceof Client){
            System.out.println("\nQue voulez-vous faire ?");
            System.out.println("[0] Ajouter une activité");
            System.out.println("[1] Modifier une activité");
            System.out.println("[2] Supprimer une activité");
            System.out.println("[3] Créer une activité");
            System.out.println("[4] Visualiser mes activités");
            System.out.println("[5] Ajouter un interet");
            System.out.println("[6] Supprimer un interet");
            System.out.println("[7] Affichier mes intérêts");
            System.out.println("[8] Retour");
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
                    ActivityController.activityData(user);
                    displayManageActivities(user);
                    break;
                case "5":         
                    UserController.addInterest(user);
                    displayManageActivities(user);
                    break;
                case "6":
                    UserController.deleteInterest(user);
                    displayManageActivities(user);
                    break;
                case "7":
                    for (String interestToShow : user.getInterests()){
                        System.out.println(interestToShow);
                    }
                    displayManageActivities(user);
                    break;
                case "8":
                    Menu.displayHomePage(user);
                    break;
                default:
                    System.out.println("Veuillez entrer une valeur valide");
                    displayManageActivities(user);
                    break;
            }
        } else if (user instanceof Supplier){
            System.out.println("\nQue voulez-vous faire ?");
            System.out.println("[0] Ajouter une activité");
            System.out.println("[1] Modifier une activité");
            System.out.println("[2] Supprimer une activité");
            System.out.println("[3] Créer une activité");
            System.out.println("[4] Visualiser mes activités");
            System.out.println("[5] Retour");
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
                    ActivityController.activityData(user);
                    displayManageActivities(user);
                    break;
                case "5":
                    Menu.displayHomePage(user);
                    break;
                default:
                    System.out.println("Veuillez entrer une valeur valide");
                    displayManageActivities(user);
                    break;
            }
        }

        
    }

}
