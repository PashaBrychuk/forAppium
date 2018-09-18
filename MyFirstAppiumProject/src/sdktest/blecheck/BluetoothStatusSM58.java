package sdktest.blecheck;

import org.junit.Test;
import sdktest.*;

import java.io.IOException;

public class BluetoothStatusSM58 extends AppiumSetUp {


    @Test
    public void SupportsBluetoothBLE() throws IOException {

        // Test case SM-5-8 Verify that SDK provides the Bluetooth status

        BLEFunctions bleFunctions = new BLEFunctions(driver);
        SettingsPage settingsPage = new SettingsPage(driver);


        settingsPage.CheckBLESTATUS("Enabled");

        bleFunctions.BLEDisable(0);
        settingsPage.CheckBLESTATUS("Disabled");


        bleFunctions.BLEDisable(1);
        settingsPage.CheckBLESTATUS("Enabled");
    }
}
