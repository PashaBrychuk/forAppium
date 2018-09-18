package sdktest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;

public class MainPage {

    private AppiumDriver mDriver;


    public MainPage(AppiumDriver driver) {
        mDriver = driver;
        PageFactory.initElements(driver, this);

    }

    public void PressScanButton() {
        WebElement scanButton = mDriver.findElement(By.id("com.hilti.hiltibletestapp:id/startScan"));
        scanButton.click();
    }

    public void ScanScatusCheck(String ScanStatus) {
        WebElement StatusOfScan = mDriver.findElement(By.id("com.hilti.hiltibletestapp:id/requestStatusTextView"));
        Assert.assertEquals(ScanStatus, StatusOfScan.getText());
    }

    public boolean ScanDurationCheck (String ScanDuration) {
        boolean TestisOK;
        WebElement StatusOfScan = mDriver.findElement(By.id("com.hilti.hiltibletestapp:id/requestStatusTextView"));
        TestisOK = StatusOfScan.getText().contains(ScanDuration);

        return TestisOK;
    }

    public void ScanProcessCheck(String ScanProcess){

        WebElement StatusDuringScan = mDriver.findElement(By.id("com.hilti.hiltibletestapp:id/statusTextView"));
        assertEquals(ScanProcess, StatusDuringScan.getText());
    }

    public void StopScanButtonPress(){
        WebElement StopScanButton = mDriver.findElement(By.id("com.hilti.hiltibletestapp:id/stopScan"));
        StopScanButton.click();
    }

    public void PressSettingsButton() {
        WebElement SettingsButton = mDriver.findElement(By.id("com.hilti.hiltibletestapp:id/settings"));
        SettingsButton.click();
        try {
            Thread.sleep(1000);
        } catch (Exception ignore) {
        };
    }

    public void BLEDisable () throws IOException {
        String s;
        String State = "";
        int BLEStateEnabled = 1;

        Process exec = Runtime.getRuntime().exec("adb shell settings get global bluetooth_on");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));

        while ((s = bufferedReader.readLine()) != null) {
            System.out.println(s);
            State = State + s;
        }
        System.out.println(State);

        int StateOfBLE = Integer.parseInt(State);

        if (StateOfBLE == BLEStateEnabled) {

            exec = Runtime.getRuntime().exec("adb shell am start -a android.settings.BLUETOOTH_SETTINGS");
            bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));

            while ((s = bufferedReader.readLine()) != null) {
                System.out.println(s);
            }

            while (mDriver.findElements(By.xpath(".//*/android.widget.LinearLayout/android.widget.Switch")).isEmpty()) {


                try {
                    Thread.sleep(1000);
                } catch (Exception ignore) {
                }
                ;
            }

            mDriver.findElement(By.xpath(".//*/android.widget.LinearLayout/android.widget.Switch")).click();
            try {
                Thread.sleep(5000);
            } catch (Exception ignore) {
            }
            ;


            ((AndroidDriver) mDriver).pressKeyCode(AndroidKeyCode.BACK);
            try {
                Thread.sleep(10000);
            } catch (Exception ignore) {
            }
            ;
        }
    }



}