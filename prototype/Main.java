import java.util.UUID;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        ArrayList<User> allUsers = new ArrayList<User>();
        ArrayList<Activity> allActivities = new ArrayList<Activity>(); 
        Menu menu = new Menu(allUsers, allActivities);
        menu.displayLoginPage();

        //hard code Users
        Client user1 = new Client("John","Doe","johndoe123","password1",
        UUID.randomUUID(),"johndoe@gmail.com","No Company","514-111-1111",new RobotFleet(new ArrayList<Robot>()), 
        new ArrayList<String>(), new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<Order>());

        Client user2 = new Client("Zane","Underwood","ZaneUnderwood231","password2",
        UUID.randomUUID(),"ZaneUnderwood@gmail.com","Nvidia","514-222-2222",new RobotFleet(new ArrayList<Robot>()),
        new ArrayList<String>(),new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<Order>());

        Client user3 = new Client("Annabelle","Valdez","AnnabelleValdez562","password3",
        UUID.randomUUID(),"AnnabelleValdez@gmail.com","No Company","514-333-3333",new RobotFleet(new ArrayList<Robot>()),
        new ArrayList<String>(), new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<Order>());

        Client user4 = new Client("Gianluca","Gray","GianlucaGray135","password4", 
        UUID.randomUUID(),"GianlucaGray@gmail.com","PepsiCo","514-444-4444",new RobotFleet(new ArrayList<Robot>()),
        new ArrayList<String>(),new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<Order>());

        Client user5 = new Client("Jaydon","Sanchez","JaydonSanchez213","password5",
        UUID.randomUUID(),"JaydonSanchez@gmail.com","No Company","514-555-5555",new RobotFleet( new ArrayList<Robot>()),
        new ArrayList<String>(),new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<Order>());

        Client user6 = new Client("Ray","Charles","JazzyBoy","password11",
        UUID.randomUUID(),"RayCharles@gmail.com","No Company","514-101-0101",new RobotFleet(new ArrayList<Robot>()),
        new ArrayList<String>(), new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<Order>());

        Client user7 = new Client("Frank","Sinatra","DeepBlueEyes","password12",
        UUID.randomUUID(),"FrankSinatra@gmail.com","No Company","514-111-1111",new RobotFleet(new ArrayList<Robot>()),
        new ArrayList<String>(),new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<Order>());

        Client user8 = new Client("George","Washington","MakeAmerica","password13",
        UUID.randomUUID(),"GeorgeWashington@gmail.com","No Company","514-121-2121",new RobotFleet(new ArrayList<Robot>()),
        new ArrayList<String>(), new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<Order>());

        Client user9 = new Client("Teddy","Roosevelt","BullMoose","password14",
        UUID.randomUUID(),"TeddyRoosevelt@gmail.com","No Company","514-131-3131",new RobotFleet(new ArrayList<Robot>()),
        new ArrayList<String>(), new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<Order>());

        Client user10 = new Client("Manfred","Albert","TheRedBaron","password15",
        UUID.randomUUID(),"ManfredAlbert@gmail.com","No Company","514-141-4141",new RobotFleet(new ArrayList<Robot>()),
        new ArrayList<String>(), new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<Order>());

        Supplier user11 = new Supplier("Michael","Oliver","MichaelOliver521","password6",
        UUID.randomUUID(),"MichaelOliver@gmail.com","Amazon","514-666-6666",new RobotFleet(new ArrayList<Robot>()),
        new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(), 10, new ArrayList<Component>());

        Supplier user12 = new Supplier("Frankie","Charles","FrankieCharles178","password7",
        UUID.randomUUID(),"FrankieCharles@gmail.com","WheelCar.inc","514-777-7777",new RobotFleet(new ArrayList<Robot>()),
        new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(), 7, new ArrayList<Component>());

        Supplier user13 = new Supplier("Josh","Burton","JoshBurton412","password8",
        UUID.randomUUID(),"JoshBurton@gmail.com","Apple","514-888-8888",new RobotFleet(new ArrayList<Robot>()),
        new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(), 4, new ArrayList<Component>());

        Supplier user14 = new Supplier("Sophie","Moreno","SophieMoreno723","password9",
        UUID.randomUUID(),"SophieMoreno@gmail.com","Google","514-999-9999",new RobotFleet(new ArrayList<Robot>()),
        new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(), 5, new ArrayList<Component>());

        Supplier user15 = new Supplier("Edward","Blair","EdwardBlair413","password10",
        UUID.randomUUID(),"EdwardBlair@gmail.com","AeroX","514-123-4567",new RobotFleet(new ArrayList<Robot>()),
        new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(), 8, new ArrayList<Component>());


        //hard code ComponentTags
        ArrayList<String> componentTags =  new ArrayList<String>();
        componentTags.add("heavy");
        componentTags.add("lightweight");
        componentTags.add("high-performance");
        componentTags.add("waterproof");
        componentTags.add("eco-friendly");
        componentTags.add("durable");
        componentTags.add("fragile");
        componentTags.add("expensive");
        componentTags.add("cheap");
        componentTags.add("energy-efficient");


        //hard code Interests
        ArrayList<String> interests = new ArrayList<String>();
        interests.add("Sports and Fitness");
        interests.add("Arts and Crafts");
        interests.add("Music and Performing Arts");
        interests.add("Outdoor and Adventure");
        interests.add("Technology and Gaming");
        interests.add("Cooking and Baking");
        interests.add("Reading and Writing");
        interests.add("Travel and Culture");
        interests.add("Education and Learning");
        interests.add("Health and Wellness");


        //hard code Components
        Component CPU = new Component("CPU", new ArrayList<String>(Arrays.asList("high-performance","lightweight")), 350.0f, 1.0f, 1.0f, 1.0f, user11 ,UUID.randomUUID());
        Component Roue = new Component("Roue", new ArrayList<String>(Arrays.asList("durable")), 50.0f, 20.0f, 20.0f, 55.7f, user12 ,UUID.randomUUID());
        Component Bras = new Component("Bras", new ArrayList<String>(Arrays.asList("waterproof","expensive")), 100.0f, 20.0f, 20.0f, 20.0f, user15 ,UUID.randomUUID());
        Component Helice = new Component("Hélice", new ArrayList<String>(Arrays.asList("durable","expensive")), 45.0f, 15.0f, 15.0f, 10.0f, user15 ,UUID.randomUUID());
        Component Camera = new Component("Caméra", new ArrayList<String>(Arrays.asList("energy-efficient","fragile")), 200.0f, 10.0f, 10.0f, 15.0f, user13 ,UUID.randomUUID());
        Component HautParleur = new Component("Haut-Parleur", new ArrayList<String>(Arrays.asList("eco-friendly")), 30.0f, 5.0f, 5.0f, 5.0f, user14 ,UUID.randomUUID());
        Component Micro = new Component("Microphone", new ArrayList<String>(Arrays.asList("fragile","lightweight","cheap")), 20.0f, 2.0f, 2.0f, 2.0f, user14 ,UUID.randomUUID());
        Component Ecran = new Component("Écran", new ArrayList<String>(Arrays.asList("high-performance","waterproof","energy-efficient")), 100.0f, 20.0f, 30.0f, 40.0f, user13 ,UUID.randomUUID());

        //hard code Robots


        //Robot robot1 = new Robot("Rob Bott", "aquaticRobot", "2021-10-10", "2021-10-11", "Montreal", "Canada", "10:00", "12:00", user1);
        Robot Robot1 = new Robot ("Rob Bott", "explorationRobot", new ArrayList<Component>(Arrays.asList(CPU,Bras,Camera,Helice,Roue)), UUID.randomUUID(), 100, new float[]{0.0f,1.0f,2.0f}, 0.0f,  0.0f, 2048.0f);
        Robot Robot2 = new Robot("Apon", "serviceRobot", new ArrayList<Component>(Arrays.asList(CPU,Ecran,Roue)), UUID.randomUUID(), 100, new float[]{0.0f,0.0f,0.0f}, 0.0f,  0.0f, 512.0f);
        Robot Robot3 = new Robot("Robo", "entertainmentRobot", new ArrayList<Component>(Arrays.asList(CPU,HautParleur,Ecran)), UUID.randomUUID(), 100, new float[]{0.0f,0.0f,0.0f}, 0.0f,  0.0f, 256.0f);
        Robot Robot4 = new Robot("Copper", "militaryRobot", new ArrayList<Component>(Arrays.asList(CPU,Helice,Bras,Roue)), UUID.randomUUID(), 100, new float[]{0.0f,0.0f,0.0f}, 0.0f,  0.0f, 2048.0f);
        Robot Robot5 = new Robot("Bolt", "explorationRobot", new ArrayList<Component>(Arrays.asList(CPU,Bras,Camera,Helice,Roue)), UUID.randomUUID(), 100, new float[]{0.0f,0.0f,0.0f}, 0.0f,  0.0f, 1024.0f);
        //hard code activities
        //Activity activity1 = new Activity("Activity 1", , "2021-10-10", "2021-10-11", "Montreal", "Canada", "10:00", "12:00", user1);
        Activity correcActivity1 = new Activity("Activity 1", correctRobot1, "2024-07-03", "2024-07-03", interests, 0, user10, new ArrayList<Task>());
        //Activity activity2 = new Activity("Activity 2", "Description 2", "2021-10-11", "2021-10-12", "Montreal", "Canada", "10:00", "12:00", user2);
        Activity correcActivity2 = new Activity("Activity 2", correctRobot1, "2025-07-03", "2025-07-03", interests, 0, user10, new ArrayList<Task>());
    }
}