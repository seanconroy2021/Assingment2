package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameAppTest {

    private GameApp gaAppBelowBoundary, gaAppOnBoundary, gaAppAboveBoundary, gaAppInvalidData;

    private Developer developerLego = new Developer("Lego", "www.lego.com");
    private Developer developerSphero = new Developer("Sphero", "www.sphero.com");

    @BeforeEach
    void setUp() {
        //Validation: appSize(1-1000), appVersion(>=1.0), ageRating (0-18), appCost(>=0), Multiplayer(true/false).
        gaAppBelowBoundary = new GameApp(developerLego, "WeDo", 1, 1.0, 0, false);
        gaAppOnBoundary = new GameApp(developerLego, "Spike", 1000, 2.0, 1.99, true);
        gaAppAboveBoundary = new GameApp(developerLego, "EV3", 1001, 3.5, 2.99, false);
        gaAppInvalidData = new GameApp(developerLego, "", -1, 0, -1.00,false);
    }

    @AfterEach
    void tearDown() {
        gaAppBelowBoundary = gaAppOnBoundary = gaAppAboveBoundary = gaAppInvalidData = null;
        developerLego = developerSphero = null;
    }

    @Nested
    class Getters {

        @Test
        void getDeveloper() {
            assertEquals(developerLego, gaAppBelowBoundary.getDeveloper());
            assertEquals(developerLego, gaAppOnBoundary.getDeveloper());
            assertEquals(developerLego, gaAppAboveBoundary.getDeveloper());
            assertEquals(developerLego, gaAppInvalidData.getDeveloper());
        }

        @Test
        void getAppName() {
            assertEquals("WeDo", gaAppBelowBoundary.getAppName());
            assertEquals("Spike", gaAppOnBoundary.getAppName());
            assertEquals("EV3", gaAppAboveBoundary.getAppName());
            assertEquals("", gaAppInvalidData.getAppName());
        }

        @Test
        void getAppSize() {
            assertEquals(1, gaAppBelowBoundary.getAppSize());
            assertEquals(1000, gaAppOnBoundary.getAppSize());
            assertEquals(0, gaAppAboveBoundary.getAppSize());
            assertEquals(0, gaAppInvalidData.getAppSize());
        }

        @Test
        void getAppVersion() {
            assertEquals(1.0, gaAppBelowBoundary.getAppVersion());
            assertEquals(2.0, gaAppOnBoundary.getAppVersion());
            assertEquals(3.5, gaAppAboveBoundary.getAppVersion());
            assertEquals(1.0, gaAppInvalidData.getAppVersion());
        }

        @Test
        void getAppCost() {
            assertEquals(0, gaAppBelowBoundary.getAppCost());
            assertEquals(1.99, gaAppOnBoundary.getAppCost());
            assertEquals(2.99, gaAppAboveBoundary.getAppCost());
            assertEquals(0, gaAppInvalidData.getAppCost());
        }

        @Test
        void isMultiplayer() {
            assertEquals(false, gaAppBelowBoundary.isMultiplayer());
            System.out.println( gaAppOnBoundary.isMultiplayer());
            assertEquals(true, gaAppOnBoundary.isMultiplayer());
            assertEquals(false, gaAppAboveBoundary.isMultiplayer());
            //assertEquals(0, gaAppInvalidData.isMultiplayer());
        }

    }

    @Nested
    class Setters {

        @Test
        void setDeveloper() {
            //no validation in models
            assertEquals(developerLego, gaAppBelowBoundary.getDeveloper());
            gaAppBelowBoundary.setDeveloper(developerSphero);
            assertEquals(developerSphero, gaAppBelowBoundary.getDeveloper());
        }

        @Test
        void setAppName() {
            //no validation in models
            assertEquals("WeDo", gaAppBelowBoundary.getAppName());
            gaAppBelowBoundary.setAppName("Mindstorms");
            assertEquals("Mindstorms", gaAppBelowBoundary.getAppName());
        }

        @Test
        void setAppSize() {
            //Validation: appSize(1-1000)
            assertEquals(1, gaAppBelowBoundary.getAppSize());

            gaAppBelowBoundary.setAppSize(1000);
            assertEquals(1000, gaAppBelowBoundary.getAppSize()); //update

            gaAppBelowBoundary.setAppSize(1001);
            assertEquals(1000, gaAppBelowBoundary.getAppSize()); //no update

            gaAppBelowBoundary.setAppSize(2);
            assertEquals(2, gaAppBelowBoundary.getAppSize()); //update

            gaAppBelowBoundary.setAppSize(0);
            assertEquals(2, gaAppBelowBoundary.getAppSize()); //no update
        }

        @Test
        void setAppVersion() {
            //Validation: appVersion(>=1.0)
            assertEquals(1.0, gaAppBelowBoundary.getAppVersion());

            gaAppBelowBoundary.setAppVersion(2.0);
            assertEquals(2.0, gaAppBelowBoundary.getAppVersion()); //update

            gaAppBelowBoundary.setAppVersion(0.0);
            assertEquals(2.0, gaAppBelowBoundary.getAppVersion()); //no update

            gaAppBelowBoundary.setAppVersion(1.0);
            assertEquals(1.0, gaAppBelowBoundary.getAppVersion()); //update
        }

        @Test
        void setAppCost() {
            //Validation: appCost(>=0)
            assertEquals(0.0, gaAppBelowBoundary.getAppCost());

            gaAppBelowBoundary.setAppCost(1.0);
            assertEquals(1.0, gaAppBelowBoundary.getAppCost()); //update

            gaAppBelowBoundary.setAppCost(-1);
            assertEquals(1.0, gaAppBelowBoundary.getAppCost()); //no update

            gaAppBelowBoundary.setAppCost(0.0);
            assertEquals(0.0, gaAppBelowBoundary.getAppCost()); //update
        }

        @Test
        void setMultiplayer() {
            assertEquals(false, gaAppBelowBoundary.setMultiplayer(false));

            gaAppBelowBoundary.setAppCost(1.0);
            assertEquals(1.0, gaAppBelowBoundary.getAppCost()); //update

            gaAppBelowBoundary.setAppCost(-1);
            assertEquals(1.0, gaAppBelowBoundary.getAppCost()); //no update

            gaAppBelowBoundary.setAppCost(0.0);
            assertEquals(0.0, gaAppBelowBoundary.getAppCost()); //update

        }

    }
}
