import java.util.UUID;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        
        //hard code users
        Client user1 = new Client("John","Doe","johndoe123","password1",UUID.randomUUID(),"johndoe@gmail.com","No Company","514-111-1111",new RobotFleet(),
        new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<String>(),new ArrayList<Order>());
        Client user2 = new Client("Zane","Underwood","ZaneUnderwood231","password2",UUID.randomUUID(),"ZaneUnderwood@gmail.com","Nvidia","514-222-2222",new RobotFleet(),
        new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<String>(),new ArrayList<Order>());
        Client user3 = new Client("Annabelle","Valdez","AnnabelleValdez562","password3",UUID.randomUUID(),"AnnabelleValdez@gmail.com","No Company","514-333-3333",new RobotFleet(),
        new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<String>(),new ArrayList<Order>());
        Client user4 = new Client("Gianluca","Gray","GianlucaGray135","password4",UUID.randomUUID(),"GianlucaGray@gmail.com","PepsiCo","514-444-4444",new RobotFleet(),
        new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<String>(),new ArrayList<Order>());
        Client user5 = new Client("Jaydon","Sanchez","JaydonSanchez213","password5",UUID.randomUUID(),"JaydonSanchez@gmail.com","No Company","514-555-5555",new RobotFleet(),
        new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(),new ArrayList<String>(),new ArrayList<Order>());
        Supplier user6 = new Supplier("Michael","Oliver","MichaelOliver521","password6",UUID.randomUUID(),"MichaelOliver@gmail.com","No Company","514-666-6666",new RobotFleet(),
        new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(), 10, new ArrayList<Component>());
        Supplier user7 = new Supplier("Frankie","Charles","FrankieCharles178","password7",UUID.randomUUID(),"FrankieCharles@gmail.com","No Company","514-777-7777",new RobotFleet(),
        new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(), 7, new ArrayList<Component>());
        Supplier user8 = new Supplier("Josh","Burton","JoshBurton412","password8",UUID.randomUUID(),"JoshBurton@gmail.com","Apple","514-888-8888",new RobotFleet(),
        new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(), 4, new ArrayList<Component>());
        Supplier user9 = new Supplier("Sophie","Moreno","SophieMoreno723","password9",UUID.randomUUID(),"SophieMoreno@gmail.com","Google","514-999-9999",new RobotFleet(),
        new ArrayList<Activity>(), new ArrayList<User>(), new ArrayList<User>(), 5, new ArrayList<Component>());
        Supplier user10 = new Supplier("Edward","Blair","EdwardBlair413","password10",UUID.randomUUID(),"EdwardBlair@gmail.com","No Company","514-123-4567",new RobotFleet(),
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
        Component CPU = new Component("CPU", new ArrayList<String>(), 350.0, 10.0,1.0,1.0, user6 ,UUID.randomUUID());

        //hard code Robots
        Robot robot1 = new Robot("Rob Bott", "aquaticRobot", "2021-10-10", "2021-10-11", "Montreal", "Canada", "10:00", "12:00", user1);

        //hard code activities
        Activity activity1 = new Activity("Activity 1", , "2021-10-10", "2021-10-11", "Montreal", "Canada", "10:00", "12:00", user1);
        Activity activity2 = new Activity("Activity 2", "Description 2", "2021-10-11", "2021-10-12", "Montreal", "Canada", "10:00", "12:00", user2);
    }
}