package sdktest.scanning;

import org.junit.Test;
import sdktest.AppiumSetUp;
import sdktest.BLESDKProjectMethods;

import java.io.IOException;

public class ScanBX3RealDevice2 extends AppiumSetUp {

    private String DeviceName = "name: BX 3";
    private String DeviceAddress = "address: 00:13:43:48:27:BC";

    @Test
    public void BX3Scan2() throws IOException {
      BLESDKProjectMethods.DeviceConnectOptions(driver, DeviceName, DeviceAddress);

    }
}
