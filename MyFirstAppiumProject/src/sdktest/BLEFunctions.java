package sdktest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BLEFunctions extends AppiumSetUp {
    String s;
    String State = "";
    int BLEStateEnabled = 1;
    int BLEStateDisable = 0;

    private AppiumDriver mDriver;


    public BLEFunctions (AppiumDriver driver) {
        mDriver = driver;
        PageFactory.initElements(driver, this);

    }


    public void BLEDisable (int BLEStateEnabledDisable) throws IOException {
        State = "";
        s = "";

        Process exec = Runtime.getRuntime().exec("adb shell settings get global bluetooth_on");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));

        while ((s = bufferedReader.readLine()) != null) {
            System.out.println(s);
            State = State + s;
        }
        System.out.println(State);

        int StateOfBLE = Integer.parseInt(State);

        if (StateOfBLE != BLEStateEnabledDisable) {

            exec = Runtime.getRuntime().exec("adb shell am start -a android.settings.BLUETOOTH_SETTINGS");
            bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));

            while ((s = bufferedReader.readLine()) != null) {
                System.out.println(s);
            }

            while (mDriver.findElements(By.xpath(".//*/android.widget.LinearLayout/android.widget.Switch")).isEmpty()) {


                try {
                    Thread.sleep(5000);
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
