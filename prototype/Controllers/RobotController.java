package Controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.UUID;

import Models.User;
import Models.Component;
import Models.Robot;
import Models.typeOfRobots.AerialRobot;
import Models.typeOfRobots.AquaticRobot;
import Models.typeOfRobots.TerrestrialRobot;
import Views.RobotFleetMenu;

public class RobotController {
    public static void showRobots(User user){
        System.out.println("\n\n\nVoici la liste de vos robots:\n");
        for (Robot robot : user.getRobotFleet().getRobots()){
            System.out.println(robot.getName());
        }
        RobotFleetMenu.displayManageRobotFleet(user);
    }
    public static void addRobot(User user){
        String name;
        Scanner scanner = new Scanner(System.in);
            while (true){

                System.out.println("\n\n\n***Création de robot***\n");
                System.out.println("Entrer le nom de votre robot\n");
                name = scanner.nextLine();
                boolean nameTaken = false;

                for (Robot robot : user.getRobotFleet().getRobots()){
                    if (robot.getName().equals(name)){
                        System.out.println("nom déjà pris, svp réessayez\n");
                        nameTaken = true;
                        break;
                    }
                }
                if (!nameTaken){
                    break;
                }
                
            }

            System.out.println("\nEntrer le type de votre robot"); 
            String type = scanner.nextLine();

            System.out.println("\nIndiquez la categorie de votre robot");
            System.out.println("[0] Robot Aérien");
            System.out.println("[1] Robot Terrestre");
            System.out.println("[2] Robot Aquatique");

            String value = scanner.nextLine();
            switch (value) {
                case "0":
                    user.getRobotFleet().add(new AerialRobot(name, type, new ArrayList<Component>(), UUID.randomUUID(),
                    0,new double[] {0.0,0.0,0.0}, 0, 0, 0,0 ));
                    break;
                case "1":
                    user.getRobotFleet().add(new TerrestrialRobot(name, type, new ArrayList<Component>(), UUID.randomUUID(),
                    0,new double[] {0.0,0.0,0.0}, 0, 0, 0,0));
                    break;
                case "2":
                    user.getRobotFleet().add(new AquaticRobot(name, type, new ArrayList<Component>(), UUID.randomUUID(),
                    0,new double[] {0.0,0.0,0.0}, 0, 0, 0,0));
                    break;
                default:
                    System.out.println("\ncommande non connue");
                    
                    break;
            }
            System.out.println("\nLe robot a été créé avec succès\n");
            RobotFleetMenu.displayManageRobotFleet(user);
    }


    public static void deleteRobot(User user){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n\n***Suppression de robot***\n");
        System.out.println("Voici la liste de vos robots\n");
        for (Robot robot : user.getRobotFleet().getRobots()){
            System.out.println(robot.getName());
        }
        System.out.println("Entrer le nom du robot que vous voulez enlever (ou ecrivez rien pour quitter)\n");
        while (true){
            String nameToRemove = scanner.nextLine();
            if (nameToRemove.equals("")){
                break;
            }
            boolean nameInList = false;
            Iterator<Robot> robotIterator = user.getRobotFleet().getRobots().iterator();
            while (robotIterator.hasNext()){
                Robot robot = robotIterator.next();
                if (robot.getName().equals(nameToRemove)){
                    robotIterator.remove();
                    nameInList = true;
                    System.out.println("\nLe robot a été supprimé avec succès\n");
                }
            }
            if (nameInList){
                break;
            }
            System.out.println("\nLe nom n'est pas dans votre liste de robots, svp réessayez\n");
        }
        RobotFleetMenu.displayManageRobotFleet(user);
    }


    public static void lookRobot(User user){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEntrez le nom du robot que vous voulez examiner\n");
                String searchName = scanner.nextLine();
                boolean inList = false;
                for (Robot robot : user.getRobotFleet().getRobots()){
                    if (robot.getName().equals(searchName)){
                        robot.robotData();
                        inList = true;
                        break;
                    }
                }
                if (!inList){
                    System.out.println("Vous n'avez pas de robot avec ce nom.\n");
                }
                RobotFleetMenu.displayManageRobotFleet(user);
    }

}
