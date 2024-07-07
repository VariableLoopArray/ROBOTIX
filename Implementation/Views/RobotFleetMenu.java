package Views;

import java.util.Scanner;


import Controllers.RobotController;

import Models.User;

public class RobotFleetMenu {

    public static void displayManageRobotFleet(User user){

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n\n\t\t*** Menu robots ***\n");
        System.out.println("Indiquez ce que vous voulez faire avec votre flotte");
        System.out.println("[0] Voir mes robots");
        System.out.println("[1] Créer un robot");
        System.out.println("[2] Supprimer un robot");
        System.out.println("[3] Examiner un robot");
        System.out.println("[4] Retour");

        
        switch( scanner.nextLine()){
            case "0":
                RobotController.showRobots(user);
                break;
            case "1":
                RobotController.addRobot(user);
                break;
            case "2":
                RobotController.deleteRobot(user);
                break;
            case "3":
                RobotController.lookRobot(user);
                break;
            case "4":
                Menu.displayHomePage(user);
                break;
            default:
                System.out.println("commande non connue");
                displayManageRobotFleet(user);
        }

    }
}