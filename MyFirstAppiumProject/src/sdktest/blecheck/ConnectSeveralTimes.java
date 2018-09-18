package sdktest.blecheck;

import org.junit.Test;
import sdktest.AppiumSetUp;
import sdktest.BLESDKProjectMethods;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ConnectSeveralTimes extends AppiumSetUp {

    private int Time = 30000;



    @Test
    public void SupportsBluetoothBLE() throws IOException {

        // CM - 3-1 Verify that behavior is correct when you try to connect several times during "connecting " phase

        BLESDKProjectMethods.StartScanElements(driver);
        BLESDKProjectMethods.MoveToConnectionTab (driver, 0);

        ConnectButton.click();
        ConnectButton.click();

        assertEquals(RequestStatusAfterSecondConnect, RequestStatusField.getText());

    }
}
