package Views;

import java.util.ArrayList;
import java.util.Scanner;
import Database.Database;
import Models.Activity;
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

            System.out.printf("%-30s %s%n","["+ i +"] "+ user.getActivities().get(i).getName(), "["+ i +"] " + Database.getAllActivities().get(i).getName());
            count ++;
        }

        int bigger = Math.max(user.getActivities().size(), Available.size());
        if(user.getActivities().size() > Available.size()){
            for (int i = count; i < bigger; i++){
                System.out.printf("%-30s %s%n","["+ i +"] "+ user.getActivities().get(i).getName(), "" );
            }
        }else{
            for (int i = count; i < bigger; i++){
                System.out.printf("%-30s %s%n","" , "["+ i +"] " + Database.getAllActivities().get(i).getName());
            }
        }

        System.out.println("\nQue voulez-vous faire ?");
        System.out.println("[0] Ajouter une activité");
        System.out.println("[1] Modifier une activité");
        System.out.println("[2] Supprimer une activité");
        System.out.println("[3] Ajouter un interet");
        System.out.println("[4] Supprimer un interet");
        System.out.println("[5] Retour");
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
                break;
            case "1":
                //user.modifyActivity();
                break;
            case "2":
                System.out.println("Entrez le numéro de l'activité que vous voulez supprimer (Colonne de gauche)");
                Activity toDelete = new Activity();
                boolean isInt2 = true;
                while(isInt2)
                try {
                    String activityToDelete = scanner.nextLine();
                    toDelete = user.getActivities().get(Integer.parseInt(activityToDelete));
                    isInt2 = false;
                } catch (Exception e) {
                    System.out.println("Erreur: Entrez un numéro valide");
                }
                user.removeActivity(toDelete);
                break;
            case "3":
                //user.addInterest();
                break;
            case "4":
                //user.deleteInterest();
                break;
            case "5":
                Menu.displayHomePage(user);
                break;
            default:
                break;
        }
    }

}
