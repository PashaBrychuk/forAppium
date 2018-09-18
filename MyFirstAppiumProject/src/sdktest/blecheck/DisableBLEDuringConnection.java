package sdktest.blecheck;

import org.junit.Test;
import sdktest.AppiumSetUp;
import sdktest.BLEFunctions;
import sdktest.BLESDKProjectMethods;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class DisableBLEDuringConnection extends AppiumSetUp {

    @Test
    public void DisableBLEDuringConnection() throws IOException {

        //

        BLEFunctions bleFunctions = new BLEFunctions(driver);
        BLESDKProjectMethods.StartScanElements(driver);
        BLESDKProjectMethods.MoveToConnectionTab(driver, 0);


        ConnectButton.click();
        bleFunctions.BLEDisable(0);


        assertEquals(RequestStatusAfterDisconnection, RequestStatusField.getText());
        assertEquals(CallBackDisconnected, CallBackField.getText());
        assertEquals(ToolStatusDisconnected, ToolStatusField.getText());


        bleFunctions.BLEDisable(1);

    }
}
