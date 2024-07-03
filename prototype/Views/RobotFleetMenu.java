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
            
            String name;
                while (true){

                    System.out.println("Entrer le nom de votre robot\n");
                    name = scanner.nextLine();
                    boolean nameTaken = false;

                    if(name.equals("quitter")){
                        System.out.println("nom invalide, réessayez un autre nom\n");
                        nameTaken = true;
                    }

                    for (Robot robot : user.getRobotFleet().getRobots()){
                        if (robot.getName().equals(name)){
                            System.out.println("nom déjà pris, svp réessayez\n");
                            nameTaken = true;
                        }
                    }
                    if (!nameTaken){
                        break;
                    }
                    
                }

                System.out.println("Entrer le type de votre robot\n"); 
                String type = scanner.nextLine();

                user.getRobotFleet().add(new Robot(name, type, new ArrayList<Component>(), UUID.randomUUID(),
                10,new double[] {0.0,0.0,0.0}, 0, 0, 0));

                System.out.println("Le robot a été créé avec succès\n");
                displayManageRobotFleet(user);
                
                break;
            case "3":
                
                System.out.println(" Entrer le nom du robot que vous voulez enlever " + 
                "\nou quitter pour quitter");
            
                while (true){
                    String nameToRemove = scanner.nextLine();
                    if (nameToRemove.equals("quitter")){
                        break;
                    }
                    boolean nameInList = false;
                    Iterator<Robot> robotIterator = user.getRobotFleet().getRobots().iterator();
                    while (robotIterator.hasNext()){
                        Robot robot = robotIterator.next();
                        if (robot.getName().equals(nameToRemove)){
                            robotIterator.remove();
                            nameInList = true;
                        }
                    }
                    if (nameInList){
                        break;
                    }
                    System.out.println("the nom n'est pas dans votre liste de robots, svp réessyez\n");
                
                }
                displayManageRobotFleet(user);
                break;
            case "4":
                System.out.println("Entrez le nom du robot que vous voulez examiner\n");
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
                    System.out.println("Vous n'avez pas de robot de ce nom.\n");
                }
                displayManageRobotFleet(user);
                break;
            case "5":
                Menu.displayHomePage(user);
                break;
            default:
                System.out.println("commande non connue");
        }

    }
}