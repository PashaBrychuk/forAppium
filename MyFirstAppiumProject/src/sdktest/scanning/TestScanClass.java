// changed by Pasha

package sdktest.scanning;

import io.appium.java_client.touch.offset.PointOption;
//
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.TouchAction;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import sdktest.AppiumSetUp;
import sdktest.BLESDKProjectMethods;
import sdktest.ScreenSize;

import java.io.File;
import java.io.IOException;

public class TestScanClass extends AppiumSetUp {

    private int Time = 30000;
    private boolean CheckImage = false;

    private String PathToElement = "";

    @Test
    public void BX3Scan2() throws IOException {


        ScreenSize sSize = new ScreenSize();
        sSize.updateSize();

        BLESDKProjectMethods.StartScanElements(driver);

        while(!this.CheckImage) {

                PathToElement = ".//*/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout/android.widget.TextView";

                try {
                    BLESDKProjectMethods.ScanTextVerification(driver, PathToElement);
                } catch (AssertionError e) {
                    System.out.println("Device has an error");
                }

                File scrFile = (File)driver.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File("c:\\temp\\screenshot.png"));

                //(new TouchAction(this.driver)).longPress(sSize.x, sSize.y1).moveTo(sSize.x, sSize.y2).release().perform();
            TouchAction action = new TouchAction(driver);
            action.longPress(new LongPressOptions().withPosition(PointOption.point(sSize.x,sSize.y1))).moveTo(PointOption.point(sSize.x,sSize.y2)).release().perform();




            try {
                    Thread.sleep(10000);
                } catch (Exception var11) {
                    ;
                }

                File scrFile2 = (File)this.driver.getScreenshotAs(OutputType.FILE);

                FileUtils.copyFile(scrFile2, new File("c:\\temp\\screenshot1.png"));


                File f = new File("c:\\temp\\screenshot.png");
                File f1 = new File("c:\\temp\\screenshot1.png");

                System.out.println(FileUtils.contentEquals(f, f1));

                if (FileUtils.contentEquals(f, f1)) {


                    if(!driver.findElements(By.xpath(".//*/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.TextView")).isEmpty()) {



                            PathToElement = ".//*/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.TextView";
                        try {
                            BLESDKProjectMethods.ScanTextVerification(driver, PathToElement);
                        } catch (AssertionError e) {
                            System.out.println("Device has an error");
                        }
                        this.CheckImage = true;
                    } else {
                        this.CheckImage = true;
                    }
                }
        }
    }
}
