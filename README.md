# ROBOTIX SYSTEM - README 

## Description
Robotix est un système de gestion de robots où les utilisateurs peuvent enregistrer différents robots pour exécuter diverses activités. Ce logiciel permet également aux fournisseurs de vendre des composants de robots. Les utilisateurs peuvent profiter d'une longue liste de fonctionnalités, incluant, mais ne se limitant pas à, la visualisation des tâches, le contrôle des robots, le signalement d'erreurs et la participation à certaines activités. La plateforme est conçue pour offrir des activités ludiques et enseigner différentes compétences telles que l'apprentissage des langues, la programmation informatique, les mathématiques, etc. Finalement, la créativité est sans fin sur une plateforme aussi vaste et flexible.

## Fonctionnalités
- Se connecter
  - Gérer Profile
    - Visualiser mes données
    - Gérer mes données
      - Changer de nom
      - Changer de prénom
      - Changer de nom d'utilisateur
      - Changer de mot de passe
      - Changer d'adresse courriel
      - Changer de nom d'entrepise
      - Changer de numéro de téléphone
      - Follow quelqu'un
      - Unfollow quelqu'un
      - Retour
    - Retour
  - Gérer Portefeuille
    - Regarder la quantité d'argent dans mon portefeuille
    - Ajouter de l'argent dans mon compte
    - Retour
  - Gérer Commandes
    - Voir mes commandes (Voir mes ventes pour Fournisseur)
    - Commander une composante (seulement Client)
    - Retour
  - Gérer Activité
    - Ajouter une activité
    - Modifier une activité
    - Supprimer une activité
    - Créer une activité
    - Visualiser mes activités
    - Ajouter un intérêt (seulement Client)
    - Supprimer un intérêt (seulement Client)
    - Afficher mes intérêts (seulement Client)
    - Retour
  - Gérer Flotte de Robots
     - Voir mes robots
     - Créer un robot
     - Supprimer une robot
     - Examiner un robot
     - Retour
  - Gérer Inventaire
      - Voir mon inventaire
      - Enlever une composantes de mon inventaire
      - Ajouter une composante de mon inventaire (seulement Fournisseur)
      - Retour
  - Gérer Temps
      - Afficher le temps actuel
      - Sauter d'une journée
      - Retour
  - Se Déconnecter
- Créer un compte
- Commandes publiques
- Quitter

Quelques commentaires que nous aimerions ajouter pour une meilleure compréhension de notre système :

- Il est possible que nous ayons omis d'ajouter des blocs try-catch à certains endroits, étant donné le grand nombre de fonctionnalités. Nous avons fait de notre mieux pour essayer de tout tester. De plus, plusieurs méthodes peuvent être désorganisées car il s'agit encore d'un prototype. Nous sommes toujours en train de discuter de la structure MVC, ce qui peut parfois être déroutant, et certaines méthodes peuvent ne pas être cohérentes avec d'autres du même type dans notre code.

- Notre code contient plusieurs instances de scanners non fermées. Cela est dû au fait que nous avons initialement commencé à coder de cette manière. Cependant, nous avons découvert plus tard dans notre processus de développement qu'il n'est pas possible de fermer un scanner même s'il est initialisé dans une méthode différente (en Java, une fois qu'un objet Scanner est fermé, il ne peut pas être réouvert pour lire de nouvelles entrées). Ainsi, nous avons plusieurs scanners initialisés qui restent ouverts sans être fermés correctement.

- Pour créer une activité, il est nécessaire d'avoir un robot.

- Lors de la commande d'une composante, la livraison est fixée à 5 jours. Cela signifie que vous devez accéder à "Gérer le temps" et avancer de 6 jours pour que le statut de la commande passe à "livré". De même, les commandes ne disparaissent pas de la section "Voir mes commandes" mais changent simplement de statut pour permettre le suivi des commandes passées.
  
- En ce qui concerne les activités, nous avons noté que le système Robotix propose des activités que les utilisateurs peuvent ajouter à leur compte. Les utilisateurs peuvent également créer des activités pour Robotix, devenant ainsi les créateurs. Cependant, ils doivent ajouter ces activités à leur compte eux-mêmes (elles ne sont pas ajoutées automatiquement). Seuls les créateurs des différentes activitées peuvent modifier ces dernières.
  
- Nous avions l'intention de coder plusieurs autres fonctionnalités : déduction d'argent de l'utilisateur lorsqu'il commande des composants, vérification de diverses méthodes telles que le mot de passe, les tâches, les activités, etc., système de gestion de la batterie pour les activités en cours qui utilisent un certain nombre de batteries des robots par jour, système de points pour les activités (actuellement sans signification), système de gestion de l'utilisation du CPU et de la mémoire (statique pour le moment), et création de robots avec des composants retirés des utilisateurs. (mais nous manquons de temps pour le moment !)

- Lorsque nous demandons un nom, veuillez écrire le nom demandé. Si nous demandons un numéro, veuillez écrire le numéro correspondant (Lisez bien!).
    
## Organisation des fichiers
```bash
├── Analyse/
│ ├── fichiersVPP/
│ │ ├── activityAction.vpp
│ │ ├── activityBookActivity.vpp
│ │ ├── activityBuyComponent.vpp
│ │ ├── activityPrincipal.vpp
│ │ ├── activityRegisterRobot.vpp
│ │ ├── activityTask.vpp
│ │ ├── classDiagram.vpp
│ │ ├── sequenceAction.vpp
│ │ ├── sequenceBookActivity.vpp
│ │ ├── sequenceBuyComponent.vpp
│ │ ├── sequenceRegisterRobot.vpp
│ │ └── sequenceTask.vpp
│ ├── action.svg
│ ├── activity.svg
│ ├── activityDiagram.html
│ ├── analyse.html
│ ├── classDiagram.html
│ ├── component.svg
│ ├── principal.svg
│ ├── rapport2.html
│ ├── robot.svg
│ ├── sequenceDiagram.html
│ ├── stats1.png
│ ├── stats2.png
│ ├── stats3.png
│ └── task.svg
├── Conception/
│ ├── actionSequence.svg
│ ├── bookActivity.svg
│ ├── buyComponent.svg
│ ├── classDiagram.svg
│ ├── robotRegistration.svg
│ └── taskSequence.svg
├── Exigences/
│ ├── besoinNF.html
│ ├── cu.html
│ ├── glossaire.html
│ ├── rapport1.html
│ ├── risque.html
│ ├── tache1.png
│ └── tache2.svg
├── Implémentation/
│ ├── Controllers/
│ │ ├── ActivityController.java
│ │ ├── AdminController.java
│ │ ├── LoginController.java
│ │ ├── RobotController.java
│ │ ├── TimeController.java
│ │ └── UserController.java
│ ├── Database/
│ │ └── Database.java
│ ├── Models/
│ │ ├── typeOfRobots/
│ │ │ ├── AerialRobot.java
│ │ │ ├── AquaticRobot.java
│ │ │ └── TerrestrialRobot.java
│ │ ├── Action.java
│ │ ├── Activity.java
│ │ ├── Client.java
│ │ ├── Component.java
│ │ ├── Order.java
│ │ ├── Robot.java
│ │ ├── RobotFleet.java
│ │ ├── Supplier.java
│ │ ├── Task.java
│ │ └── User.java
│ ├── Views/
│ │ ├── ActivityMenu.java
│ │ ├── Menu.java
│ │ ├── RobotFleet.java
│ │ └── UserMenu.java
│ └── Main.java
└── README.md
```

Nous n'avons pas inclus le fichier JAR et tous les fichiers connexes dans la structure des fichiers parce qu'IntelliJ crée plusieurs fichiers supplémentaires que nous n'avons pas écrits et qui ne proviennent pas de nous.

(En ce qui concerne les types de robots, nous avons réalisé un peu tard qu'il aurait été nécessaire d'intégrer des types abstraits dans notre diagramme de classe. Modifier notre code pour rendre la classe Utilisateur abstraite pour inclure Client et Fournisseur a été très difficile, donc nous avons implémenté ces types de robots. Plus tard, nous avons appris que le prototype ne nécessitait pas forcément de correspondre exactement à nos diagramme  :C )


Pour exécuter le code source, vous pouvez soit utiliser un IDE qui permet de le lancer directement à partir de la classe Main, soit le compiler et l'exécuter depuis le répertoire Implementation.

Pour exécuter un fichier JAR, installez la dernière version de Java, allez dans le répertoire où se trouve le fichier JAR, et écrivez la commande "java -jar Implementation.jar".

