package models;

import utils.Utilities;

public class GameApp extends App {

    boolean isMultiplayer = false;

    public GameApp(Developer developer, String appName, double appSize, double appVersion, double appCost, boolean isMultiplayer) {
        super(developer, appName, appSize, appVersion, appCost);
    }


    public boolean isMultiplayer() {
        return isMultiplayer;
    }

    public void setMultiplayer(boolean multiplayer) {
        isMultiplayer = multiplayer;
    }

    @Override
    public boolean isRecommendedApp() {
        if (( isMultiplayer()== true ) && (super.calculateRating() > 4.0)) {
            return true;
        } else {
            return false;
        }
    }

    public String appSummary()
    {
        return super.appSummary()+" Multiplayer "+ Utilities.booleanToYN(isMultiplayer());
    }

    public String toString()
    {
        return super.toString()+" Multiplayer: "+ Utilities.booleanToYN(isMultiplayer());
    }

}
