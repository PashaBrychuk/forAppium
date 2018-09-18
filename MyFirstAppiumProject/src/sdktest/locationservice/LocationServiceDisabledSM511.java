package sdktest.locationservice;

import org.junit.Test;
import sdktest.AppiumSetUp;
import sdktest.BLESDKProjectMethods;
import sdktest.MainPage;

import java.io.IOException;

public class LocationServiceDisabledSM511 extends AppiumSetUp {

    private String ScanStatus = "LOCATION_SERVICES_UNAVAILABLE";

    @Test
    public void CheckLocationServices() throws IOException {

        // Test case SM-5-11 Verify that correct status and correct message are displayed when you try to scan when Location service is disabled

        MainPage mainPage = new MainPage(driver);

        BLESDKProjectMethods.DisableLocalService();
        mainPage.PressScanButton();

        mainPage.ScanScatusCheck(ScanStatus);


       BLESDKProjectMethods.EnableLocalService();

    }
}
