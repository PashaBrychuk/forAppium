package sdktest.scanning;

import org.junit.Test;
import sdktest.AppiumSetUp;
import sdktest.MainPage;

public class StopScanWithoutStartingSM43 extends AppiumSetUp {

    private String ScanStatus = "SCAN_WAS_NOT_STARTED";

    @Test
    public void PressScanButtonWithoutStopping() {

        //Test case SM-4-3 Stop scan several times without starting

        MainPage mainPage = new MainPage(driver);

        mainPage.StopScanButtonPress();
        mainPage.StopScanButtonPress();
        mainPage.StopScanButtonPress();
        mainPage.StopScanButtonPress();
        mainPage.StopScanButtonPress();

        mainPage.ScanScatusCheck(ScanStatus);
    }
}
