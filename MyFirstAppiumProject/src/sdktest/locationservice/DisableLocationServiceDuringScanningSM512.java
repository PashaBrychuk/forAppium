package sdktest.locationservice;

import org.junit.Test;
import sdktest.AppiumSetUp;
import sdktest.BLESDKProjectMethods;
import sdktest.MainPage;

import java.io.IOException;

public class DisableLocationServiceDuringScanningSM512 extends AppiumSetUp {

    public String ScanStatus = "LOCATION_SERVICES_WAS_DISABLED";

    @Test
    public void CheckLocationServices() throws IOException {

        // Test case SM-5-12 Verify that the behavior is correct when you disable Location service during scanning


        MainPage mainPage = new MainPage(driver);
        mainPage.PressScanButton();

        BLESDKProjectMethods.DisableLocalService();

       mainPage.ScanScatusCheck(ScanStatus);

       BLESDKProjectMethods.EnableLocalService();

    }
}
