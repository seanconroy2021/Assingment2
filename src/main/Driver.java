package main;

import controllers.AppStoreAPI;
import controllers.DeveloperAPI;
import models.App;
import models.Developer;
import models.EducationApp;
import utils.ScannerInput;
import utils.Utilities;

import javax.swing.*;

public class Driver {

    //TODO Some skeleton code has been given to you.
    //     Familiarise yourself with the skeleton code...run the menu and then review the skeleton code.
    //     Then start working through the spec.

    private DeveloperAPI developerAPI = new DeveloperAPI();
    private AppStoreAPI appStoreAPI = new AppStoreAPI();
    private JPanel mainPanel;
    private JTextField appStoreTextField;

    public static void main(String[] args) {
        new Driver().start();
    }

    public void start() {
        loadAllData();
        runMainMenu();
    }

    private int mainMenu() {
        System.out.println("""
                 -------------App Store------------
                |  1) Developer - Management MENU  |
                |  2) App - Management MENU        |
                |  3) Reports MENU                 |
                |----------------------------------|
                |  4) Search                       |
                |  5) Sort                         |
                |----------------------------------|
                |  6) Recommended Apps             |
                |  7) Random App of the Day        |
                |  8) Simulate ratings             |
                |----------------------------------|
                |  20) Save all                    |
                |  21) Load all                    |
                |----------------------------------|
                |  0) Exit                         |
                 ----------------------------------""");
        return ScannerInput.validNextInt("==>> ");
    }

    private void runMainMenu() {
        int option = mainMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> runDeveloperMenu();
                //case 2 ->
                //case 3 -> // TODO run the Reports Menu and the associated methods (your design here)
                case 4 -> searchAppsBySpecificCriteria();
                //case 5 -> // TODO Sort Apps by Name
                //case 6 -> // TODO print the recommended apps
                //case 7 -> // TODO print the random app of the day
                case 8 -> simulateRatings();
                case 20 -> saveAllData();
                case 21 -> loadAllData();
                case 22->  appVersionInput(); //todo-tester case
                default -> System.out.println("Invalid option entered: " + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = mainMenu();
        }
        exitApp();
    }

    private void exitApp() {
        saveAllData();
        System.out.println("Exiting....");
        System.exit(0);
    }
    //--------------------------------------------------
    //  App Management - Menu Items
    //--------------------------------------------------
    private int appMenu()
    {
        System.out.println("""
                 -------App Store Menu-------
                |   1) Add a app             |
                |   2) Update a app          |
                |   3) Delete app            |
                |   0) RETURN to main menu   |
                 ----------------------------""");
        return ScannerInput.validNextInt("==>> ");
    }

    private void runAppMenu() {
        int option = appMenu();
        while (option != 0) {
            switch (option) {
                //case 1 -> //addApp();
                case 2 -> updateApp();
                case 3 -> updateDeveloper();
                case 4 -> deleteApp();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = developerMenu();
        }
    }

    //________________________
    // super App Attributes Input
    //________________________
    private String  appNameInput()
    {
        String appName = ScannerInput.validNextLine("Please enter the app name: ");
          while(appStoreAPI.isValidAppName(appName))
          {
              appName = ScannerInput.validNextLine("Please enter a valid app name: ");
          }

          return appName;
    }

    private int  appSizeInput()
    {
        int appSize = ScannerInput.validNextInt("Please enter the app size: ");
        Utilities.validRange(appSize, 1, 1000);
        while(!Utilities.validRange(appSize, 1, 1000))
        {
            appSize = ScannerInput.validNextInt("Please enter a valid app size (1-1000 MB): ");
        }

        return appSize;
    }

    private double  appVersionInput()
    {
        double appVersion = ScannerInput.validNextDouble("Please enter the version : ");

        while(!Utilities.greaterThanOrEqualTo(appVersion, 1))
        {
            appVersion = ScannerInput.validNextInt("Please enter a valid version greater than one : ");
        }

        return appVersion;
    }

    private double  appVersionInput()
    {
        double appVersion = ScannerInput.validNextDouble("Please enter the version : ");

        while(!Utilities.greaterThanOrEqualTo(appVersion, 1))
        {
            appVersion = ScannerInput.validNextInt("Please enter a valid version greater than one : ");
        }

        return appVersion;
    }



    private String addEducationApp()
    {
        Developer developer =showDeveloperToAddToApp();
        //EducationAppDeveloper developer, String appName, double appSize, double appVersion, double appCost, int level)
        String appName = ScannerInput.validNextLine("Please enter the  app name: ");
        //double appSize =
        //appStoreAPI.addApp(new EducationApp())

        return "";
    }

    private String addGameApp()
    {
        return "";
    }

    private String addProductivtyApp()
    {
        return "";
    }

    private void  updateApp()
    {

    }

    private void  deleteApp()
    {

    }





    //--------------------------------------------------
    //  Developer Management - Menu Items
    //--------------------------------------------------
    private int developerMenu() {
        System.out.println("""
                 -------Developer Menu-------
                |   1) Add a developer       |
                |   2) List developer        |
                |   3) Update developer      |
                |   4) Delete developer      |
                |   0) RETURN to main menu   |
                 ----------------------------""");
        return ScannerInput.validNextInt("==>> ");
    }


    private void runDeveloperMenu() {
        int option = developerMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> addDeveloper();
                case 2 -> System.out.println(developerAPI.listDevelopers());
                case 3 -> updateDeveloper();
                case 4 -> deleteDeveloper();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = developerMenu();
        }
    }

    private void addDeveloper() {
        String developerName = ScannerInput.validNextLine("Please enter the developer name: ");
        String developerWebsite = ScannerInput.validNextLine("Please enter the developer website: ");

        if (developerAPI.addDeveloper(new Developer(developerName, developerWebsite))) {
            System.out.println("Add successful");
        } else {
            System.out.println("Add not successful");
        }
    }

    private void updateDeveloper() {
        System.out.println(developerAPI.listDevelopers());
        Developer developer = readValidDeveloperByName();
        if (developer != null) {
            String developerWebsite = ScannerInput.validNextLine("Please enter new website: ");
            if (developerAPI.updateDeveloperWebsite(developer.getDeveloperName(), developerWebsite))
                System.out.println("Developer Website Updated");
            else
                System.out.println("Developer Website NOT Updated");
        } else
            System.out.println("Developer name is NOT valid");
    }

    private void deleteDeveloper() {
        String developerName = ScannerInput.validNextLine("Please enter the developer name: ");
        if (developerAPI.removeDeveloper(developerName) != null) {
            System.out.println("Delete successful");
        } else {
            System.out.println("Delete not successful");
        }
    }

    private Developer readValidDeveloperByName() {
        String developerName = ScannerInput.validNextLine("Please enter the developer's name: ");
        if (developerAPI.isValidDeveloper(developerName)) {
            return developerAPI.getDeveloperByName(developerName);
        } else {
            return null;
        }
    }

    private Developer  showDeveloperToAddToApp()
    {
        System.out.println(developerAPI.listDevelopers()+"\n");
        Developer developer  = readValidDeveloperByName();

        while (developer == null)
        {
            developer = readValidDeveloperByName();
        }

        return developer;

    }


    //--------------------------------------------------
    // TODO UNCOMMENT THIS CODE as you start working through this class
    //--------------------------------------------------
    private void searchAppsBySpecificCriteria() {
        System.out.println("""
                What criteria would you like to search apps by:
                  1) App Name
                  2) Developer Name
                  3) Rating (all apps of that rating or above)""");
        int option = ScannerInput.validNextInt("==>> ");
        switch (option) {
            // TODO Search methods below
            // case 1 -> searchAppsByName();
            // case 2 -> searchAppsByDeveloper(readValidDeveloperByName());
            // case 3 -> searchAppsEqualOrAboveAStarRating();
            // default -> System.out.println("Invalid option");
        }
    }

    //--------------------------------------------------
    //simulateRatings
    //--------------------------------------------------
    private void simulateRatings() {
         //simulate random ratings for all apps (to give data for recommended apps and reports etc).
        if (appStoreAPI.numberOfApps() > 0) {
            System.out.println("Simulating ratings...");
            appStoreAPI.simulateRatings();
            System.out.println(appStoreAPI.listSummaryOfAllApps());
        } else {
            System.out.println("No apps");
        }
    }

    //--------------------------------------------------
    //  Persistence Menu Items
    //--------------------------------------------------

    private void saveAllData() {

        try {
            appStoreAPI.load();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllData() {
        try {
            appStoreAPI.save();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}