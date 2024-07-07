package Controllers;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Database.Database;
import Models.Action;
import Models.Activity;
import Models.Task;
import Models.User;
import Views.ActivityMenu;

public class ActivityController {
    public static void addActivity(User user, ArrayList<Activity> availableActivities){
        Scanner scanner = new Scanner(System.in);
        if(availableActivities.size() == 0){
            System.out.println("Il n'y a pas d'activités disponibles pour le moment");
            ActivityMenu.displayManageActivities(user);
        }
        System.out.println("Entrez le numéro de l'activité que vous voulez ajouter (Colonne de droite)");
        Activity toAdd = new Activity();
        boolean isInt = true;
        int activityNumber = 0;
        while(isInt)
        try {
            String activityToAdd = scanner.nextLine();
            toAdd = availableActivities.get(Integer.parseInt(activityToAdd));
            activityNumber = Integer.parseInt(activityToAdd);
            isInt = false;
        } catch (Exception e) {
            System.out.println("Erreur: Entrez un numéro valide");
        }

        System.out.println(toAdd.getCreator().getUserName());
        toAdd.getCreator().getNotifs().add("ActivityFollower " + user.getUserName());
        user.addActivity(toAdd);
        

    }

    public static void deleteActivity(User user){
        Scanner scanner = new Scanner(System.in);
        if(user.getActivities().size() == 0){
            System.out.println("Vous n'avez pas encore ajouté d'activités à votre compte");
            ActivityMenu.displayManageActivities(user);
        }
        System.out.println("Entrez le numéro de l'activité que vous voulez supprimer (Colonne de gauche)");

        Activity toDelete = new Activity();
        boolean isInt = true;
        while(isInt){
            try {
                String activityToDelete = scanner.nextLine();
                toDelete = user.getActivities().get(Integer.parseInt(activityToDelete));
                isInt = false;
            } catch (Exception e) {
                System.out.println("Erreur: Entrez un numéro valide, veuillez réessayer");
            }
        }
        user.removeActivity(toDelete);
    }


    public static void modifyActivity(User user){
        try{
        boolean isInt = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voici les activités dont vous êtes le créateur:");
        for (int i = 0; i < Database.getAllActivities().size(); i++){
            if(Database.getAllActivities().get(i).getCreator() == user){
                isInt = false;
                System.out.println("["+i+"] "+ Database.getAllActivities().get(i).getName());
            }
        }
        if (isInt) {
            System.out.println("Oups! Le système n'a pas trouvé d'activités que vous avez créé. Veuillez en créer une pour pouvoir la modifier.\n"+
                                "(un activité ajouttée ne vous donne pas le droit de la modifier, il faut être le créateur de l'activité)");
            ActivityMenu.displayManageActivities(user);
        }

        System.out.println("Entrez le numéro de l'activité que vous voulez modifier (Seul créateur de l'activité)");
        Activity toModify = new Activity();
        boolean isInt2 = true;
        while(isInt2){
            try {
                String activityToModify = scanner.nextLine();
                if(Database.getAllActivities().get(Integer.parseInt(activityToModify)).getCreator() != user){
                    System.out.println("Erreur: Vous n'êtes pas le créateur de cette activité");
                    break;
                }
                toModify = Database.getAllActivities().get(Integer.parseInt(activityToModify));
                isInt2 = false;
            } catch (Exception e) {
                System.out.println("Erreur: Entrez un numéro valide");
                modifyActivity(user);
            }
        }
        System.out.println("Entrez un nouveau nom pour l'activité ou \"skip\" pour ne pas modifier");
        String answers = scanner.nextLine();
        if (!answers.equals("skip")){
            toModify.setName(answers);
        }
        for (int i = 0; i < user.getRobotFleet().getRobots().size(); i++){
            System.out.println(i + ") " + user.getRobotFleet().getRobots().get(i).getName());
        }
        System.out.println("Entrez le nom d'un nouveau robot ou \"skip\" pour ne pas modifier");
        answers = scanner.nextLine();
        if (!answers.equals("skip")){
            isInt = true;
            for (int i = 0; i < user.getRobotFleet().getRobots().size(); i++){
                if(user.getRobotFleet().getRobots().get(i).getName().equals(answers)){
                    toModify.setRobot(user.getRobotFleet().getRobots().get(i));
                    isInt = false;
                }
            }
            if (isInt){
                System.out.println("Erreur: Robot non trouvé");
                ActivityMenu.displayManageActivities(user);
            }

        }
        System.out.println("Entrez une nouvelle date de début (format: yyyy-mm-dd) ou \"skip\" pour ne pas modifier");
        answers = scanner.nextLine();
        if (!answers.equals("skip")){
            toModify.setStartDate(LocalDate.parse(answers));
        }
        System.out.println("Entrez une nouvelle date de fin (format: yyyy-mm-dd) ou \"skip\" pour ne pas modifier");
        answers = scanner.nextLine();
        if (!answers.equals("skip")){
            toModify.setEndDate(LocalDate.parse(answers));
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
            System.out.println(i+") "+ toModify.getTasks().get(i).getName());
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

    public static void activityData(User user){
        Scanner scanner = new Scanner(System.in);
        if(user.getActivities().size() == 0){
            System.out.println("Vous n'avez pas encore ajouté d'activités à votre compte");
            ActivityMenu.displayManageActivities(user);
        }
        System.out.println("Voici les activités que vous avez ajouté");
        for (int i = 0; i < user.getActivities().size(); i++){
            System.out.println("["+i+"] "+ user.getActivities().get(i).getName());
        }

        System.out.println("Entrez le numéro de l'activité que vous voulez visualiser");
        Activity toView = new Activity();
        boolean isInt4 = true;
        while(isInt4){
            try {
                String activityToView = scanner.nextLine();
                toView = user.getActivities().get(Integer.parseInt(activityToView));
                isInt4 = false;
            } catch (Exception e) {
                System.out.println("Erreur: Entrez un numéro valide");
            }
        }
        System.out.println("Nom: "+ toView.getName());
        System.out.println("Robot: "+ toView.getRobot().getName());
        System.out.println("Statut: "+ toView.getStatus());
        System.out.println("Date de début: "+ toView.getStartDate());
        System.out.println("Date de fin: "+ toView.getEndDate());
        System.out.println("Catégorie d'intérêt: "+ toView.getInterests());
        System.out.println("Points gagnés: "+ toView.getPoints());
        System.out.println("Créateur: "+ toView.getCreator().getUserName());
        System.out.println("Tâches: ");
        for (int i = 0; i < toView.getTasks().size(); i++){
            System.out.println(i + ")"+ toView.getTasks().get(i).getName());
        }
    }

    
    public static void createActivity(User user){
        try {
        System.out.println("Création d'une activité pour la plateforme ROBOTIX");
        Scanner scanner = new Scanner(System.in);
        Activity newActivity = new Activity();
        System.out.println("Entrez le nom de l'activité");
        newActivity.setName(scanner.nextLine());
        if(user.getRobotFleet().getRobots().size() == 0){
            System.out.println("Vous n'avez pas de robots pour le moment, veuillez en ajouter un pour continuer");
            ActivityMenu.displayManageActivities(user);
        }
        try {
            System.out.println("Voici la liste de vos robots: ");
            for (int i = 0; i < user.getRobotFleet().getRobots().size(); i++){
                System.out.println("["+i+"] "+ user.getRobotFleet().getRobots().get(i).getName());
            }
            System.out.println("Entrez le nom du robot");
            newActivity.setRobot(user.getRobotFleet().getRobots().get(Integer.parseInt(scanner.nextLine())));
        } catch (Exception e) {
            System.out.println("Erreur: Entrée invalide ou robot non trouvé");
            ActivityMenu.displayManageActivities(user);
        }

        System.out.println("Entrez la date de début (format: yyyy-mm-dd)");
        newActivity.setStartDate(LocalDate.parse(scanner.nextLine()));
        System.out.println("Entrez la date de fin (format: yyyy-mm-dd)");
        newActivity.setEndDate(LocalDate.parse(scanner.nextLine()));
        System.out.println("Voici les catégories d'intérêts disponibles:");
        for (int i = 0; i < Database.getInterests().size(); i++){
            System.out.println("["+i+"] "+ Database.getInterests().get(i));
        }
        System.out.println("Entrez le numéro de la catégorie d'intérêt");
        newActivity.setInterests(Database.getInterests().get(Integer.parseInt(scanner.nextLine())));
        System.out.println("Entrez le nombre de points distribués pour cette activité");
        newActivity.setPoints(Integer.parseInt(scanner.nextLine()));
        newActivity.setCreator(user);
        System.out.println("Entrez le nom de la tâche 1");
        Task newTask = new Task("Pas de nom",new ArrayList<Action>());
        newTask.setName(scanner.nextLine());
        System.out.println("Entrez la première instruction de la tâche(1/4)");
        newTask.getIntructions().add(new Action(scanner.nextLine()));
        System.out.println("Entrez la deuxième instruction de la tâche(2/4)");
        newTask.getIntructions().add(new Action(scanner.nextLine()));
        System.out.println("Entrez la troisième instruction de la tâche(3/4)");
        newTask.getIntructions().add(new Action(scanner.nextLine()));
        System.out.println("Entrez la quatrième instruction de la tâche(4/4)");
        newTask.getIntructions().add(new Action(scanner.nextLine()));
        Task newTask2 = new Task("Pas de nom",new ArrayList<Action>());
        System.out.println("Entrez le nom de la tâche 2");
        newTask2.setName(scanner.nextLine());
        System.out.println("Entrez la première instruction de la tâche(1/4)");
        newTask2.getIntructions().add(new Action(scanner.nextLine()));
        System.out.println("Entrez la deuxième instruction de la tâche(2/4)");
        newTask2.getIntructions().add(new Action(scanner.nextLine()));
        System.out.println("Entrez la troisième instruction de la tâche(3/4)");
        newTask2.getIntructions().add(new Action(scanner.nextLine()));
        System.out.println("Entrez la quatrième instruction de la tâche(4/4)");
        newTask2.getIntructions().add(new Action(scanner.nextLine()));
        System.out.println("Entrez le nom de la tâche 3");
        Task newTask3 = new Task("Pas de nom",new ArrayList<Action>());
        newTask3.setName(scanner.nextLine());
        System.out.println("Entrez la première instruction de la tâche(1/4)");
        newTask3.getIntructions().add(new Action(scanner.nextLine()));
        System.out.println("Entrez la deuxième instruction de la tâche(2/4)");
        newTask3.getIntructions().add(new Action(scanner.nextLine()));
        System.out.println("Entrez la troisième instruction de la tâche(3/4)");
        newTask3.getIntructions().add(new Action(scanner.nextLine()));
        System.out.println("Entrez la quatrième instruction de la tâche(4/4)");
        newTask3.getIntructions().add(new Action(scanner.nextLine()));

        newActivity.setTasks(new ArrayList<>());
        newActivity.getTasks().add(newTask);
        newActivity.getTasks().add(newTask2);
        newActivity.getTasks().add(newTask3);

        System.out.println("Activité créée");
        System.out.println(newActivity.getInterests());
        for (User u : Database.getAllUsers()){
            if (u.getInterests().contains(newActivity.getInterests())){
                u.getNotifs().add("ActivityInterest" + " " + newActivity.getName());
            }
        }
        Database.getAllActivities().add(newActivity);
        
        ActivityMenu.displayManageActivities(user);
        } catch (Exception e) {
            System.out.println("Erreur: Entrée invalide");
            ActivityMenu.displayManageActivities(user);
        }

    }
}