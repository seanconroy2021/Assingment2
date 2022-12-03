package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.*;
import utils.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.random;
import static utils.RatingUtility.generateRandomRating;

public class AppStoreAPI {

    private List<App> apps = new ArrayList<App>();

    //TODO refer to the spec and add in the required methods here (make note of which methods are given to you first!)


    // Method to simulate ratings (using the RatingUtility).
    // This will be called from the Driver (see skeleton code)

    public void simulateRatings(){
        for (App app :apps) {
            app.addRating(generateRandomRating());
        }
    }

    /**
     *
     * @param appName
     * @return
     */
    public App getAppByName(String appName)
    {
        if (isValidAppName(appName))
        {
            for (App app : apps)
            {
                if (app.getAppName().toLowerCase().contains(appName.toLowerCase())) {
                    return app;
                }
            }
        }

       return null;
    }

    /**
     *
     * @param index
     * @return
     */
    public App getAppByIndex(int index)
    {
        if(isValidIndex(index)) {return  apps.get(index);}
        else {return null;}
    }

    //---------------------
    // Validation methods
    //---------------------

    /**
     * This method takes in a number and checks if it is a valid index in the app ArrayList.
     *
     * @param index A number representing a potential index in the ArrayList.
     * @return True of the index number passed is a valid index in the ArrayList, false otherwise.
     */
    public boolean isValidIndex(int index) {
        return (index >= 0) && (index < apps.size());

    }


    /**
     * This method check to see if the app  is in the system by the app name.
     * send in parameter String of app looking for and if found in the arraylist true is sent back else false.
     * @param appName A app name representing a potential app name in the arraylist.
     * @return true if the app name is found, false otherwise.
     */
    public boolean isValidAppName(String appName)
    {
        for (App app : apps)
        {
            if (app.getAppName().toLowerCase().contains(appName.toLowerCase()))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * This will get the number of apps in the arraylist and return it in int.
     * @return The number of apps in the arraylist in int.
     */
    public int numberOfApps()
    {
        return apps.size();
    }



    /**
     *  Add the app object, passed as a parameter, to the ArrayList
     * @param app  object to be added to the ArrayList.
     * @return If the app object has been successful it will send back true else false.
     */
    public boolean addApp(App app)
    {
        return apps.add(app);
    }


    /**
     * This method builds and returns a String containing all the app in the ArrayList.
     * For each app stored, the associated index number is included.
     * If no apps are stored in the ArrayList, the String "No apps in the system" is returned.
     *
     * @return A String containing all the app in the ArrayList, or "No app in the store",
     * if empty.
     */
    public String listAllApps()
    {
        if(apps.isEmpty())
        {
            return "no apps in the system";
        }
        else
        {
            String listofApps="";
            {
                for (int i = 0; i < apps.size(); i++)
                {
                    listofApps += i+ ": "+ apps.get(i);
                }

                return listofApps;
            }
        }

    }

    /**
     * This method builds and returns a String containing all the app summary in the arraylist.
     * If no apps are stored in the arraylist, the string "no app in the system".
     *
     * @retun A string containing all the app summaries or "no app in the system".
     */
    public String listSummaryOfAllApps()
    {
        if(apps.isEmpty())
        {
            return "no apps in the system";
        }
        else
        {
            String summaryOfApps="";
            for (App app : apps)
            {
               summaryOfApps += app.appSummary();
            }

            return summaryOfApps;
        }


    }

    /**
     * This method builds and returns a String containing all game apps in the ArrayList.
     * For each game app  stored, the associated index number is included.
     * If no apps  are stored in the ArrayList, the String "No apps in the system" is returned " is returned.
     * If no game app are stored in the ArrayList, the String "no game apps in the system".
     *
     * @return A String containing all the game app  in the ArrayList, or  "No apps in the system",or "no game apps in the system"
     *
     */
    public String  listAllGameApps()
    {
        String listOfGameApps ="";
        if(apps.isEmpty())
        {
            return "no apps in the system";
        }
        else
        {
            for (App app : apps)
            {
                if(app instanceof GameApp)
                {
                    int index = apps.indexOf(app);
                    listOfGameApps += index +": " + app.toString();
                }
            }
        }

        if(listOfGameApps.equals(""))
        {
            return "no game apps in the system";
        }
        else
        {
            return listOfGameApps;
        }
    }

    /**
     * This method builds and returns a String containing all education apps in the ArrayList.
     * For each education app  stored, the associated index number is included.
     * If no apps  are stored in the ArrayList, the String "No apps in the system" is returned.
     * If no education app are stored in the ArrayList, the String "no education apps in the system".
     *
     * @return A String containing all the education app  in the ArrayList, or  "No apps in the system",or "no education apps in the system"
     *
     */

    public String  listAllEducationApps()
    {
        String listOfEducationApps ="";
        if(apps.isEmpty())
        {
            return "no apps in the system";
        }
        else
        {
            for (App app : apps)
            {
                if(app instanceof EducationApp)
                {
                    int index = apps.indexOf(app);
                    listOfEducationApps += index +": " + app.toString();
                }
            }
        }

        if(listOfEducationApps.equals(""))
        {
            return "no education apps in the system";
        }
        else
        {
            return listOfEducationApps;
        }
    }

    /**
     * This method builds and returns a String containing all productivity apps in the ArrayList.
     * For each productivity app  stored, the associated index number is included.
     * If no apps  are stored in the ArrayList, the String "No apps in the system" is returned.
     * If no productivity app are stored in the ArrayList, the String "no productivity apps in the system".
     *
     * @return A String containing all the productivity  app  in the ArrayList, or  "No apps in the system",or "no productivity apps in the system"
     *
     */

    public String  listAllProductivityApps()
    {
        String listOfProductivityApps ="";
        if(apps.isEmpty())
        {
            return "no apps in the system";
        }
        else
        {
            for (App app : apps)
            {
                if(app instanceof ProductivityApp)
                {
                    int index = apps.indexOf(app);
                    listOfProductivityApps += index +": " + app.toString();
                }
            }
        }

        if(listOfProductivityApps.equals(""))
        {
            return "no productivity apps in the system";
        }
        else
        {
            return listOfProductivityApps;///?
        }
    }

    /**
     * It searches for an app by app name . A String searchString is sent in as a parameter
     * @param searchString A String searchString is sent in as parameter & what looking for in
     * the app name in the arrayList.
     * @return If the app is found by app name a String is built up with all the app that contain the searchString, and it is returned.
     * if there is no apps in the system "no apps in the system" or if no apps match the searchString "No apps found for" + searchString is sent back.
     *
     */
    public String listAllAppsByName(String searchString)
    {
        String foundApp = "";
        if(apps.isEmpty())
        {
            return "no apps in the system";
        }
        else
        {
            for (App app : apps)
            {
                if(app.getAppName().toLowerCase().contains(searchString.toLowerCase()))
                {
                    foundApp += app.toString();
                }
            }
        }

        if (foundApp.equals("")) {
            return "No apps found for" + ": " + searchString;
        }
        else
        {
            return foundApp;
        }

    }

    /**
     * It searches for app that have a rating which is sent in as a parameter that equal or above and add it to a built up string
     * and returned. If no apps in the system "no apps in the system" is returned or if no app what that rating "No apps have a rating of " +rating+" or above"
     * is sent back.
     * @param rating A int is sent in as a parameter & if the rating is the equal or above the app it added to the string.
     *
     * @return A string of all the app that match that rating is sent back or "no app in the system".
     *If no apps have that rating "No apps have a rating of " +rating+" or above" is returned.
     */
    public  String listAllAppsAboveOrEqualAGivenStarRating( int rating )
    {
        String ratingList="";
        if(apps.isEmpty())
        {
            return "no apps in the system";
        }
        else
        {
            for (App app : apps)
            {
                if(app.calculateRating()>= rating ) //  check do i use uilties.
                {
                    int index = apps.indexOf(app);
                    ratingList += index +": " + app.toString();
                }
            }
        }

        if (ratingList.equals(""))
        {
            return "No apps have a rating of " +rating+" or above";
        }
        else
        {
            return ratingList;
        }
    }

    /**
     *It builds  a string of all the recommended app in the system and return it.
     *
     * @return Build a string containing all the recommended apps and return it.
     * If there is no apps in the system "no apps in the system" or if there is no recommended apps it will return "No recommended apps".
     */
    public String listAllRecommendedApps()
    {
        String allRecommendedApps="";
        if(apps.isEmpty())
        {
            return "no apps in the system";
        }
        else
        {
            for (App app : apps)
            {
                if(app.isRecommendedApp())
                {
                    int index = apps.indexOf(app);
                    allRecommendedApps += index +": " + app.toString();
                }
            }
        }

        if(allRecommendedApps.equals(""))
        {
            return "No recommended apps";
        }
        else
        {
            return allRecommendedApps;
        }

    }

    /**
     * It build a string of all the apps with the developer matching and return it
     * @param developer Is a a
     * @return
     */
    public String  listAllAppsByChosenDeveloper(Developer developer)
    {
        String appsByDev="";
        if(apps.isEmpty())
        {
            return "no apps in the system";
        }
        else
        {
            for (App app : apps)
            {
                if (app.getDeveloper().equals(developer))//toDo devloper make check if correct
                {
                    int index = apps.indexOf(app);
                    appsByDev += index +": " + app.toString();
                }
            }
        }

        if (appsByDev.equals(""))
        {
            return "No apps for developer: "+ developer;
        }
        else
        {
            return appsByDev;
        }

    }


















    //---------------------
    // Persistence methods
    //---------------------
    // TODO UNCOMMENT THIS COMPLETED CODE block as you start working through this class
    //---------------------
    /*
    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[]{App.class, EducationApp.class, GameApp.class, ProductivityApp.class, Rating.class};

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader(fileName()));
        apps = (List<App>) in.readObject();
        in.close();
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(fileName()));
        out.writeObject(apps);
        out.close();
    }

    public String fileName(){
        return "apps.xml";
    }
    */
}