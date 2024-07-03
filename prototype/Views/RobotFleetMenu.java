package Views;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

import java.util.Iterator;
import Models.Component;
import Models.Robot;
import Models.RobotFleet;
import Models.User;

public class RobotFleetMenu {

    public static void displayManageRobotFleet(User user){

        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Indiquez ce que vous voulez faire avec votre flotte");
        System.out.println("[1] Montrer mes robots");
        System.out.println("[2] Ajouter un robot");
        System.out.println("[3] Supprimer un robot");
        System.out.println("[4] Examiner un robot");
        System.out.println("[5] Retour");

        
        switch( scanner.nextLine()){
            case "1":
                user.getRobotFleet().showRobots();
                displayManageRobotFleet(user);
                break;
            case "2":
            
                user.addRobot();
            
                
                break;
            case "3":
                
                user.deleteRobot();
                break;
            case "4":
                user.lookRobot();
                break;
            case "5":
                Menu.displayHomePage(user);
                break;
            default:
                System.out.println("commande non connue");
        }

    }
}