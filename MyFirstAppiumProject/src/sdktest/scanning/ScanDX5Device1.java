package sdktest.scanning;

import org.junit.Test;
import sdktest.AppiumSetUp;
import sdktest.BLESDKProjectMethods;

import java.io.IOException;

public class ScanDX5Device1  extends AppiumSetUp {
    private String DeviceName = "name: DX 5";
    private String DeviceAddress = "address: C5:61:B8:33:1B:00";

    @Test
    public void DX5Device1Scan() throws IOException {

        //Test cases SM-1-1 Verify that it is possible to scan Hilti devices using StartScan() option and PP-1-2 Verify that SDK provides parsing of "Hilti Tool ID", "Tool specific data" and "Scan response data" to a readable format for DX5/460/9 Tools
        BLESDKProjectMethods.DeviceConnectOptions(driver, DeviceName, DeviceAddress);
    }
}
