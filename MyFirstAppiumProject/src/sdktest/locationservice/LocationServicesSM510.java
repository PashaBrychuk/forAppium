package sdktest.locationservice;

import org.junit.Test;
import sdktest.AppiumSetUp;
import sdktest.BLESDKProjectMethods;
import sdktest.SettingsPage;

import java.io.IOException;

public class LocationServicesSM510 extends AppiumSetUp {

    private String LocationCheck = "Enabled";
    private String LocationCheckDisabled = "Disabled";

    @Test
    public void CheckLocationServices() throws IOException {

        // Test case SM-5-10 Verify that Location service status is checked on SDK side


        SettingsPage settingsPage = new SettingsPage(driver);

        BLESDKProjectMethods.MoveToSettingsTab(driver);


        settingsPage.LocationServiceCheck(LocationCheck);


        BLESDKProjectMethods.DisableLocalService();


        settingsPage.LocationServiceCheck(LocationCheckDisabled);


       BLESDKProjectMethods.EnableLocalService();

    }
}
