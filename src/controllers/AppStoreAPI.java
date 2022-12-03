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

    //---------------------
    // Validation methods
    //---------------------

    public boolean isValidIndex(int index) {
        return (index >= 0) && (index < apps.size());

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
    public String  listAllGamesApp()
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