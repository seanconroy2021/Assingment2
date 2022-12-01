package models;

public class ProductivityApp extends App {

    public ProductivityApp(Developer developer, String appName, double appSize, double appVersion, double appCost) {
        super(developer, appName, appSize, appVersion, appCost);
    }

    @Override
    public boolean isRecommendedApp() {
        if((super.getAppCost()>= 1.99) && (super.calculateRating()> 3.0 ))
        {
            return true;
        }
        else
        {
            return false;
        }
    }


}
