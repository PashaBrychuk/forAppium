package sdktest.blecheck;

import org.junit.Test;
import sdktest.AppiumSetUp;
import sdktest.BLEFunctions;
import sdktest.BLESDKProjectMethods;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ConnectBLEisDisabledCM21 extends AppiumSetUp {


    @Test
    public void SupportsBluetoothBLE() throws IOException {

        // CM - 2-1 Verify that error status is displayed when you try to connect to the Hilti tool when Bluetooth was disabled

        BLEFunctions bleFunctions = new BLEFunctions(driver);

        BLESDKProjectMethods.StartScanElements(driver);

        BLESDKProjectMethods.MoveToConnectionTab (driver, 0);



        assertEquals(ToolStatusBefore, ToolStatusField.getText());


        bleFunctions.BLEDisable(0);

        ConnectButton.click();
        try{Thread.sleep(3000);} catch(Exception ignore){};

        assertEquals(ToolStatusAfter, ToolStatusField.getText());
        assertEquals(RequestStatusAfter, RequestStatusField.getText());
        assertEquals(CallBackAfter, CallBackField.getText());

        bleFunctions.BLEDisable(1);

    }
}
