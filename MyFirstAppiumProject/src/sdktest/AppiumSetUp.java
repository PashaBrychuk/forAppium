package sdktest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class AppiumSetUp {

    protected static AppiumDriver driver;
    public String BluetoothStatus = "Enabled";
    public String BluetoothStatusOff = "Disabled";

    public String RequestStatusAfter = "Request status: SUCCESS";
    public String RequestStatusAfterSecondConnect = "Request status: DEVICE_ALREADY_CONNECTING";
    public String CallBackAfter = "Callback: Error: BLUETOOTH_ERROR";
    public String ToolStatusAfter = "Tool status: DISCONNECTED";
    public String ToolStatusBefore = "Tool status: DISCONNECTED";
    public static WebElement ConnectButton;
    public static WebElement RequestStatusField;
    public static WebElement CallBackField;
    public static WebElement ToolStatusField;


    public String RequestStatusAfterDisconnection = "Request status: SUCCESS";
    public String CallBackDisconnected = "Callback: onDisconnected";
    public String ToolStatusDisconnected = "Tool status: DISCONNECTED";





    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Samsung Galaxy S7");
        capabilities.setCapability("appPackage", "com.hilti.hiltibletestapp");
        capabilities.setCapability("appActivity", "MainActivity");
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("autoGrantPermissions", true);


        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);


    }
    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
