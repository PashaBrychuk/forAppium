package sdktest.blecheck;

import org.junit.Test;
import sdktest.AppiumSetUp;
import sdktest.BLEFunctions;
import sdktest.MainPage;

import java.io.IOException;

public class StartScanwhenBLEisOffSM44 extends AppiumSetUp {

    private String ScanMessageafterStopping = "BLUETOOTH_UNAVAILABLE"; // "NOT READY"+"\n"+"Bluetooth is disabled"
    private String ScanStatusafterStopping = "NOT READY"+"\n"+"Bluetooth is disabled";;

    @Test
    public void StopScanSM31CheckStatus() throws IOException {

        // Test case SM-4-4 Start Scan when BLE is Off
        MainPage mainPage = new MainPage(driver);
        BLEFunctions bleFunctions = new BLEFunctions(driver);

        mainPage.PressScanButton();

        bleFunctions.BLEDisable(0);


        mainPage.PressScanButton();

        mainPage.ScanScatusCheck(ScanMessageafterStopping);

        mainPage.ScanProcessCheck(ScanStatusafterStopping);

        bleFunctions.BLEDisable(1);

    }
}
