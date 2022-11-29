package models;
public class EducationApp extends App {

    int level = 0;

    public EducationApp(Developer developer, String appName, double appSize, double appVersion, double appCost, int level) {
        super(developer, appName, appSize, appVersion, appCost);
        setLevel(level);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public boolean isRecommendedApp() {
       if(getAppCost() >0.99 && calculateRating()>=3.50 && getLevel()>3)
       {
           return true;
       }
       else
       {
           return false;
       }
    }

    public String appSummary()
    {
        return super.appSummary()+getLevel();
    }

    public String toString()
    {
        return super.toString()+getLevel();
    }







}
