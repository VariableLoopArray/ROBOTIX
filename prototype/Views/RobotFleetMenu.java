package Views;

import java.util.Scanner;

import Models.User;

public class RobotFleetMenu {

    public static void displayManageRobotFleet(User user){

        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Indiquez ce que vous voulez faire avec votre flotte");
        System.out.println("[1] Ajouter un robot");
        System.out.println("[2] Supprimer un robot");
        System.out.println("[3] Examiner un robot");


    }
}