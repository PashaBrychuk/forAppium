package sdktest.blecheck;

import org.junit.Test;
import sdktest.AppiumSetUp;
import sdktest.BLESDKProjectMethods;
import sdktest.SettingsPage;

public class MobileDeviceSupportsBluetoothBLESM56 extends AppiumSetUp {

    private String BluetoothStatus = "Enabled";
    private String BLESupport = "Supported";

    @Test
    public void SupportsBluetoothBLE() {


        // Test case SM-5-6 Verify that information that Mobile device supports Bluetooth BLE feature is provided

        SettingsPage settingsPage = new SettingsPage(driver);

        BLESDKProjectMethods.MoveToSettingsTab(driver);

        settingsPage.CheckBLESTATUS(BluetoothStatus);

        settingsPage.BLESUPPORT(BLESupport);

    }


}
