package Controllers;
import java.util.ArrayList;
import java.util.Scanner;

import Database.Database;
import Models.Activity;
import Models.Task;
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


    public static void modifyActivity(User user){
        try{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voici les activités dont vous êtes le créateur:");
        for (int i = 0; i < Database.getAllActivities().size(); i++){
            if(Database.getAllActivities().get(i).getCreator() == user){
                System.out.println("["+i+"] "+ Database.getAllActivities().get(i).getName());
            }
        }

        System.out.println("Entrez le numéro de l'activité que vous voulez modifier (Seul créateur de l'activité)");
        Activity toModify = new Activity();
        boolean isInt3 = true;
        while(isInt3){
            try {
                String activityToModify = scanner.nextLine();
                if(Database.getAllActivities().get(Integer.parseInt(activityToModify)).getCreator() != user){
                    System.out.println("Erreur: Vous n'êtes pas le créateur de cette activité");
                    break;
                }
                toModify = Database.getAllActivities().get(Integer.parseInt(activityToModify));
                isInt3 = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Entrez un nouveau nom pour l'activité ou \"skip\" pour ne pas modifier");
        String answers = scanner.nextLine();
        if (!answers.equals("skip")){
            toModify.setName(answers);
        }
        System.out.println("Entrez le nom d'un nouveau robot ou \"skip\" pour ne pas modifier");
        answers = scanner.nextLine();
        if (!answers.equals("skip")){
            for (int i = 0; i < user.getRobotFleet().getRobots().size(); i++){
                if(user.getRobotFleet().getRobots().get(i).getName().equals(answers)){
                    toModify.setRobot(user.getRobotFleet().getRobots().get(i));
                }
            }
        }
        System.out.println("Entrez une nouvelle date de début (format: yyyy-mm-dd) ou \"skip\" pour ne pas modifier");
        answers = scanner.nextLine();
        if (!answers.equals("skip")){
            toModify.setStartDate(answers);
        }
        System.out.println("Entrez une nouvelle date de fin (format: yyyy-mm-dd) ou \"skip\" pour ne pas modifier");
        answers = scanner.nextLine();
        if (!answers.equals("skip")){
            toModify.setEndDate(answers);
        }
        System.out.println("Entrez une nouvelle catégorie d'intérèt ou \"skip\" pour ne pas modifier");
        System.out.println("Les intérêts actuels sont: ");
        for (String interest : Database.getInterests()){
            System.out.println(interest);
        }
        answers = scanner.nextLine();
        if (!answers.equals("skip")){
            toModify.setInterests(answers);
        }
        System.out.println("Entrez le nouveau nombre de points gagnés ou \"skip\" pour ne pas modifier");
        answers = scanner.nextLine();
        if (!answers.equals("skip")){
            toModify.setPoints(Integer.parseInt(answers));
        }
        System.out.println("Souhaitez-vous transférer le contrôle (Si oui, veuillez spécifier l'utilisateur par leur nom d'utilisateur) ou \"skip\" pour ne pas modifier");
        answers = scanner.nextLine();
        if (!answers.equals("skip")){
            for (User user2 : Database.getAllUsers()){
                if(user2.getUserName().equalsIgnoreCase(answers)){
                    toModify.setCreator(user2);
                }
            }
        }
        System.out.println("Entrez le numéro de la tâche que vous voulez modifier ou \"skip\" pour ne pas modifier");
        for (int i = 0; i < toModify.getTasks().size(); i++){
            System.out.println("["+i+"] "+ toModify.getTasks().get(i).getName());
        }
        answers = scanner.nextLine();
        if (!answers.equals("skip")){
            Task taskToModify = toModify.getTasks().get(Integer.parseInt(answers));
            System.out.println("Entrez le nouveau nom de la tâche");
            taskToModify.setName(scanner.nextLine());
            System.out.println("Entrez la premiere étape de la tâche(1/4)");
            taskToModify.getIntructions().get(0).setAction(scanner.nextLine());
            System.out.println("Entrez la deuxième étape de la tâche(2/4)");
            taskToModify.getIntructions().get(1).setAction(scanner.nextLine());
            System.out.println("Entrez la troisième étape de la tâche(3/4)");
            taskToModify.getIntructions().get(2).setAction(scanner.nextLine());
            System.out.println("Entrez la quatrième étape de la tâche(4/4)");
            taskToModify.getIntructions().get(3).setAction(scanner.nextLine());
        }
        System.out.println("Modification terminée");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}