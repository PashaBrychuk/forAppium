//changes made by Pasha
package sdktest;
import io.appium.java_client.touch.offset.PointOption;




import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


import static org.junit.Assert.assertEquals;

public class SettingsPage {
    private AppiumDriver mDriver;

    public SettingsPage(AppiumDriver driver) {
        mDriver = driver;
        PageFactory.initElements(driver, this);
    }

    public void LocationServiceCheck(String LocationServiceCheckStatus) {
        WebElement LocationService = mDriver.findElement(By.id("com.hilti.hiltibletestapp:id/locationServicesTextView"));
        assertEquals(LocationServiceCheckStatus, LocationService.getText());
    }

    public void CheckBLESTATUS(String BLESTATUS){

        if (!mDriver.findElements(By.id("com.hilti.hiltibletestapp:id/settings")).isEmpty()) {
            WebElement SettingsButton = mDriver.findElement(By.id("com.hilti.hiltibletestapp:id/settings"));
            SettingsButton.click();
            try {
                Thread.sleep(1000);
            } catch (Exception ignore) {
            }
            ;

            ((AndroidDriver) mDriver).pressKeyCode(AndroidKeyCode.BACK);
        }

        ScreenSize sSize = new ScreenSize();
        sSize.updateSize();
        System.out.println("Before while");
        //WebElement JastElement = mDriver.findElement(By.id("com.hilti.hiltibletestapp:id/locationServicesTextView"));
        //JastElement.click();

        while (mDriver.findElements(By.id("com.hilti.hiltibletestapp:id/statusTextView")).isEmpty()) {
            System.out.println("Before move");
            //new TouchAction(mDriver).longPress(sSize.x, sSize.y2+100).moveTo(sSize.x, sSize.y1).release().perform();
            TouchAction action = new TouchAction(mDriver);
            action.longPress(new LongPressOptions().withPosition(PointOption.point(sSize.x,sSize.y2+100))).moveTo(PointOption.point(sSize.x,sSize.y1)).release().perform();
            System.out.println("Move");
            try {
                Thread.sleep(5000);
            } catch (Exception ignore) {
            }
            ;
        }

        WebElement BluetoothState = mDriver.findElement(By.id("com.hilti.hiltibletestapp:id/statusTextView"));
        assertEquals(BLESTATUS,  BluetoothState.getText());

    }

    public void BLESUPPORT (String BLESupport){
        ScreenSize sSize = new ScreenSize();
        sSize.updateSize();

        //new TouchAction(mDriver).longPress(sSize.x, sSize.y1).moveTo(sSize.x, sSize.y2).release().perform();
        TouchAction action = new TouchAction(mDriver);

        action.longPress(new LongPressOptions().withPosition(PointOption.point(sSize.x,sSize.y2))).moveTo(PointOption.point(sSize.x,sSize.y2)).release().perform();

        try {
            Thread.sleep(1000);
        } catch (Exception ignore) {
        };
        WebElement BLE = mDriver.findElement(By.id("com.hilti.hiltibletestapp:id/bleTextView"));
        assertEquals(BLESupport, BLE.getText());

    }

    public void SetSimulationMode() {

        ScreenSize sSize = new ScreenSize();
        sSize.updateSize();

        //new TouchAction(mDriver).longPress(sSize.x, sSize.y2).moveTo(sSize.x, sSize.y1).release().perform();


        TouchAction action = new TouchAction(mDriver);

        action.longPress(new LongPressOptions().withPosition(PointOption.point(sSize.x,sSize.y2))).moveTo(PointOption.point(sSize.x,sSize.y1)).release().perform();


        try {
            Thread.sleep(1000);
        } catch (Exception ignore) {
        };

        WebElement ModeSelect = mDriver.findElement(By.id("android:id/text1"));
        ModeSelect.click();

        WebElement SelectMode = mDriver.findElement(By.xpath(".//*/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[2]"));
        SelectMode.click();

    }

    public void SetScanDuration (String ScanDuration){

        WebElement SetDuration = mDriver.findElement(By.id("com.hilti.hiltibletestapp:id/duration"));
        SetDuration.clear();
        SetDuration.sendKeys(ScanDuration);
        ((AndroidDriver)mDriver).pressKeyCode(AndroidKeyCode.BACK);
    }

}
