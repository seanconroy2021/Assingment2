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

//        @Test
//        void setAppCost() {
//            //Validation: appCost(>=0)
//            assertEquals(0.0, gaAppBelowBoundary.getAppCost());
//
//            gaAppBelowBoundary.setAppCost(1.0);
//            assertEquals(1.0, gaAppBelowBoundary.getAppCost()); //update
//
//            gaAppBelowBoundary.setAppCost(-1);
//            assertEquals(1.0, gaAppBelowBoundary.getAppCost()); //no update
//
//            gaAppBelowBoundary.setAppCost(0.0);
//            assertEquals(0.0, gaAppBelowBoundary.getAppCost()); //update
//        }



    }

    @Nested // mutiple test in the one class.
    class ObjectStateMethods {

        @Test
        void appSummaryReturnsCorrectString() {
            EducationApp edApp = setupEducationAppWithRating(3, 4);
            String stringContents = edApp.appSummary();

            assertTrue(stringContents.contains("level " + edApp.getLevel()));
            assertTrue(stringContents.contains(edApp.getAppName() + "(V" + edApp.getAppVersion()));
            assertTrue(stringContents.contains(edApp.getDeveloper().toString()));
            assertTrue(stringContents.contains("€" + edApp.getAppCost()));
            assertTrue(stringContents.contains("Rating: " + edApp.calculateRating()));
        }

        @Test
        void toStringReturnsCorrectString() {
            EducationApp edApp = setupEducationAppWithRating(3, 4);
            String stringContents = edApp.toString();

            assertTrue(stringContents.contains(edApp.getAppName()));
            assertTrue(stringContents.contains("(Version " + edApp.getAppVersion()));
            assertTrue(stringContents.contains(edApp.getDeveloper().toString()));
            assertTrue(stringContents.contains(edApp.getAppSize() + "MB"));
            assertTrue(stringContents.contains("Cost: " + edApp.getAppCost()));
            assertTrue(stringContents.contains("Level: " + edApp.getLevel()));
            assertTrue(stringContents.contains("Ratings (" + edApp.calculateRating()));

            //contains list of ratings too
            assertTrue(stringContents.contains("John Doe"));
            assertTrue(stringContents.contains("Very Good"));
            assertTrue(stringContents.contains("Jane Doe"));
            assertTrue(stringContents.contains("Excellent"));
        }

    }

    @Nested
    class RecommendedApp {

        @Test
        void appIsNotRecommendedWhenInAppCostIs99c() {
            //setting all conditions to true with ratings of 3 and 4 (i.e. 3.5)
            EducationApp edApp = setupEducationAppWithRating(1, 2);

            //now setting appCost to 0.99 so app should not be recommended now
            edApp.setAppCost(0.99);
            assertFalse(edApp.isRecommendedApp());
        }

        @Test
        void appIsNotRecommendedWhenRatingIsLessThan3AndAHalf() {
            //setting all conditions to true with ratings of 3 and 3 (i.e. 3.0)
            GameApp gaApp  =setupGamingAppWithRating(3, 3);
            //verifying recommended app returns false (rating not high enough
            assertFalse(gaApp.isRecommendedApp());
        }

        @Test
        void appIsNotRecommendedWhenNoRatingsExist() {
            //setting all conditions to true with no ratings
            GameApp gaApp  = new GameApp(developerLego, "WeDo", 1,
                    1.0, 1.00,  true);
            //verifying recommended app returns true
            assertFalse(gaApp.isRecommendedApp());
        }

        @Test
        void appIsRecommendedWhenAllOfTheConditionsAreTrue() {
            //setting all conditions to true with ratings of 3 and 4 (i.e. 3.5)
            GameApp gaApp = setupGamingAppWithRating(3, 4);

            //verifying recommended app returns true
            assertTrue(gaApp.isRecommendedApp());
        }

    }

    GameApp setupGamingAppWithRating(int rating1, int rating2) {
        //setting all conditions to true
        GameApp gaApp = new GameApp(developerLego, "WeDo", 1,
                1.0, 1.00,  true);
        gaApp.addRating(new Rating(rating1, "John Doe", "Very Good"));
        gaApp.addRating(new Rating(rating2, "Jane Doe", "Excellent"));

        //verifying all conditions are true for a recommended gaming app
        assertEquals(2, gaApp.getRatings().size());  //two ratings are added
        assertEquals(((rating1 + rating2) / 2.0), gaApp.calculateRating(), 0.01);
        assertTrue(gaApp.isMultiplayer());

        return gaApp;
    }


}
