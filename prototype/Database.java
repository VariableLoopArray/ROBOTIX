import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class Database{
        private static ArrayList<User> allUsers = new ArrayList<User>();
        private static ArrayList<Activity> allActivities = new ArrayList<Activity>(); 
        private static ArrayList<String> componentTags = new ArrayList<String>();
        private static ArrayList<Component> allComponents = new ArrayList<Component>();
        private static ArrayList<Robot> allRobots = new ArrayList<Robot>();
        private static ArrayList<Supplier> allSuppliers = new ArrayList<Supplier>();
        private static ArrayList<Client> allClients = new ArrayList<Client>();
        private static ArrayList<String> interests = new ArrayList<String>();
        static{
        //hard code users
        Client user1 = new Client("John","Doe","johndoe123","password1",UUID.randomUUID(),
        "johndoe@gmail.com","No Company","514-111-1111",0f , new RobotFleet(new ArrayList<Robot>()), 
        new ArrayList<String>(), new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<Order>());

        Client user2 = new Client("Zane","Underwood","ZaneUnderwood231","password2",UUID.randomUUID(),
        "ZaneUnderwood@gmail.com","Nvidia","514-222-2222", 0f,new RobotFleet(new ArrayList<Robot>()),
        new ArrayList<String>(),new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<Order>());

        Client user3 = new Client("Annabelle","Valdez","AnnabelleValdez562","password3", UUID.randomUUID(),
        "AnnabelleValdez@gmail.com","No Company","514-333-3333",0f,new RobotFleet(new ArrayList<Robot>()),
        new ArrayList<String>(), new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<Order>());

        Client user4 = new Client("Gianluca","Gray","GianlucaGray135","password4", UUID.randomUUID(),
        "GianlucaGray@gmail.com","PepsiCo","514-444-4444",0f,new RobotFleet(new ArrayList<Robot>()),
        new ArrayList<String>(),new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<Order>());

        Client user5 = new Client("Jaydon","Sanchez","JaydonSanchez213","password5",UUID.randomUUID(),
        "JaydonSanchez@gmail.com","No Company","514-555-5555",0f,new RobotFleet( new ArrayList<Robot>()),
        new ArrayList<String>(),new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<Order>());

        Client user6 = new Client("Ray","Charles","JazzyBoy","password11",UUID.randomUUID(),
        "RayCharles@gmail.com","No Company","514-101-0101",0f,new RobotFleet(new ArrayList<Robot>()),
        new ArrayList<String>(), new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<Order>());

        Client user7 = new Client("Frank","Sinatra","DeepBlueEyes","password12",UUID.randomUUID(),
        "FrankSinatra@gmail.com","No Company","514-111-1111",0f,new RobotFleet(new ArrayList<Robot>()),
        new ArrayList<String>(),new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<Order>());

        Client user8 = new Client("George","Washington","MakeAmerica","password13",UUID.randomUUID(),
        "GeorgeWashington@gmail.com","No Company","514-121-2121",0f,new RobotFleet(new ArrayList<Robot>()),
        new ArrayList<String>(), new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<Order>());

        Client user9 = new Client("Teddy","Roosevelt","BullMoose","password14",UUID.randomUUID(),
        "TeddyRoosevelt@gmail.com","No Company","514-131-3131",0f,new RobotFleet(new ArrayList<Robot>()),
        new ArrayList<String>(), new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<Order>());

        Client user10 = new Client("Manfred","Albert","TheRedBaron","password15",UUID.randomUUID(),
        "ManfredAlbert@gmail.com","No Company","514-141-4141",0f,new RobotFleet(new ArrayList<Robot>()),
        new ArrayList<String>(), new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<Order>());

        Supplier user11 = new Supplier("Michael","Oliver","MichaelOliver521","password6",UUID.randomUUID(),
        "MichaelOliver@gmail.com","Amazon","514-666-6666",0f,new RobotFleet(new ArrayList<Robot>()),
        new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(), 10, new ArrayList<Component>());

        Supplier user12 = new Supplier("Frankie","Charles","FrankieCharles178","password7",UUID.randomUUID(),
        "FrankieCharles@gmail.com","WheelCar.inc","514-777-7777",0f,new RobotFleet(new ArrayList<Robot>()),
        new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(), 7, new ArrayList<Component>());

        Supplier user13 = new Supplier("Josh","Burton","JoshBurton412","password8",UUID.randomUUID(),
        "JoshBurton@gmail.com","Apple","514-888-8888",0f,new RobotFleet(new ArrayList<Robot>()),
        new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(), 4, new ArrayList<Component>());

        Supplier user14 = new Supplier("Sophie","Moreno","SophieMoreno723","password9",UUID.randomUUID(),
        "SophieMoreno@gmail.com","Google","514-999-9999",0f,new RobotFleet(new ArrayList<Robot>()),
        new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(), 5, new ArrayList<Component>());

        Supplier user15 = new Supplier("Edward","Blair","EdwardBlair413","password10",UUID.randomUUID(),
        "EdwardBlair@gmail.com","AeroX","514-123-4567",0f,new RobotFleet(new ArrayList<Robot>()),
        new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(), 8, new ArrayList<Component>());

        allUsers.add(user1);
        allClients.add(user1);
        allUsers.add(user2);
        allClients.add(user2);
        allUsers.add(user3);
        allClients.add(user3);
        allUsers.add(user4);
        allClients.add(user4);
        allUsers.add(user5);
        allClients.add(user5);
        allUsers.add(user6);
        allClients.add(user6);
        allUsers.add(user7);
        allClients.add(user7);
        allUsers.add(user8);
        allClients.add(user8);
        allUsers.add(user9);
        allClients.add(user9);
        allUsers.add(user10);
        allClients.add(user10);
        allUsers.add(user11);
        allSuppliers.add(user11);
        allUsers.add(user12);
        allSuppliers.add(user12);
        allUsers.add(user13);
        allSuppliers.add(user13);
        allUsers.add(user14);
        allSuppliers.add(user14);
        allUsers.add(user15);
        allSuppliers.add(user15);

        //hard code componentTags
        componentTags.add("lourd");
        componentTags.add("léger");
        componentTags.add("haute performance");
        componentTags.add("imperméable");
        componentTags.add("écologique");
        componentTags.add("durable");
        componentTags.add("fragile");
        componentTags.add("cher");
        componentTags.add("bon marché");
        componentTags.add("Consommation d'énergie efficace");


        //hard code Interests
        interests.add("Sports et Fitness");
        interests.add("Arts et Artisanat");
        interests.add("Musique et Arts du Spectacle");
        interests.add("Activité de Plein Air et Aventure");
        interests.add("Technologie et Jeux Vidéo");
        interests.add("Cuisine et Gastonomie");
        interests.add("Lecture et Écriture");
        interests.add("Voyages et culture");
        interests.add("Éducation et Apprentissage");
        interests.add("Santé et Bien-être");


        //hard code Components
        Component CPU = new Component("CPU", new ArrayList<String>(Arrays.asList("haute performance","léger")), 350.0f, 1.0f, 1.0f, 1.0f, user11 ,UUID.randomUUID());
        Component Roue = new Component("Roue", new ArrayList<String>(Arrays.asList("durable")), 50.0f, 20.0f, 20.0f, 55.7f, user12 ,UUID.randomUUID());
        Component Bras = new Component("Bras", new ArrayList<String>(Arrays.asList("imperméable","cher")), 100.0f, 20.0f, 20.0f, 20.0f, user15 ,UUID.randomUUID());
        Component Helice = new Component("Hélice", new ArrayList<String>(Arrays.asList("durable","cher")), 45.0f, 15.0f, 15.0f, 10.0f, user15 ,UUID.randomUUID());
        Component Camera = new Component("Caméra", new ArrayList<String>(Arrays.asList("Consommation d'énergie efficace","fragile")), 200.0f, 10.0f, 10.0f, 15.0f, user13 ,UUID.randomUUID());
        Component HautParleur = new Component("Haut-Parleur", new ArrayList<String>(Arrays.asList("écologique")), 30.0f, 5.0f, 5.0f, 5.0f, user14 ,UUID.randomUUID());
        Component Micro = new Component("Microphone", new ArrayList<String>(Arrays.asList("fragile","léger","bon marché")), 20.0f, 2.0f, 2.0f, 2.0f, user14 ,UUID.randomUUID());
        Component Ecran = new Component("Écran", new ArrayList<String>(Arrays.asList("haute performance","imperméable","Consommation d'énergie efficace")), 100.0f, 20.0f, 30.0f, 40.0f, user13 ,UUID.randomUUID());

        allComponents.add(CPU);
        allComponents.add(Roue);
        allComponents.add(Bras);
        allComponents.add(Helice);
        allComponents.add(Camera);
        allComponents.add(HautParleur);
        allComponents.add(Micro);
        allComponents.add(Ecran);
    

        //hard code Robots
        Robot Robot1 = new Robot ("Rob Bott", "explorationRobot", new ArrayList<Component>(Arrays.asList(CPU,Bras,Camera,Helice,Roue)), UUID.randomUUID(), 100, new float[]{0.0f,1.0f,2.0f}, 0.0f,  0.0f, 2048.0f);
        Robot Robot2 = new Robot("Apon", "serviceRobot", new ArrayList<Component>(Arrays.asList(CPU,Ecran,Roue)), UUID.randomUUID(), 100, new float[]{0.0f,0.0f,0.0f}, 0.0f,  0.0f, 512.0f);
        Robot Robot3 = new Robot("Robo", "entertainmentRobot", new ArrayList<Component>(Arrays.asList(CPU,HautParleur,Ecran)), UUID.randomUUID(), 100, new float[]{0.0f,0.0f,0.0f}, 0.0f,  0.0f, 256.0f);
        Robot Robot4 = new Robot("Copper", "militaryRobot", new ArrayList<Component>(Arrays.asList(CPU,Helice,Bras,Roue)), UUID.randomUUID(), 100, new float[]{0.0f,0.0f,0.0f}, 0.0f,  0.0f, 2048.0f);
        Robot Robot5 = new Robot("Bolt", "educationalRobot", new ArrayList<Component>(Arrays.asList(CPU,Ecran)), UUID.randomUUID(), 100, new float[]{0.0f,0.0f,0.0f}, 0.0f,  0.0f, 1024.0f);

        allRobots.add(Robot1);
        allRobots.add(Robot2);
        allRobots.add(Robot3);
        allRobots.add(Robot4);
        allRobots.add(Robot5);

        //hard code activities
        Activity Activity1 = new Activity("CodingBootCamp", Robot5, "2024-07-03", "2024-09-03", "Éducation et Apprentissage", 150, user10, new ArrayList<Task>());
        Activity Activity2 = new Activity("Recette de cuisine", Robot5, "2024-07-30", "2024-07-30","Éducation et Apprentissage" , 15, user9, new ArrayList<Task>());
        Activity Activity3 = new Activity("Concert de Jazz", Robot3, "2024-08-03", "2024-08-03", "Musique et Arts du Spectacle", 10, user8, new ArrayList<Task>());
        Activity Activity4 = new Activity("Exploration de la cave", Robot1, "2024-08-10", "2024-08-10", "Activité de Plein Air et Aventure", 20, user7, new ArrayList<Task>());
        Activity Activity5 = new Activity("Ranger la maison", Robot2, "2024-08-17", "2024-08-17", "Santé et Bien-être", 10, user6, new ArrayList<Task>());

        allActivities.add(Activity1);
        allActivities.add(Activity2);
        allActivities.add(Activity3);
        allActivities.add(Activity4);
        allActivities.add(Activity5);


        }
        public static ArrayList<User> getAllUsers(){
            return allUsers;
        }
        public static ArrayList<Activity> getAllActivities(){
            return allActivities;
        }
        public static ArrayList<String> getComponentTags(){
            return componentTags;
        }
        public static ArrayList<Component> getAllComponents(){
            return allComponents;
        }
        public static ArrayList<Robot> getAllRobots(){
            return allRobots;
        }
        public static ArrayList<Supplier> getAllSuppliers(){
            return allSuppliers;
        }
        public static ArrayList<Client> getAllClients(){
            return allClients;
        }
        public static ArrayList<String> getInterests(){
            return interests;
        }
        
}