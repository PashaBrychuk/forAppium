package sdktest.scanning;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.junit.Test;
import sdktest.AppiumSetUp;
import sdktest.BLESDKProjectMethods;
import sdktest.MainPage;
import sdktest.SettingsPage;

public class ScanIntervalConfigParameterSM21 extends AppiumSetUp {

    private int Time = 30000;
    private String ScanLengh = "30";
    private String Duration = "TIMEOUT";
    private String DurationDuringScan = "SUCCESS";

    private String ScanStatusDuringScanning = "SCANNING...";

    @Test
    public void PressScanButton(){

        // Test case SM-2-1 Verify a scan interval configurable parameter and SM-3-2 Verify that scan process will be stopped automatically when time out occurs
        SettingsPage settingsPage = new SettingsPage(driver);
        MainPage mainPage = new MainPage(driver);

        BLESDKProjectMethods.MoveToSettingsTab(driver);

        settingsPage.SetScanDuration(ScanLengh);
        ((AndroidDriver)driver).pressKeyCode(AndroidKeyCode.BACK);

        mainPage.PressScanButton();




        long StartTime  = System.currentTimeMillis();
        long NewTime  = System.currentTimeMillis();
      do {
          NewTime  = System.currentTimeMillis();

      }while (mainPage.ScanDurationCheck(DurationDuringScan));

        if ((NewTime-StartTime)>=(Time-2000)&(NewTime-StartTime)<=(Time+2000)) {
            mainPage.ScanScatusCheck(Duration);
            System.out.println(NewTime - StartTime);
        }
        else{
          if ((NewTime-StartTime)<(Time-2000)|| (NewTime-StartTime)>(Time+2000) ) {
              mainPage.ScanScatusCheck(DurationDuringScan);
          }
        }


        //Test case SM-3-4: Verify that it is possible to start new scan process when it was stopped for some reason
        mainPage.PressScanButton();

        mainPage.ScanScatusCheck(DurationDuringScan);
        mainPage.ScanProcessCheck(ScanStatusDuringScanning);

    }
}
