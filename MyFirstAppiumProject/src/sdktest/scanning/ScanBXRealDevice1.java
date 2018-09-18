package sdktest.scanning;

import org.junit.Test;
import sdktest.AppiumSetUp;
import sdktest.BLESDKProjectMethods;

import java.io.IOException;

public class ScanBXRealDevice1 extends AppiumSetUp {

        private String DeviceName = "name: BX 3";
        private String DeviceAddress = "address: 00:13:43:2D:AD:88";

        @Test
        public void BX3Scan() throws IOException {

            //Test cases SM-1-1 Verify that it is possible to scan Hilti devices using StartScan() option and PP-1-1 Verify that SDK provides parsing of "Hilti Tool ID", "Tool specific data" and "Scan response data" to a readable format for BX3 Tools

            BLESDKProjectMethods.DeviceConnectOptions(driver, DeviceName, DeviceAddress);
        }
    }


