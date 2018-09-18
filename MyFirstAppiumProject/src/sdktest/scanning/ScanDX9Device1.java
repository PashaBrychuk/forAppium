package sdktest.scanning;

import org.junit.Test;
import sdktest.AppiumSetUp;
import sdktest.BLESDKProjectMethods;

import java.io.IOException;

public class ScanDX9Device1 extends AppiumSetUp {

    private String DeviceName = "name: DX 9";
    private String DeviceAddress = "address: E4:EA:0D:1C:E4:D8";

    @Test
    public void DX9Device1Scan() throws IOException {

        //Test cases SM-1-1 Verify that it is possible to scan Hilti devices using StartScan() option and PP-1-2 Verify that SDK provides parsing of "Hilti Tool ID", "Tool specific data" and "Scan response data" to a readable format for DX5/460/9 Tools
        BLESDKProjectMethods.DeviceConnectOptions(driver, DeviceName, DeviceAddress);
    }
}
