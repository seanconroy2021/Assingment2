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
import java.util.Random;

import static java.lang.Math.random;
import static utils.RatingUtility.generateRandomRating;

public class AppStoreAPI implements ISerializer {

    private List<App> apps = new ArrayList<App>();



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
     *This finds the app in the arraylist by the index sent In. If app is found the App object is sent back else null
     * @param index It is a int parameter which is sent in to find the app that matches the index.
     * @return If index is valid and matches an app in the arraylist it is sent back else null.
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
     * Delete a app from the ArrayList, if it exists, at the index passed as a parameter.
     *
     * @param index Index of the App object in the ArrayList
     * @return The deleted App object or null if no object is at the index location
     */
    public App deleteAppByIndex(int index)
    {
        if (isValidIndex(index))
        {
           return  apps.remove(index);
        }
        else
        {
            return null;
        }
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
     * @param developer It is object Developer that is sent in.
     * @return Build a string that contains all the apps by the developer and return it.
     * If there is no qpps "no apps in the system" or if there is no app found from the developer "No apps for developer: " developer is returned.
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

    /**
     * It will count the number of app by a chosen developer. It will take in object Developer and compare it to each app in the arraylist.
     * It will add 1 each time developer matches the app developer sent in.
     * @param developer  It is object Developer that is sent in.
     * @return It will add up all the app matching in int type and send it back else if no apps matches or app is empty send back 0.
     */
    public int numberOfAppsByChosenDeveloper(Developer developer)
    {
        int count = 0;
        if(apps.isEmpty())
        {
            return 0;
        }
        else
        {
            for (App app : apps)
            {
                if (app.getDeveloper().equals(developer))//toDo devloper make check if correct
                {
                   count++;
                }
            }
        }
        return count;
    }

    /**
     * This method will find a random app in the arrayList and send it back as App object.
     * @return An app object is sent back or null if app arraylist is empty.
     */
    public App randomApp()
    {
        if(apps.isEmpty())
        {
            return null;
        }
        else
        {
            //https://java2blog.com/generate-random-number-between-1-and-100-java/
            Random randIndex = new Random();
            int randomIndex = randIndex.ints(0, apps.size()).findAny().getAsInt();
            return apps.get(randomIndex);
        }
    }


    //------------ sorting method ------------//
    public void sortAppsByNameAscending() //TODO
    {
        for (int i = apps.size() -1; i >= 0; i--)
        {
            int highestIndex = 0;
            for (int j = 0; j <= i; j++)
            {
                if (apps.get(j).getAppName().compareTo(apps.get(highestIndex).getAppName()) > 0) {
                    highestIndex = j;
                }
            }
            swapApps((ArrayList<App>) apps, i, highestIndex);//todo

        }
    }

    private void swapApps (ArrayList<App> apps, int i, int j)
    {
        App smaller = apps.get(i);
        App bigger = apps.get(j);

        apps.set(i,bigger);
        apps.set(j, smaller);

    }

    //---------------------
    // Persistence methods
    //---------------------
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
        apps = (ArrayList<App>) in.readObject();
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
}