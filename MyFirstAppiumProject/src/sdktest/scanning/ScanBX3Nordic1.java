package sdktest.scanning;

import org.junit.Test;
import sdktest.AppiumSetUp;
import sdktest.BLESDKProjectMethods;

import java.io.IOException;

public class ScanBX3Nordic1 extends AppiumSetUp {

    private String DeviceName = "name: BX 3";
    private String DeviceAddress = "address: E6:9E:91:19:8C:4C";

    @Test
    public void BX3ScanNordic1() throws IOException {


        //Test cases SM-1-1 Verify that it is possible to scan Hilti devices using StartScan() option and PP-1-1 Verify that SDK provides parsing of "Hilti Tool ID", "Tool specific data" and "Scan response data" to a readable format for BX3 Tools

       BLESDKProjectMethods.DeviceConnectOptions(driver, DeviceName, DeviceAddress);

    }
}
