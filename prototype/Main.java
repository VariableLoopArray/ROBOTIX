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
        //hard code users

        Client user1 = new Client("John","Doe","johndoe123","password1",UUID.randomUUID(),
        "johndoe@gmail.com","No Company","514-111-1111",new RobotFleet(), new ArrayList<String>(), new ArrayList<Activity>(), 
        new ArrayList<User>(), new ArrayList<User>(),new ArrayList<Order>());

        Client user2 = new Client("Zane","Underwood","ZaneUnderwood231","password2",
        UUID.randomUUID(),"ZaneUnderwood@gmail.com","Nvidia","514-222-2222",new RobotFleet(),new ArrayList<String>(),
        new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<Order>());

        Client user3 = new Client("Annabelle","Valdez","AnnabelleValdez562","password3",
        UUID.randomUUID(),"AnnabelleValdez@gmail.com","No Company","514-333-3333",new RobotFleet(),
        new ArrayList<String>(), new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<Order>());

        Client user4 = new Client("Gianluca","Gray","GianlucaGray135","password4", 
        UUID.randomUUID(),"GianlucaGray@gmail.com","PepsiCo","514-444-4444",new RobotFleet(),new ArrayList<String>(),
        new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<Order>());

        Client user5 = new Client("Jaydon","Sanchez","JaydonSanchez213","password5",
        UUID.randomUUID(),"JaydonSanchez@gmail.com","No Company","514-555-5555",new RobotFleet(),
        new ArrayList<String>(),new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<Order>());

        Client user6 = new Client("Ray","Charles","JazzyBoy","password11",UUID.randomUUID(),
        "RayCharles@gmail.com","No Company","514-101-0101",new RobotFleet(),new ArrayList<String>(), 
        new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<Order>());

        Client user7 = new Client("Frank","Sinatra","DeepBlueEyes","password12",
        UUID.randomUUID(),"FrankSinatra@gmail.com","No Company","514-111-1111",new RobotFleet(),
        new ArrayList<String>(),new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<Order>());

        Client user8 = new Client("George","Washington","MakeAmerica","password13",
        UUID.randomUUID(),"GeorgeWashington@gmail.com","No Company","514-121-2121",new RobotFleet(),
        new ArrayList<String>(), new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<Order>());

        Client user9 = new Client("Teddy","Roosevelt","BullMoose","password14",
        UUID.randomUUID(),"TeddyRoosevelt@gmail.com","No Company","514-131-3131",new RobotFleet(),
        new ArrayList<String>(), new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<Order>());

        Client user10 = new Client("Manfred","Albert","TheRedBaron","password15",
        UUID.randomUUID(),"ManfredAlbert@gmail.com","No Company","514-141-4141",new RobotFleet(),
        new ArrayList<String>(), new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<Order>());

        Supplier user11 = new Supplier("Michael","Oliver","MichaelOliver521","password6",
        UUID.randomUUID(),"MichaelOliver@gmail.com","No Company","514-666-6666",new RobotFleet(),
        new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(), 10, new ArrayList<Component>());

        Supplier user12 = new Supplier("Frankie","Charles","FrankieCharles178","password7",
        UUID.randomUUID(),"FrankieCharles@gmail.com","No Company","514-777-7777",new RobotFleet(),
        new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(), 7, new ArrayList<Component>());

        Supplier user13 = new Supplier("Josh","Burton","JoshBurton412","password8",
        UUID.randomUUID(),"JoshBurton@gmail.com","Apple","514-888-8888",new RobotFleet(),
        new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(), 4, new ArrayList<Component>());

        Supplier user14 = new Supplier("Sophie","Moreno","SophieMoreno723","password9",
        UUID.randomUUID(),"SophieMoreno@gmail.com","Google","514-999-9999",new RobotFleet(),
        new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(), 5, new ArrayList<Component>());

        Supplier user15 = new Supplier("Edward","Blair","EdwardBlair413","password10",
        UUID.randomUUID(),"EdwardBlair@gmail.com","No Company","514-123-4567",new RobotFleet(),
        new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(), 8, new ArrayList<Component>());


        //hard code componentTags
        ArrayList<String> componentTags =  new ArrayList<String>();
        componentTags.add("lightweight");
        componentTags.add("high-performance");
        componentTags.add("expensive");
        componentTags.add("waterproof");
        componentTags.add("eco-friendly");
        componentTags.add("durable");

        //hard code Interests
        ArrayList<String> interests = new ArrayList<String>();
        interests.add("Robotics");
        interests.add("Artificial Intelligence");
        interests.add("Machine Learning");
        interests.add("Computer Vision");
        interests.add("Data Science");


        //hard code components
        Component CPU = new Component("CPU", new ArrayList<String>(Arrays.asList("high-performance","lightweight")), (float) 350.0, (float)10.0,(float)1.0,(float)1.0, user11 ,UUID.randomUUID());


        RobotFleet robotFleet1 = new RobotFleet();
        //hard code Robots
        //Robot robot1 = new Robot("Rob Bott", "aquaticRobot", "2021-10-10", "2021-10-11", "Montreal", "Canada", "10:00", "12:00", user1);
        Robot correctRobot1 = new Robot ("Rob Bott", "aquaticRobot", new ArrayList<Component>(), robotFleet1, UUID.randomUUID(), 10, new float[]{(float)0.0,(float)1.0,(float)2.0}, (float) 0.0, (float) 0.0, (float) 16.0);
        //hard code activities
        //Activity activity1 = new Activity("Activity 1", , "2021-10-10", "2021-10-11", "Montreal", "Canada", "10:00", "12:00", user1);
        Activity correcActivity1 = new Activity("Activity 1", correctRobot1, "2024-07-03", "2024-07-03", interests, 0, user10, new ArrayList<Task>());
        //Activity activity2 = new Activity("Activity 2", "Description 2", "2021-10-11", "2021-10-12", "Montreal", "Canada", "10:00", "12:00", user2);
        Activity correcActivity2 = new Activity("Activity 2", correctRobot1, "2025-07-03", "2025-07-03", interests, 0, user10, new ArrayList<Task>());
    }
}