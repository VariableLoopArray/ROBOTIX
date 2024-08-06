# ROBOTIX SYSTEM - README 

## Description
Robotix est un système de gestion de robots où les utilisateurs peuvent enregistrer différents robots pour exécuter diverses activités. Ce logiciel permet également aux fournisseurs de vendre des composants de robots. Les utilisateurs peuvent profiter d'une longue liste de fonctionnalités, incluant, mais ne se limitant pas à, la visualisation des tâches, la création de robots, l'achat de composants et la participation à diverses activités. La plateforme est conçue pour offrir des activités ludiques et enseigner différentes compétences telles que l'apprentissage des langues, la programmation informatique, les mathématiques, etc. Finalement, la créativité est sans fin sur une plateforme aussi vaste et flexible.

## Fonctionnalités
- Se connecter
  - Gérer Profile
    - Visualiser mes données
    - Gérer mes données
      - Changer de nom d'utilisateur
      - Changer de mot de passe
      - Changer d'adresse courriel
      - Changer de nom d'entrepise
      - Changer de numéro de téléphone
  - Activité (seulement Client)
    - Ajouter une activité
    - Modifier une activité
    - Supprimer une activité
    - Créer une activité
    - Visualiser mes activités
  - Flotte de Robots (seulement Client)
    - Voir mes robots
    - Créer un robot
    - Supprimer une robot
    - Examiner un robot
  - Inventaire
    - Voir mon inventaire
    - Ajouter une composante de mon inventaire (seulement Fournisseur)
    - Modifier une Composante (seulement Fournisseur)
    - Supprimer une Composante (seulement Fournisseur)
  - Achat
    - Afficher Shop (tous les composants des Fournisseurs)
    - Acheter Composant
    - Filtrer Composants
  - Inbox
    - Recevoir des Notifications (Création d'activités avec les mêmes intérêts pour les clients & Composant vendu pour fournisseur)
    - Recevoir Emails (Lien de confirmation & potentiellement notification si l'utilisateur choisit)
  - Se Déconnecter
- Créer un compte (Client & Fournisseur)
- Gérer Temps
    - Afficher le temps actuel
    - Fixer une date pour le système

### Juste pour clarifier certaines fonctionalite:
- Dans la page de profil, lorsque vous modifiez vos informations, les changements ne sont pas appliqués en temps réel. Pour voir les modifications, retournez à la page d'accueil, puis revenez à la page de profil.
- Lors de la création d'un compte, que ce soit en tant que client ou fournisseur, vous recevrez un email dans la section inbox de votre compte. Cet email contient un bouton sur lequel il faut cliquer pour éviter que votre compte soit supprimé 24 heures après la création. Un message dans vos notifications vous confirmera que vous avez bien cliqué sur le bouton. Notez que vous pouvez cliquer plusieurs fois, mais cela ne changera plus rien après la première confirmation.
- Sur la page de connexion, un bouton permet de changer la date du système. Cela permet de simuler le système à une autre date, par exemple, pour tester la suppression d'un compte non confirmé en ajoutant un jour. De plus, la modification de la date permet aussi de changer le statut des activités.
- Lors de la création d'une activité, une tâche doit être accompagnée d'une description, sinon elle ne sera pas créée.
    
## Organisation des fichiers
```bash

├───Analyse
│   │   action.svg
│   │   activity.svg
│   │   activityDiagram.html
│   │   analyse.html
│   │   classDiagram.html
│   │   component.svg
│   │   principal.svg
│   │   rapport2.html
│   │   robot.svg
│   │   sequenceDiagram.html
│   │   stats1.png
│   │   stats2.png
│   │   stats3.png
│   │   task.svg
│   │
│   └───fichiersVPP
│           activityAction.vpp
│           activityBookActivity.vpp
│           activityBuyComponent.vpp
│           activityPrincipal.vpp
│           activityRegisterRobot.vpp
│           activityTask.vpp
│           classDiagram.vpp
│           sequenceAction.vpp
│           sequenceBookActivity.vpp
│           sequenceBuyComponent.vpp
│           sequenceRegisterRobot.vpp
│           sequenceTask.vpp
│
├───Application
│   ├───.idea
│   │       .gitignore
│   │       aws.xml
│   │       Devoir3.iml
│   │       misc.xml
│   │       modules.xml
│   │       vcs.xml
│   │
│   └───Robotix
│       │   .gitignore
│       │   mvnw
│       │   mvnw.cmd
│       │   pom.xml
│       │
│       ├───.idea
│       │       .gitignore
│       │       compiler.xml
│       │       encodings.xml
│       │       jarRepositories.xml
│       │       misc.xml
│       │       uiDesigner.xml
│       │       vcs.xml
│       │       workspace.xml
│       │
│       ├───.mvn
│       │   └───wrapper
│       │           maven-wrapper.jar
│       │           maven-wrapper.properties
│       │
│       ├───lib
│       │       error_prone_annotations-2.27.0.jar
│       │       gson-2.11.0.jar
│       │
│       ├───src
│          ├───main
│          │   ├───java
│          │   │   │   module-info.java
│          │   │   │
│          │   │   ├───Controller
│          │   │   │       ActivityController.java
│          │   │   │       ComponentController.java
│          │   │   │       CreateAccountController.java
│          │   │   │       HomepageController.java
│          │   │   │       InboxController.java
│          │   │   │       LoginController.java
│          │   │   │       ProfileController.java
│          │   │   │       RobotController.java
│          │   │   │       ShopController.java
│          │   │   │
│          │   │   ├───main
│          │   │   │       Robotix.java
│          │   │   │
│          │   │   └───Model
│          │   │       │   Activity.java
│          │   │       │   Component.java
│          │   │       │   Robot.java
│          │   │       │   Task.java
│          │   │       │   User.java
│          │   │       │
│          │   │       └───TypeOfUsers
│          │   │               Client.java
│          │   │               Supplier.java
│          │   │
│          │   ├───JsonFiles
│          │   │       activities.json
│          │   │       activitiesFinal.json
│          │   │       client.json
│          │   │       clientFinal.json
│          │   │       currentDate.json
│          │   │       supplier.json
│          │   │       supplierFinal.json
│          │   │
│          │   └───resources
│          │       ├───CssFiles
│          │       │       Activity.css
│          │       │       Component.css
│          │       │       Homepage.css
│          │       │       Inbox.css
│          │       │       LoginAndCreate.css
│          │       │       Profile.css
│          │       │       Robot.css
│          │       │       Shop.css
│          │       │
│          │       ├───FxmlPages
│          │       │       AccountCreationMenu.fxml
│          │       │       ActivityMenu.fxml
│          │       │       HomepageMenu.fxml
│          │       │       InboxMenu.fxml
│          │       │       LoginMenu.fxml
│          │       │       MyComponentsMenu.fxml
│          │       │       ProfileMenu.fxml
│          │       │       RobotMenu.fxml
│          │       │       ShopMenu.fxml
│          │       │
│          │       └───Images
│          │               DefaultProfilePic.png
│          │               HomepageBackground.jpg
│          │               LoginBackground.jpg
│          │
│          └───test
│              └───java
│                  └───Controller
│                          ActivityControllerTest.java
│                          ComponentControllerTest.java
│                          CreateAccountControllerTest.java
│                          ProfileControllerTest.java
│                          RobotControllerTest.java
│                          ShopControllerTest.java
│       
│     
├───Conception
│       actionSequence.svg
│       bookActivity.svg
│       buyComponent.svg
│       classDiagram.svg
│       robotRegistration.svg
│       taskSequence.svg
│
├───Exigences
│       besoinNF.html
│       cu.html
│       glossaire.html
│       rapport1.html
│       risque.html
│       tache1.png
│       tache2.svg
│
└───Rapport
    │   rapportFinal.html
    │
    └───VPPFiles
            SequenceActionFinal.svg
            SequenceBuyComponentFinal.svg
            SequenceCreateActivity.svg
            SequenceCreateRobot.svg
            SequenceTaskFinal.svg

```
## Données de départ

Un bouton a été implémenté sur la page de connexion pour afficher les utilisateurs pré-configurés. :D

## Instruction pour installer le projet

1. **Installer Java 22 (JDK 22)**  
   Assurez-vous que JDK 22 est installé sur votre système.
2. **Installer Maven 3 (préférablement 3.11.0)**
    Assurez-vous que Maven est installé sur votre système.

## Instructions pour exécuter le Projet dans IntelliJ IDEA

1. **Ajouter `pom.xml` comme Projet Maven**  
   - Faites un clic droit sur le fichier `pom.xml`.
   - Sélectionnez « Ajouter comme Projet Maven ».

2. **Configurer le Répertoire de Travail**  
   - Allez en haut à droite de l'interface et cliquez sur la configuration actuelle.
   - Sélectionnez « Modifier les Configurations ».
   - Définissez le **Répertoire de Travail** (Working Directory) sur le dossier `Application/Robotix`.

3. **Définir la Classe Principale**  
   - Allez en haut à droite de l'interface et cliquez sur la configuration actuelle.
   - Sélectionnez « Modifier les Configurations ».
   - Réglez la **Classe Principale** (Main Class) à `main.Robotix`.

4. **Ajouter les Options JVM**  
   - Allez en haut à droite de l'interface et cliquez sur la configuration actuelle.
   - Sélectionnez « Modifier les Configurations ».
   - Ajoutez les options suivantes dans les **Options VM** :
     ```
     --add-opens java.base/java.time=com.google.gson
     ```


- Pour exécuter les tests, ajoutez les options JVM `--add-opens java.base/java.time=com.google.gson` pour chaque classe de test que vous voulez éxécuter.
  
- Pour exécuter le fichier JAR, assurez-vous d'avoir installé JDK 22 et toutes les dépendances mentionnées ci-dessus. Ensuite, cliquez simplement sur le fichier JAR ou naviguez jusqu'au répertoire contenant le fichier JAR et exécutez la commande suivante :
  ```
  java -jar Robotix.jar
  ```

