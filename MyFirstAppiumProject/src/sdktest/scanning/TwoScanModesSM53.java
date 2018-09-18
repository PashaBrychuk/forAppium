package sdktest.scanning;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import sdktest.AppiumSetUp;

import static org.junit.Assert.assertTrue;

public class TwoScanModesSM53 extends AppiumSetUp {

    @Test
    public void CheckTwoScanModesSM53() {

        //Test case SM-5-3 Verify that two Scan modes are available: the Simulator mode and BLE mode

        Dimension size = driver.manage().window().getSize();

        int y1 = (int) (size.height * 0.90);
        int y2 = (int) (size.height * 0.50);
        int x = (int) (size.width*0.5);


        WebElement SettingsButton = driver.findElement(By.id("com.hilti.hiltibletestapp:id/settings"));
        SettingsButton.click();

//        TouchAction action = new TouchAction(driver).longPress(x,y2).moveTo(x,y1).release().perform();
        TouchAction action = new TouchAction(driver);

        action.longPress(new LongPressOptions().withPosition(PointOption.point(x,y2))).moveTo(PointOption.point(x,y1)).release().perform();
        try{Thread.sleep(1000);} catch(Exception ignore){};

        WebElement ModeSelect = driver.findElement(By.id("android:id/text1"));
        ModeSelect.click();

        WebElement SelectMode = driver.findElement(By.xpath(".//*/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[2]"));
        SelectMode.click();
        //SelectMode.

        ((AndroidDriver)driver).pressKeyCode(AndroidKeyCode.BACK);
        ((AndroidDriver)driver).pressKeyCode(AndroidKeyCode.BACK);
        WebElement ScanButton = driver.findElement(By.id("com.hilti.hiltibletestapp:id/startScan"));
        ScanButton.click();

        try{Thread.sleep(30000);} catch(Exception ignore){};

        WebElement ScanElement = driver.findElement(By.id("com.hilti.hiltibletestapp:id/text"));
        assertTrue(ScanElement.getText().contains("simulation"));

        SettingsButton.click();
        //new TouchAction(driver).longPress(x,y2).moveTo(x,y1).release().perform();
        action.longPress(new LongPressOptions().withPosition(PointOption.point(x,y2))).moveTo(PointOption.point(x,y1)).release().perform();

        try{Thread.sleep(1000);} catch(Exception ignore){};
        ModeSelect.click();
        WebElement SelectModeOne = driver.findElement(By.xpath(".//*/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[1]"));
        SelectModeOne.click();
        ((AndroidDriver)driver).pressKeyCode(AndroidKeyCode.BACK);
        ((AndroidDriver)driver).pressKeyCode(AndroidKeyCode.BACK);





    }
}
