package Database;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import Models.Action;
import Models.Activity;
import Models.Client;
import Models.Component;
import Models.Order;
import Models.Robot;
import Models.RobotFleet;
import Models.Supplier;
import Models.Task;
import Models.User;

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

        //hard code Tasks and Actions
        ArrayList<Action> Arriver = new ArrayList<Action>();
        Arriver.add(new Action("Planifiez votre itinéraire vers le lieu"));
        Arriver.add(new Action("Réservez un moyen de transport ou assurez-vous que votre véhicule est prêt"));
        Arriver.add(new Action("Quittez votre domicile à temps pour arriver à l'heure"));
        Arriver.add(new Action("Enregistrez-vous à l'arrivée"));

        ArrayList<Action> Coder = new ArrayList<Action>();
        Coder.add(new Action("Lisez et comprenez les règles du concours"));
        Coder.add(new Action("Installez votre poste de travail"));
        Coder.add(new Action("Commencez à coder selon les exigences"));
        Coder.add(new Action("Soumettez votre code avant la date limite"));

        ArrayList<Action> Presenter = new ArrayList<Action>();
        Presenter.add(new Action("Préparez vos diapositives de présentation"));
        Presenter.add(new Action("Entraînez-vous à présenter"));
        Presenter.add(new Action("Installez votre matériel de présentation"));
        Presenter.add(new Action("Présentez votre projet aux juges"));

        Task task1ForActivity1 = new Task("Arriver", Arriver);
        Task task2ForActivity1 = new Task("Coder", Coder);
        Task task3ForActivity1 = new Task("Presenter", Presenter);



        ArrayList<Action> Preparer = new ArrayList<>();
        Preparer.add(new Action("Lire la recette en entier"));
        Preparer.add(new Action("Faire une liste des ingrédients nécessaires"));
        Preparer.add(new Action("Acheter les ingrédients manquants"));
        Preparer.add(new Action("Mesurer et préparer les ingrédients"));

        ArrayList<Action> cuisson = new ArrayList<>();
        cuisson.add(new Action("Préchauffer le four ou préparer la cuisinière"));
        cuisson.add(new Action("Suivre les étapes de la recette pour cuire les ingrédients"));
        cuisson.add(new Action("Remuer ou ajuster la cuisson selon les indications"));
        cuisson.add(new Action("Vérifier la cuisson et ajuster le temps si nécessaire"));

        ArrayList<Action> presentationEtService = new ArrayList<>();
        presentationEtService.add(new Action("Préparer les plats ou assiettes de service"));
        presentationEtService.add(new Action("Dresser la table"));
        presentationEtService.add(new Action("Disposer les aliments de manière appétissante"));
        presentationEtService.add(new Action("Servir les plats à table"));

        Task task1ForActivity2 = new Task("Préparation", Preparer);
        Task task2ForActivity2 = new Task("Cuisson", cuisson);
        Task task3ForActivity2 = new Task("Présentation et service", presentationEtService);


        ArrayList<Action> preparerEvenement = new ArrayList<>();
        preparerEvenement.add(new Action("Réserver la salle de concert"));
        preparerEvenement.add(new Action("Engager les musiciens"));
        preparerEvenement.add(new Action("Promouvoir l'événement"));
        preparerEvenement.add(new Action("Vendre des billets"));

        ArrayList<Action> installerEquipement = new ArrayList<>();
        installerEquipement.add(new Action("Installer le système de sonorisation"));
        installerEquipement.add(new Action("Configurer les instruments de musique"));
        installerEquipement.add(new Action("Vérifier les éclairages"));
        installerEquipement.add(new Action("Faire un test de son"));

        ArrayList<Action> gererConcert = new ArrayList<>();
        gererConcert.add(new Action("Accueillir les spectateurs"));
        gererConcert.add(new Action("Superviser l'entrée et la sortie"));
        gererConcert.add(new Action("Assurer la sécurité pendant le concert"));
        gererConcert.add(new Action("Coordonner avec les musiciens et le personnel"));

        Task task1ForActivity3 = new Task("Préparer l'événement", preparerEvenement);
        Task task2ForActivity3 = new Task("Installer l'équipement", installerEquipement);
        Task task3ForActivity3 = new Task("Gérer le concert", gererConcert);



        ArrayList<Action> preparerExploration = new ArrayList<>();
        preparerExploration.add(new Action("Rassembler l'équipement nécessaire"));
        preparerExploration.add(new Action("Vérifier les lampes torches et les batteries"));
        preparerExploration.add(new Action("Porter des vêtements appropriés"));
        preparerExploration.add(new Action("Informer quelqu'un de votre plan d'exploration"));

        ArrayList<Action> descendreDansLaCave = new ArrayList<>();
        descendreDansLaCave.add(new Action("Ouvrir la porte de la cave"));
        descendreDansLaCave.add(new Action("Descendre les escaliers prudemment"));
        descendreDansLaCave.add(new Action("Allumer la lampe torche"));
        descendreDansLaCave.add(new Action("Observer les environs pour tout danger potentiel"));

        ArrayList<Action> explorerLaCave = new ArrayList<>();
        explorerLaCave.add(new Action("Chercher des objets intéressants"));
        explorerLaCave.add(new Action("Prendre des notes ou des photos"));
        explorerLaCave.add(new Action("Éviter les zones dangereuses ou instables"));
        explorerLaCave.add(new Action("Remonter à la surface prudemment"));

        Task task1ForActivity4 = new Task("Préparer l'exploration", preparerExploration);
        Task task2ForActivity4 = new Task("Descendre dans la cave", descendreDansLaCave);
        Task task3ForActivity4 = new Task("Explorer la cave", explorerLaCave);



        ArrayList<Action> preparerLeRangement = new ArrayList<>();
        preparerLeRangement.add(new Action("Rassembler les produits de nettoyage nécessaires"));
        preparerLeRangement.add(new Action("Préparer des sacs pour les déchets et le recyclage"));
        preparerLeRangement.add(new Action("Mettre de la musique motivante"));
        preparerLeRangement.add(new Action("Faire une liste des pièces à ranger"));

        ArrayList<Action> rangerLesObjets = new ArrayList<>();
        rangerLesObjets.add(new Action("Remettre chaque objet à sa place"));
        rangerLesObjets.add(new Action("Trier les objets inutiles à jeter ou à donner"));
        rangerLesObjets.add(new Action("Organiser les placards et les tiroirs"));
        rangerLesObjets.add(new Action("Vider les poubelles"));

        ArrayList<Action> nettoyerLesSurfaces = new ArrayList<>();
        nettoyerLesSurfaces.add(new Action("Dépoussiérer les meubles"));
        nettoyerLesSurfaces.add(new Action("Passer l'aspirateur sur les tapis et les moquettes"));
        nettoyerLesSurfaces.add(new Action("Laver les sols"));
        nettoyerLesSurfaces.add(new Action("Nettoyer les fenêtres et les miroirs"));

        Task task1ForActivity5 = new Task("Préparer le rangement", preparerLeRangement);
        Task task2ForActivity5 = new Task("Ranger les objets", rangerLesObjets);
        Task task3ForActivity5 = new Task("Nettoyer les surfaces", nettoyerLesSurfaces);


        
        //hard code activities
        Activity Activity1 = new Activity("CodingBootCamp", Robot5, "2024-07-03", "2024-09-03", "Éducation et Apprentissage", 150, user10, new ArrayList<Task>(Arrays.asList(task1ForActivity1,task2ForActivity1,task3ForActivity1)));
        Activity Activity2 = new Activity("Recette de cuisine", Robot5, "2024-07-30", "2024-07-30","Éducation et Apprentissage" , 15, user9, new ArrayList<Task>(Arrays.asList(task1ForActivity2,task2ForActivity2,task3ForActivity2)));
        Activity Activity3 = new Activity("Concert de Jazz", Robot3, "2024-08-03", "2024-08-03", "Musique et Arts du Spectacle", 10, user8, new ArrayList<Task>(Arrays.asList(task1ForActivity3,task2ForActivity3,task3ForActivity3)));
        Activity Activity4 = new Activity("Exploration de la cave", Robot1, "2024-08-10", "2024-08-10", "Activité de Plein Air et Aventure", 20, user7, new ArrayList<Task>(Arrays.asList(task1ForActivity4,task2ForActivity4,task3ForActivity4)));
        Activity Activity5 = new Activity("Ranger la maison", Robot2, "2024-08-17", "2024-08-17", "Santé et Bien-être", 10, user6, new ArrayList<Task>(Arrays.asList(task1ForActivity5,task2ForActivity5,task3ForActivity5)));

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