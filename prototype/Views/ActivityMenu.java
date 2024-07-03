package Views;

import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.crypto.Data;

import Database.Database;
import Models.Activity;
import Models.Client;
import Models.User;

public class ActivityMenu {
    public static void displayManageActivities(User user){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t*** Menu Activité *** \n");
        
        ArrayList<Activity> Available = new ArrayList<Activity>();
        for (Activity activity : Database.getAllActivities()){
            if (!user.getActivities().contains(activity)){
                Available.add(activity);
            }
        }

        System.out.printf("%-30s %s%n", "Mes Activités", "Les Activités Disponibles \n");
        int count = 0;
        for (int i = 0; i < Math.min(user.getActivities().size(),Available.size()); i++){

            System.out.printf("%-30s %s%n","["+ i +"] "+ user.getActivities().get(i).getName(), "["+ i +"] " +
           Available.get(i).getName());
            count ++;
        }

        int bigger = Math.max(user.getActivities().size(), Available.size());
        if(user.getActivities().size() > Available.size()){
            for (int i = count; i < bigger; i++){
                System.out.printf("%-30s %s%n","["+ i +"] "+ user.getActivities().get(i).getName(), "" );
            }
        }else{
            for (int i = count; i < bigger; i++){
                System.out.printf("%-30s %s%n","" , "["+ i +"] " + Available.get(i).getName());
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
                System.out.println("Entrez le numéro de l'activité que vous voulez ajouter (Colonne de droite)");
                Activity toAdd = new Activity();
                boolean isInt = true;
                while(isInt)
                try {
                    String activityToAdd = scanner.nextLine();
                    toAdd = Available.get(Integer.parseInt(activityToAdd));
                    isInt = false;
                } catch (Exception e) {
                    System.out.println("Erreur: Entrez un numéro valide");
                }
                
                user.addActivity(toAdd);
                displayManageActivities(user);
                break;

            case "1":
                System.out.println("Entrez le numéro de l'activité que vous voulez modifier (Seul créateur de l'activité)");


                break;
            case "2":

                
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
                displayManageActivities(user);
                break;
            case "3":                


                break;
            case "4":
                
                int interestCount = 0;
                int interestToAdd;
                while(true){
                    System.out.println("Choisissez l'intérêt que vous voulez ajouter");
                    for (String interest : Database.getInterests()){
                        if (!user.getInterests().contains(interest)){
                            System.out.println(interestCount + " " + interest);
                            interestCount ++;
                        }
                    }

                    interestToAdd = Integer.parseInt(scanner.nextLine());
                    if (interestToAdd > interestCount - 1 || interestToAdd < 0){
                        System.out.println("Vous ne pouvez pas choisir ce numéro, svp réessayez");
                    }
                    else{
                        user.addInterest(Database.getInterests().get(interestToAdd));
                        break;
                    }
                }
                displayManageActivities(user);
                break;
            case "5":

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
                        user.deleteInterest(Database.getInterests().get(interestToDelete));
                        break;
                    }
                }
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
    }

}
