package sdktest.scanning;

import org.junit.Test;
import sdktest.AppiumSetUp;
import sdktest.MainPage;

public class StopScanSM31 extends AppiumSetUp {

    private String ScanMessageafterStopping = "SUCCESS";
    private String ScanStatusafterStopping = "READY";
    private String ScanStatusDuringScanning = "SCANNING...";
    private String ScanMessageDuringScanning = "SUCCESS";


    @Test
    public void StopScanSM31CheckStatus() {

        // Test case SM-3-1 Verify that it is possible to stop scan StopScan() by Client stop scan request


        MainPage mainPage = new MainPage(driver);
        mainPage.PressScanButton();



        mainPage.StopScanButtonPress();
        mainPage.ScanScatusCheck(ScanMessageafterStopping);
        mainPage.ScanProcessCheck(ScanStatusafterStopping);




        //Test case SM-3-4: Verify that it is possible to start new scan process when it was stopped for some reason
        mainPage.PressScanButton();

        mainPage.ScanProcessCheck(ScanStatusDuringScanning);
        mainPage.ScanScatusCheck(ScanMessageDuringScanning);
    }
}
