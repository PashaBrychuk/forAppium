package sdktest.scanning;

import org.junit.Test;
import sdktest.AppiumSetUp;
import sdktest.MainPage;


public class StartScanWithoutStopping42 extends AppiumSetUp {
    private String ScanStatus = "SCAN_ALREADY_STARTED";

    @Test
    public void PressScanButtonWithoutStopping(){

        // Test case SM-4-2 Start scan several times without stopping
        MainPage mainPage = new MainPage(driver);
        mainPage.PressScanButton();
        mainPage.PressScanButton();
        mainPage.PressScanButton();
        mainPage.PressScanButton();
        mainPage.PressScanButton();

        mainPage.ScanScatusCheck(ScanStatus);

    }
}
