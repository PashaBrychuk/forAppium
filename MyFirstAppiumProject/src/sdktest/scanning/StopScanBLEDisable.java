package sdktest.scanning;

import org.junit.Test;
import sdktest.AppiumSetUp;
import sdktest.BLESDKProjectMethods;
import sdktest.MainPage;

import java.io.IOException;

public class StopScanBLEDisable extends AppiumSetUp {

    private String ScanMessageafterStopping = "BLUETOOTH_WAS_DISABLED";
    private String ScanStatusafterStopping = "NOT READY"+"\n"+"Bluetooth is disabled";
    private String ScanStatusDuringScanning = "SCANNING...";
    private String ScanMessageDuringScanning = "SUCCESS";

    @Test
    public void StopScanSM31CheckStatus() throws IOException {

        // Test case SM-3-3 Verify that scan process will be stopped automatically when BLE is turned off


        MainPage mainPage = new MainPage(driver);
        mainPage.PressScanButton();

        BLESDKProjectMethods.BLEDISABLE(driver);
        try{Thread.sleep(2000);} catch(Exception ignore){};

        mainPage.ScanScatusCheck(ScanMessageafterStopping);
        mainPage.ScanProcessCheck(ScanStatusafterStopping);

        //Test case SM-3-4: Verify that it is possible to start new scan process when it was stopped for some reason

        BLESDKProjectMethods.BLEENABLE(driver);

        mainPage.PressScanButton();

        mainPage.ScanProcessCheck(ScanStatusDuringScanning);
        mainPage.ScanScatusCheck(ScanMessageDuringScanning);


    }
}
