//changed by Pasha


package sdktest;

import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.touch.TapOptions;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import io.appium.java_client.touch.offset.PointOption;

public class BLESDKProjectMethods extends AppiumSetUp {

    private static String RequestStatus = "Request status: SUCCESS";
    private static String CallBack = "Callback: onConnected";
    private static String ToolStatus = "Tool status: CONNECTED";

    private static String CallBackDisconnect = "Callback: onDisconnected";
    private static String ToolStatusDisconnect = "Tool status: DISCONNECTED";

    private static String ActivateDemoInput = "Input: 1F 01 00 00 00 1E";
    private static String ActivateDemoOutput = "Output: 1F 01 00 01 00 1F";
    private static String ActivateDemoResponse = "Parsed response: Parsed Response: ACTIVE";

    private static String DeactivateDemoInput = "Input: 1F 00 00 00 00 1F";
    private static String DeactivateDemoOutput = "Output: 1F 00 00 00 00 1F";
    private static String DeactivateDemoResponse = "Parsed response: DEACTIVE";

    private static String CheckDemoInput = "Input: 1F 02 00 00 00 1D";
    private static String CheckDemoOutputActive = "Output: 1F 02 00 01 00 1C";
    private static String CheckDemoOutputDeactive = "Output: 1F 02 00 00 00 1D";


    private static String BadCommandDemoInput = "Input: FF 00 00 00 00 FF";
    private static String BadCommandDemoOutput = "Output: 84 00 00 00 00 84";
    private static String BadCommandDemoResponse = "Parsed response: BAD_COMMAND";

    private static String TimeoutDemoInput = "Input: EE 00 00 00 00 EE";
    private static String TimeoutDemoOutput = "Output: none";
    private static String TimeoutDemoResponse = "Parsed response: TIMEOUT";

    private static String FindMeInput = "Input: 0E 01";
    private static String FindMeOutput = "Output: none";
    private static String FindMeResponse = "Parsed response: none";



    public static void DisableLocalService () throws IOException {

        Process exec = Runtime.getRuntime().exec("adb shell settings put secure location_providers_allowed -gps\n");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));

        String s;
        while((s = bufferedReader.readLine()) != null) {
            System.out.println(s);
        }
    }

    public static void EnableLocalService () throws IOException {

        Process exec = Runtime.getRuntime().exec("adb shell settings put secure location_providers_allowed +gps\n");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));

        String s;
        while((s = bufferedReader.readLine()) != null) {
            System.out.println(s);
        }
    }




    public static void CheckInterval(String AllScanText, String IntervalCheck) {
        String MaintenanceIntervalString = StringUtils.substringBetween(AllScanText, IntervalCheck, "\n");

        int MaintenanceInterval = Integer.parseInt(MaintenanceIntervalString);
        System.out.println(MaintenanceInterval);

        double Interval = MaintenanceInterval / 500.0;
        System.out.println(Interval);

        if (Interval == (int) Interval) {
            System.out.println("Interval multiplied by 500 ");

        } else {
            System.out.println("Interval was not multiplied by 500 ");

        }
    }

    public static void ConnectToTool(AppiumDriver driver, String PathToElement) {
        boolean Element = driver.findElements(By.id("com.hilti.hiltibletestapp:id/connect")).isEmpty();

        if (Element) {

            WebElement ScanTool = driver.findElement(By.xpath(PathToElement));


            //new TouchAction(driver).tap(ScanTool).release().perform();

            new TouchAction(driver).tap((TapOptions) ScanTool).release().perform();
            try {
                Thread.sleep(3000);
            } catch (Exception ignore) {
            }
            ;
        }

        WebElement ConnectButton = driver.findElement(By.id("com.hilti.hiltibletestapp:id/connect"));
        ConnectButton.click();
        try {
            Thread.sleep(10000);
        } catch (Exception ignore) {
        }
        ;

        WebElement ConnectRequestStatus = driver.findElement(By.id("com.hilti.hiltibletestapp:id/requestStatusTextView"));
        assertEquals(RequestStatus, ConnectRequestStatus.getText());

        WebElement ConnectCallback = driver.findElement(By.id("com.hilti.hiltibletestapp:id/callbackTextView"));
        assertEquals(CallBack, ConnectCallback.getText());

        WebElement ConnectToolStatus = driver.findElement(By.id("com.hilti.hiltibletestapp:id/toolStatusTextView"));
        assertEquals(ToolStatus, ConnectToolStatus.getText());
        System.out.println("Device has been connected successfully");

    }

    public static void DisconnectFromTool(AppiumDriver driver) {
        WebElement DisconnectButton = driver.findElement(By.id("com.hilti.hiltibletestapp:id/disconnect"));
        DisconnectButton.click();
        try {
            Thread.sleep(15000);
        } catch (Exception ignore) {
        }
        ;

        WebElement ConnectRequestStatus = driver.findElement(By.id("com.hilti.hiltibletestapp:id/requestStatusTextView"));
        WebElement ConnectCallback = driver.findElement(By.id("com.hilti.hiltibletestapp:id/callbackTextView"));
        WebElement ConnectToolStatus = driver.findElement(By.id("com.hilti.hiltibletestapp:id/toolStatusTextView"));

        assertEquals(RequestStatus, ConnectRequestStatus.getText());
        assertEquals(CallBackDisconnect, ConnectCallback.getText());
        assertEquals(ToolStatusDisconnect, ConnectToolStatus.getText());
        System.out.println("Device has been disconnected successfully");

    }


    public static void BLEDISABLE (AppiumDriver driver) throws IOException {
        int VersionOS = 7;
        String VersionofDevice = "";


        ScreenSize sSize = new ScreenSize();
        sSize.updateSize();

        Process exec = Runtime.getRuntime().exec("adb shell getprop ro.build.version.release");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));

        String s;
        while((s = bufferedReader.readLine()) != null) {
            System.out.println(s);
            VersionofDevice = VersionofDevice + s;
        }


        System.out.println(VersionofDevice);

        System.out.println("Step1");
        //adb shell am start -a android.settings.SETTINGS
        //adb shell am start -a android.settings.BLUETOOTH_SETTINGS

        String VersionOfDeviceText = StringUtils.substringBefore(VersionofDevice,".");

        System.out.println(VersionOfDeviceText);

        int VersionOfDeviceInt = Integer.parseInt(VersionOfDeviceText);
        System.out.println(VersionOfDeviceInt);
        System.out.println("Step2");


        if (VersionOfDeviceInt >= VersionOS ){

            exec = Runtime.getRuntime().exec("adb shell service call bluetooth_manager 8");
            bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));

            while((s = bufferedReader.readLine()) != null) {
                System.out.println(s);
            }


        } else {
            ((AndroidDriver)driver).openNotifications();



            try{Thread.sleep(1000);} catch(Exception ignore){};


            //new TouchAction(driver).longPress(sSize.x,sSize.y2).moveTo(sSize.x,sSize.y1).release().perform();

            TouchAction action = new TouchAction(driver);

            action.longPress(new LongPressOptions().withPosition(PointOption.point(sSize.x,sSize.y2))).moveTo(PointOption.point(sSize.x,sSize.y1)).release().perform();

            try{Thread.sleep(1000);} catch(Exception ignore){};

            driver.findElement(By.xpath(".//*[contains(@content-desc,'Bluetooth')]/android.widget.ImageView")).click();


            //new TouchAction(driver).longPress(sSize.x,sSize.y1).moveTo(sSize.x,sSize.y2).release().perform();

            TouchAction action1 = new TouchAction(driver);

            action1.longPress(new LongPressOptions().withPosition(PointOption.point(sSize.x,sSize.y2))).moveTo(PointOption.point(sSize.x,sSize.y1)).release().perform();

            try{Thread.sleep(1000);} catch(Exception ignore){};

            //new TouchAction(driver).longPress(sSize.x,sSize.y1).moveTo(sSize.x,sSize.y2).release().perform();

            TouchAction action2 = new TouchAction(driver);

            action2.longPress(new LongPressOptions().withPosition(PointOption.point(sSize.x,sSize.y1))).moveTo(PointOption.point(sSize.x,sSize.y2)).release().perform();
            try{Thread.sleep(1000);} catch(Exception ignore){};


        }
        try {
            Thread.sleep(2000);
        } catch (Exception ignore) {
        }
    }

    public static void BLEENABLE (AppiumDriver driver) throws IOException {
        int VersionOS = 7;
        String VersionofDevice = "";


        ScreenSize sSize = new ScreenSize();
        sSize.updateSize();

        Process exec = Runtime.getRuntime().exec("adb shell getprop ro.build.version.release");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));

        String s;
        while((s = bufferedReader.readLine()) != null) {
            System.out.println(s);
            VersionofDevice = VersionofDevice + s;
        }
        System.out.println(VersionofDevice);
        System.out.println("Step1");

        String VersionOfDeviceText = StringUtils.substringBefore(VersionofDevice,".");

        System.out.println(VersionOfDeviceText);

        int VersionOfDeviceInt = Integer.parseInt(VersionOfDeviceText);
        System.out.println(VersionOfDeviceInt);
        System.out.println("Step2");

        if (VersionOfDeviceInt >= VersionOS ){

            exec = Runtime.getRuntime().exec("adb shell service call bluetooth_manager 6");
            bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));

            while((s = bufferedReader.readLine()) != null) {
                System.out.println(s);
            }
        } else {
            ((AndroidDriver)driver).openNotifications();

            while (driver.findElements(By.xpath(".//*[contains(@content-desc,'Bluetooth')]/android.widget.ImageView")).isEmpty()) {

                //new TouchAction(driver).longPress(sSize.x, sSize.y2).moveTo(sSize.x, sSize.y1).release().perform();


                TouchAction action3 = new TouchAction(driver);

                action3.longPress(new LongPressOptions().withPosition(PointOption.point(sSize.x,sSize.y2))).moveTo(PointOption.point(sSize.x,sSize.y1)).release().perform();
                try {
                    Thread.sleep(1000);
                } catch (Exception ignore) {
                }
                ;
            }

            driver.findElement(By.xpath(".//*[contains(@content-desc,'Bluetooth')]/android.widget.ImageView")).click();
            try{Thread.sleep(5000);} catch(Exception ignore){};

            //new TouchAction(driver).longPress(sSize.x,sSize.y1).moveTo(sSize.x,sSize.y2).release().perform();
            TouchAction action4 = new TouchAction(driver);

            action4.longPress(new LongPressOptions().withPosition(PointOption.point(sSize.x,sSize.y1))).moveTo(PointOption.point(sSize.x,sSize.y2)).release().perform();


            try{Thread.sleep(1000);} catch(Exception ignore){};

            //new TouchAction(driver).longPress(sSize.x,sSize.y1).moveTo(sSize.x,sSize.y2).release().perform();

            TouchAction action5 = new TouchAction(driver);

            action5.longPress(new LongPressOptions().withPosition(PointOption.point(sSize.x,sSize.y1))).moveTo(PointOption.point(sSize.x,sSize.y2)).release().perform();


            try{Thread.sleep(1000);} catch(Exception ignore){};

        }
        try {
            Thread.sleep(2000);
        } catch (Exception ignore) {
        }

    }

    public static void DeviceConnectOptions(AppiumDriver driver, String DeviceName, String DeviceAddress) throws IOException {


        boolean CheckImage = false;


        String AllScanText = "";
        String MaintenanceInterval = "Maintenance interval: ";
        String CleaningInterval = "Cleaning interval: ";
        String PathToElement = "";


        ScreenSize sSize = new ScreenSize();
        sSize.updateSize();


        BLESDKProjectMethods.StartScanElements(driver);

        while(!CheckImage) {
            WebElement BX3ScanText = driver.findElement(By.id("com.hilti.hiltibletestapp:id/text"));
            if (BX3ScanText.getText().contains(DeviceName) & BX3ScanText.getText().contains(DeviceAddress)) {
                if (BX3ScanText.getText().contains("DX ")) {
                    AllScanText = BX3ScanText.getText();
                    BLESDKProjectMethods.CheckInterval(AllScanText, MaintenanceInterval);
                    BLESDKProjectMethods.CheckInterval(AllScanText, CleaningInterval);
                }
                PathToElement = ".//*/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout/android.widget.TextView";
                //Connect to Hilti tool: CM - 1-1 Verify that it is possible to connect to DX 5, DX 460, DX 9/BX3 and  DD150 Hilti tools
                BLESDKProjectMethods.ConnectToTool(driver, PathToElement);

                //--------------------------------------------------------------------------------------------------------
                //Disconnect from Hilti tool: CM - 1-2 Verify that it is possible to disconnect from DX 5, DX 460, DX 9/BX3 and  DD150 Hilti tools

                BLESDKProjectMethods.DisconnectFromTool(driver);

                //--------------------------------------------------------------------------------------------------------

                BLESDKProjectMethods.ConnectToTool(driver, PathToElement);

                //--------------------------------------------------------------------------------------------------------
                CheckImage = true;
            } else {
                File scrFile = (File)driver.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File("c:\\temp\\screenshot.png"));
                //(new TouchAction(driver)).longPress(sSize.x, sSize.y1).moveTo(sSize.x, sSize.y2).release().perform();
                TouchAction action6 = new TouchAction(driver);
                action6.longPress(new LongPressOptions().withPosition(PointOption.point(sSize.x,sSize.y2))).moveTo(PointOption.point(sSize.x,sSize.y1)).release().perform();
                try {
                    Thread.sleep(10000L);
                } catch (Exception var11) {
                    ;
                }
                File scrFile2 = (File)driver.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile2, new File("c:\\temp\\screenshot1.png"));
                File f = new File("c:\\temp\\screenshot.png");
                File f1 = new File("c:\\temp\\screenshot1.png");
                if (FileUtils.contentEquals(f, f1)) {

                    if(!driver.findElements(By.xpath(".//*/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.TextView")).isEmpty()) {

                        WebElement LasElement = driver.findElement(By.xpath(".//*/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.TextView"));
                        if (LasElement.getText().contains(DeviceName) & LasElement.getText().contains(DeviceAddress)) {
                            PathToElement = ".//*/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.TextView";
                            //Connect to Hilti tool: CM - 1-1 Verify that it is possible to connect to DX 5, DX 460, DX 9/BX3 and  DD150 Hilti tools
                            BLESDKProjectMethods.ConnectToTool(driver, PathToElement);
                            //--------------------------------------------------------------------------------------------------------
                            //Disconnect from Hilti tool: CM - 1-2 Verify that it is possible to disconnect from DX 5, DX 460, DX 9/BX3 and  DD150 Hilti tools
                            BLESDKProjectMethods.DisconnectFromTool(driver);
                            //--------------------------------------------------------------------------------------------------------
                            BLESDKProjectMethods.ConnectToTool(driver, PathToElement);
                            //--------------------------------------------------------------------------------------------------------
                            CheckImage = true;
                        }else {

                            CheckImage = true;
                            System.out.println("Device has not been found");
                            Assert.assertTrue(BX3ScanText.getText().contains(DeviceName));
                        }
                    } else {

                        CheckImage = true;
                        System.out.println("Device has not been found");
                        Assert.assertTrue(BX3ScanText.getText().contains(DeviceName));
                    }
                }
            }
        }
    }

    public static void MoveToSettingsTab (AppiumDriver driver){
        WebElement SettingsButton = driver.findElement(By.id("com.hilti.hiltibletestapp:id/settings"));
        SettingsButton.click();
        try {
            Thread.sleep(1000);
        } catch (Exception ignore) {
        };

        ((AndroidDriver)driver).pressKeyCode(AndroidKeyCode.BACK);

    }


    public static void MoveToConnectionTab (AppiumDriver driver, int ElementNumber){
        List<WebElement> elementsTwo = (List<WebElement>) driver.findElements(By.id("com.hilti.hiltibletestapp:id/text"));

        try {

            elementsTwo.get(ElementNumber).click();
            try{Thread.sleep(3000);} catch(Exception ignore){}

            ConnectButton = driver.findElement(By.id("com.hilti.hiltibletestapp:id/connect"));
            RequestStatusField = driver.findElement(By.id("com.hilti.hiltibletestapp:id/requestStatusTextView"));
            CallBackField = driver.findElement(By.id("com.hilti.hiltibletestapp:id/callbackTextView"));
            ToolStatusField = driver.findElement(By.id("com.hilti.hiltibletestapp:id/toolStatusTextView"));


        }catch (NoSuchElementException e){
            System.out.println("No Hilti device found");
        }
    }

    public static boolean FindElement (AppiumDriver driver, int ElementNumber, String ElementName){
        boolean ElementEx = false;

        List<WebElement> elementsTwo = (List<WebElement>) driver.findElements(By.id("com.hilti.hiltibletestapp:id/text"));

        if (elementsTwo.get(ElementNumber).getText().contains(ElementName)){
            ElementEx = true;
        }
        System.out.println(ElementEx);
        return ElementEx;
    }

    public static boolean ScrollList (AppiumDriver driver, boolean CheckImage) throws IOException {
        ScreenSize sSize = new ScreenSize();
        sSize.updateSize();


        File scrFile = (File)driver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("c:\\temp\\screenshot.png"));
        //(new TouchAction(driver)).longPress(sSize.x, sSize.y1).moveTo(sSize.x, sSize.y2).release().perform();
        TouchAction action7 = new TouchAction(driver);
        action7.longPress(new LongPressOptions().withPosition(PointOption.point(sSize.x,sSize.y2))).moveTo(PointOption.point(sSize.x,sSize.y1)).release().perform();


        try {
            Thread.sleep(5000L);
        } catch (Exception var11) {
            ;
        }

        File scrFile2 = (File)driver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2, new File("c:\\temp\\screenshot1.png"));
        File f = new File("c:\\temp\\screenshot.png");
        File f1 = new File("c:\\temp\\screenshot1.png");

        if (FileUtils.contentEquals(f, f1)) {

            CheckImage = true;
            System.out.println(CheckImage);
        }




        return (CheckImage);
    }

    public static void StartScanElements(AppiumDriver driver ) {

        String ScanLenght = "30";
        WebElement SettingsButton = driver.findElement(By.id("com.hilti.hiltibletestapp:id/settings"));
        SettingsButton.click();
        WebElement SetDuration = driver.findElement(By.id("com.hilti.hiltibletestapp:id/duration"));
        SetDuration.clear();
        SetDuration.sendKeys(ScanLenght);
        ((AndroidDriver)driver).pressKeyCode(AndroidKeyCode.BACK);
        ((AndroidDriver)driver).pressKeyCode(AndroidKeyCode.BACK);



        WebElement ScanButton = driver.findElement(By.id("com.hilti.hiltibletestapp:id/startScan"));
        ScanButton.click();

        int SleepInterval = Integer.parseInt(ScanLenght)*1000;
        try{Thread.sleep(SleepInterval);} catch(Exception ignore){}
    }


    public static void ActivateDemoMode(AppiumDriver driver ) {

        WebElement ActiveDemoMode = driver.findElement(By.id("com.hilti.hiltibletestapp:id/activateDemo"));
        ActiveDemoMode.click();

        try{Thread.sleep(1000);} catch(Exception ignore){}
        WebElement ConnectRequestStatus = driver.findElement(By.id("com.hilti.hiltibletestapp:id/requestStatusTextView"));

        if (RequestStatus.equals(ConnectRequestStatus.getText())) {

            WebElement InputDemo = driver.findElement(By.id("com.hilti.hiltibletestapp:id/input"));
            assertEquals(ActivateDemoInput, InputDemo.getText());

            WebElement OutputDemo = driver.findElement(By.id("com.hilti.hiltibletestapp:id/output"));
            assertEquals(ActivateDemoOutput, OutputDemo.getText());

            WebElement ResponseDemo = driver.findElement(By.id("com.hilti.hiltibletestapp:id/response"));
            assertEquals(ActivateDemoResponse, ResponseDemo.getText());

        } else assertEquals(RequestStatus, ConnectRequestStatus.getText());

    }

    public static void DeactivateDemoMode(AppiumDriver driver ) {
        WebElement DeactiveDemoMode = driver.findElement(By.id("com.hilti.hiltibletestapp:id/deactivateDemo"));
        DeactiveDemoMode.click();

        try{Thread.sleep(2000);} catch(Exception ignore){}
        WebElement InputDemo = driver.findElement(By.id("com.hilti.hiltibletestapp:id/input"));
        assertEquals(DeactivateDemoInput, InputDemo.getText());

        WebElement OutputDemo = driver.findElement(By.id("com.hilti.hiltibletestapp:id/output"));
        assertEquals(DeactivateDemoOutput, OutputDemo.getText());

        WebElement ResponseDemo = driver.findElement(By.id("com.hilti.hiltibletestapp:id/response"));
        assertEquals(DeactivateDemoResponse, ResponseDemo.getText());


    }

    public static void CheckDemoMode(AppiumDriver driver, String CheckDemoResponse) {
        WebElement CheckDemoMode = driver.findElement(By.id("com.hilti.hiltibletestapp:id/checkDemo"));
        CheckDemoMode.click();
        try{Thread.sleep(2000);} catch(Exception ignore){}

        WebElement InputDemo = driver.findElement(By.id("com.hilti.hiltibletestapp:id/input"));
        assertEquals(CheckDemoInput, InputDemo.getText());

        WebElement OutputDemo = driver.findElement(By.id("com.hilti.hiltibletestapp:id/output"));
        if (CheckDemoResponse.equals("ACTIVE")) {
            assertEquals(CheckDemoOutputActive, OutputDemo.getText());
        } else assertEquals(CheckDemoOutputDeactive, OutputDemo.getText());

        WebElement ResponseDemo = driver.findElement(By.id("com.hilti.hiltibletestapp:id/response"));
        Assert.assertTrue(ResponseDemo.getText().contains(CheckDemoResponse));

    }

    public static void BadCommandDemoMode(AppiumDriver driver) {
        WebElement BadCommandDemoMode = driver.findElement(By.id("com.hilti.hiltibletestapp:id/badCommand"));
        BadCommandDemoMode.click();
        try{Thread.sleep(2000);} catch(Exception ignore){}

        WebElement InputDemo = driver.findElement(By.id("com.hilti.hiltibletestapp:id/input"));
        assertEquals(BadCommandDemoInput, InputDemo.getText());

        WebElement OutputDemo = driver.findElement(By.id("com.hilti.hiltibletestapp:id/output"));
        assertEquals(BadCommandDemoOutput, OutputDemo.getText());


        WebElement ResponseDemo = driver.findElement(By.id("com.hilti.hiltibletestapp:id/response"));
        assertEquals(BadCommandDemoResponse, ResponseDemo.getText());

    }

    public static void TimeoutDemoMode(AppiumDriver driver) {
        WebElement TimeoutDemoMode = driver.findElement(By.id("com.hilti.hiltibletestapp:id/timeoutCommand"));
        TimeoutDemoMode.click();

        try{Thread.sleep(2000);} catch(Exception ignore){}

        WebElement InputDemo = driver.findElement(By.id("com.hilti.hiltibletestapp:id/input"));
        assertEquals(TimeoutDemoInput, InputDemo.getText());

        WebElement OutputDemo = driver.findElement(By.id("com.hilti.hiltibletestapp:id/output"));
        assertEquals(TimeoutDemoOutput, OutputDemo.getText());


        WebElement ResponseDemo = driver.findElement(By.id("com.hilti.hiltibletestapp:id/response"));
        assertEquals(TimeoutDemoResponse, ResponseDemo.getText());

    }

    public static void FindMe(AppiumDriver driver) {
        WebElement FindMe = driver.findElement(By.id("com.hilti.hiltibletestapp:id/findMe"));
        FindMe.click();

        WebElement InputDemo = driver.findElement(By.id("com.hilti.hiltibletestapp:id/input"));
        assertEquals(FindMeInput, InputDemo.getText());

        WebElement OutputDemo = driver.findElement(By.id("com.hilti.hiltibletestapp:id/output"));
        assertEquals(FindMeOutput, OutputDemo.getText());


        WebElement ResponseDemo = driver.findElement(By.id("com.hilti.hiltibletestapp:id/response"));
        assertEquals(FindMeResponse, ResponseDemo.getText());

    }

    public static int NumberOfElementsIdentification (AppiumDriver driver) {
        int NumberofElementsonthePage = 0;

        if(driver.findElements(By.xpath(".//*/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout/android.widget.TextView")).isEmpty()){
            NumberofElementsonthePage = 0;
            System.out.println(NumberofElementsonthePage);
        } else NumberofElementsonthePage = driver.findElements(By.id("com.hilti.hiltibletestapp:id/text")).size();


        return NumberofElementsonthePage;
    }

    public static int FindSpecificElement (String ElementName) throws IOException {
        boolean CheckElement = false;
        int ElementsNumber = 0;
        while (!CheckElement) {
            ElementsNumber = BLESDKProjectMethods.NumberOfElementsIdentification(driver);


            switch (ElementsNumber) {
                case 0: System.out.println("No elements in the scan list");
                    CheckElement = true;
                    break;

                case 1:
                    if (BLESDKProjectMethods.FindElement(driver,0, ElementName)) {
                        ElementsNumber = 0;
                        CheckElement = true;
                    } else
                        CheckElement = BLESDKProjectMethods.ScrollList(driver, CheckElement);

                    break;

                case 2:


                    if (BLESDKProjectMethods.FindElement(driver,0, ElementName)) {
                        ElementsNumber = 0;
                        CheckElement = true;

                    } else  if (BLESDKProjectMethods.FindElement(driver,1, ElementName)) {
                        ElementsNumber = 1;
                        CheckElement = true;
                    } else CheckElement = BLESDKProjectMethods.ScrollList(driver, CheckElement);





                    break;
            }
        }
        return ElementsNumber;
    }



    public static void ScanTextVerification(AppiumDriver driver,  String PathToElement){

        WebElement ScanToolText = driver.findElement(By.xpath(PathToElement));

        if (ScanToolText.getText().contains("name: DX 5") & ScanToolText.getText().contains("address: C5:61:B8:33:1B:00")) {

            try {
                assertTrue(ScanToolText.getText().contains("address: C5:61:B8:33:1B:00"));
                assertTrue(ScanToolText.getText().contains("rssi:"));
                assertTrue(ScanToolText.getText().contains("Version: V0"));
                assertTrue(ScanToolText.getText().contains("Region: EUROPE"));
                assertTrue(ScanToolText.getText().contains("VFE: 0"));
                assertTrue(ScanToolText.getText().contains("FFE: 1049408"));
                assertTrue(ScanToolText.getText().contains("Serial: 212170537"));
                assertTrue(ScanToolText.getText().contains("Total number of fastenings: 9"));
                assertTrue(ScanToolText.getText().contains("Number of fastenings since last cleaning: 9"));
                assertTrue(ScanToolText.getText().contains("Number of fastenings since last maintenance: 9"));
                assertTrue(ScanToolText.getText().contains("Cleaning interval: 2500"));
                assertTrue(ScanToolText.getText().contains("Maintenance interval: 30000"));
                assertTrue(ScanToolText.getText().contains("Battery status: 100 %"));
                assertTrue(ScanToolText.getText().contains("HW revision: 01.48"));
                assertTrue(ScanToolText.getText().contains("SW revision: 03.22"));
                assertTrue(ScanToolText.getText().contains("Time from last cleaning: 2016-01-01 02:00:00"));
                assertTrue(ScanToolText.getText().contains("Time from last service: 2016-01-01 02:00:00"));
                System.out.println("Device DX 5 #1 has been found and verified");
            } catch (AssertionError e){
                System.out.println("Device DX 5 #1 has an error");
            }
        }

        if(ScanToolText.getText().contains("name: DX 5")& ScanToolText.getText().contains("address: E8:EF:4D:8E:82:3E") ) {
            try {
                assertTrue(ScanToolText.getText().contains("address: E8:EF:4D:8E:82:3E"));
                assertTrue(ScanToolText.getText().contains("rssi:"));
                assertTrue(ScanToolText.getText().contains("Version: V0"));
                assertTrue(ScanToolText.getText().contains("Region: EUROPE"));
                assertTrue(ScanToolText.getText().contains("VFE: 0"));
                assertTrue(ScanToolText.getText().contains("FFE: 1049408"));
                assertTrue(ScanToolText.getText().contains("Serial: 213170782"));
                assertTrue(ScanToolText.getText().contains("Total number of fastenings: 45"));
                assertTrue(ScanToolText.getText().contains("Number of fastenings since last cleaning: 45"));
                assertTrue(ScanToolText.getText().contains("Number of fastenings since last maintenance: 45"));
                assertTrue(ScanToolText.getText().contains("Cleaning interval: 2500"));
                assertTrue(ScanToolText.getText().contains("Maintenance interval: 30000"));
                assertTrue(ScanToolText.getText().contains("Battery status: 100 %"));
                assertTrue(ScanToolText.getText().contains("HW revision: 01.48"));
                assertTrue(ScanToolText.getText().contains("SW revision: 03.22"));
                assertTrue(ScanToolText.getText().contains("Time from last cleaning: 2016-01-01 02:00:00"));
                assertTrue(ScanToolText.getText().contains("Time from last service: 2016-01-01 02:00:00"));
                System.out.println("Device DX 5 #2 has been found and verified");
            }catch (AssertionError e){
                System.out.println("Device DX 5 #2 has an error");
            }
        }

        if (ScanToolText.getText().contains("name: BX 3") & ScanToolText.getText().contains("address: 00:13:43:48:27:BC")) {
            try {
                Assert.assertTrue(ScanToolText.getText().contains("address: 00:13:43:48:27:BC"));
                Assert.assertTrue(ScanToolText.getText().contains("rssi:"));
                Assert.assertTrue(ScanToolText.getText().contains("Version: V0"));
                Assert.assertTrue(ScanToolText.getText().contains("Region: ORIGINAL"));
                Assert.assertTrue(ScanToolText.getText().contains("VFE: 2161621"));
                Assert.assertTrue(ScanToolText.getText().contains("FFE: 2152381"));
                Assert.assertTrue(ScanToolText.getText().contains("Serial: 0"));
                Assert.assertTrue(ScanToolText.getText().contains("Total number of fastenings: 0"));
                Assert.assertTrue(ScanToolText.getText().contains("Active error: 0"));
                Assert.assertTrue(ScanToolText.getText().contains("Battery voltage: 0.0 V"));
                Assert.assertTrue(ScanToolText.getText().contains("Temperature motor: 0 °C"));
                Assert.assertTrue(ScanToolText.getText().contains("Broadcast version: 1"));
                Assert.assertTrue(ScanToolText.getText().contains("Battery type: 0"));
                Assert.assertTrue(ScanToolText.getText().contains("HW revision: 03.05"));
                Assert.assertTrue(ScanToolText.getText().contains("SW revision: 04.06"));
                Assert.assertTrue(ScanToolText.getText().contains("Fastenings during last service: 0"));
                System.out.println("Device BX 3 real device 1 has been found and verified");
            }catch (AssertionError e){
                System.out.println("Device BX 3 real device 1 has an error");
            }
        }

        if(ScanToolText.getText().contains("name: DX 9")& ScanToolText.getText().contains("address: F8:31:D1:D2:80:3D") ) {
            try {
                assertTrue(ScanToolText.getText().contains("address: F8:31:D1:D2:80:3D"));
                assertTrue(ScanToolText.getText().contains("rssi:"));
                assertTrue(ScanToolText.getText().contains("Version: V0"));
                assertTrue(ScanToolText.getText().contains("Region: EUROPE"));
                assertTrue(ScanToolText.getText().contains("VFE: 0"));
                assertTrue(ScanToolText.getText().contains("FFE: 1049408"));
                assertTrue(ScanToolText.getText().contains("Serial: 36180020"));
                assertTrue(ScanToolText.getText().contains("Total number of fastenings: 28"));
                assertTrue(ScanToolText.getText().contains("Number of fastenings since last cleaning: 0"));
                assertTrue(ScanToolText.getText().contains("Number of fastenings since last maintenance: 28"));
                assertTrue(ScanToolText.getText().contains("Cleaning interval: 8000"));
                assertTrue(ScanToolText.getText().contains("Maintenance interval: 24000"));
                assertTrue(ScanToolText.getText().contains("Battery status: 100 %"));
                assertTrue(ScanToolText.getText().contains("HW revision: 01.03"));
                assertTrue(ScanToolText.getText().contains("SW revision: 03.50"));
                assertTrue(ScanToolText.getText().contains("Time from last cleaning: 2018-06-04 18:22:47"));
                assertTrue(ScanToolText.getText().contains("Time from last service: 2018-05-07 13:25:00"));
                System.out.println("Device DX 9 number 2 has been found and verified successfully");
            }catch (AssertionError e){
                System.out.println("Device DX 9 number 2 has an error");
            }
        }

        if(ScanToolText.getText().contains("name: DX 9")& ScanToolText.getText().contains("address: E4:EA:0D:1C:E4:D8") ) {
            try {
                assertTrue(ScanToolText.getText().contains("address: E4:EA:0D:1C:E4:D8"));
                assertTrue(ScanToolText.getText().contains("rssi:"));
                assertTrue(ScanToolText.getText().contains("Version: V0"));
                assertTrue(ScanToolText.getText().contains("Region: EUROPE"));
                assertTrue(ScanToolText.getText().contains("VFE: 0"));
                assertTrue(ScanToolText.getText().contains("FFE: 1049408"));
                assertTrue(ScanToolText.getText().contains("Serial: 36180056"));
                assertTrue(ScanToolText.getText().contains("Total number of fastenings: 4"));
                assertTrue(ScanToolText.getText().contains("Number of fastenings since last cleaning: 0"));
                assertTrue(ScanToolText.getText().contains("Number of fastenings since last maintenance: 4"));
                assertTrue(ScanToolText.getText().contains("Cleaning interval: 8000"));
                assertTrue(ScanToolText.getText().contains("Maintenance interval: 24000"));
                assertTrue(ScanToolText.getText().contains("Battery status: 100 %"));
                assertTrue(ScanToolText.getText().contains("HW revision: 01.03"));
                assertTrue(ScanToolText.getText().contains("SW revision: 03.50"));
                assertTrue(ScanToolText.getText().contains("Time from last cleaning: 2018-06-04 18:56:08"));
                assertTrue(ScanToolText.getText().contains("Time from last service: 2018-02-06 16:00:31"));
                System.out.println("Device DX 9 number 1 has been found and verified successfully");
            }catch (AssertionError e){
                System.out.println("Device DX 9 number 1 has an error");
            }
        }

        if(ScanToolText.getText().contains("name: DX 5 F10")& ScanToolText.getText().contains("address: C3:A2:F5:94:F7:1E") ) {
            try {
                assertTrue(ScanToolText.getText().contains("address: C3:A2:F5:94:F7:1E"));
                assertTrue(ScanToolText.getText().contains("rssi:"));
                assertTrue(ScanToolText.getText().contains("Version: V0"));
                assertTrue(ScanToolText.getText().contains("Region: ORIGINAL"));
                assertTrue(ScanToolText.getText().contains("VFE: 2142665"));
                assertTrue(ScanToolText.getText().contains("FFE: 2134811"));
                assertTrue(ScanToolText.getText().contains("Serial: 234567840"));
                assertTrue(ScanToolText.getText().contains("Total number of fastenings: 51"));
                assertTrue(ScanToolText.getText().contains("Number of fastenings since last cleaning: 41"));
                assertTrue(ScanToolText.getText().contains("Number of fastenings since last maintenance: 31"));
                assertTrue(ScanToolText.getText().contains("Cleaning interval: 8000"));
                assertTrue(ScanToolText.getText().contains("Maintenance interval: 30000"));
                assertTrue(ScanToolText.getText().contains("Battery status: 60 %"));
                assertTrue(ScanToolText.getText().contains("HW revision: 02.09"));
                assertTrue(ScanToolText.getText().contains("SW revision: 03.00"));
                assertTrue(ScanToolText.getText().contains("Time from last cleaning: 2016-02-01 22:19:55"));
                assertTrue(ScanToolText.getText().contains("Time from last service: 2016-02-01 22:19:55"));
                System.out.println("Device DX 5F10 number 2 has been found and verified successfully");
            }catch (AssertionError e){
                System.out.println("Device DX 5F10 number 2 has an error");
            }
        }

        if(ScanToolText.getText().contains("name: DX 5 F10")& ScanToolText.getText().contains("address: E6:9E:91:19:8C:4C") ) {
            try {
                assertTrue(ScanToolText.getText().contains("address: E6:9E:91:19:8C:4C"));
                assertTrue(ScanToolText.getText().contains("rssi:"));
                assertTrue(ScanToolText.getText().contains("Version: V0"));
                assertTrue(ScanToolText.getText().contains("Region: ORIGINAL"));
                assertTrue(ScanToolText.getText().contains("VFE: 2142665"));
                assertTrue(ScanToolText.getText().contains("FFE: 2134811"));
                assertTrue(ScanToolText.getText().contains("Serial: 234567840"));
                assertTrue(ScanToolText.getText().contains("Total number of fastenings: 51"));
                assertTrue(ScanToolText.getText().contains("Number of fastenings since last cleaning: 41"));
                assertTrue(ScanToolText.getText().contains("Number of fastenings since last maintenance: 31"));
                assertTrue(ScanToolText.getText().contains("Cleaning interval: 8000"));
                assertTrue(ScanToolText.getText().contains("Maintenance interval: 30000"));
                assertTrue(ScanToolText.getText().contains("Battery status: 60 %"));
                assertTrue(ScanToolText.getText().contains("HW revision: 02.09"));
                assertTrue(ScanToolText.getText().contains("SW revision: 03.00"));
                assertTrue(ScanToolText.getText().contains("Time from last cleaning: 2016-02-01 22:19:55"));
                assertTrue(ScanToolText.getText().contains("Time from last service: 2016-02-01 22:19:55"));
                System.out.println("Device DX 5F10 number 1 has been found and verified successfully");
            }catch (AssertionError e){
                System.out.println("Device DX 5F10 number 1 has an error");
            }
        }

        if (ScanToolText.getText().contains("name: BX 3") & ScanToolText.getText().contains("address: 00:13:43:2D:AD:88")) {
            try {
                Assert.assertTrue(ScanToolText.getText().contains("address: 00:13:43:2D:AD:88"));
                Assert.assertTrue(ScanToolText.getText().contains("rssi:"));
                Assert.assertTrue(ScanToolText.getText().contains("Version: V0"));
                Assert.assertTrue(ScanToolText.getText().contains("Region: ORIGINAL"));
                Assert.assertTrue(ScanToolText.getText().contains("VFE: 2161621"));
                Assert.assertTrue(ScanToolText.getText().contains("FFE: 2152381"));
                Assert.assertTrue(ScanToolText.getText().contains("Serial: 123"));
                Assert.assertTrue(ScanToolText.getText().contains("Total number of fastenings: 1"));
                Assert.assertTrue(ScanToolText.getText().contains("Active error: 0"));
                Assert.assertTrue(ScanToolText.getText().contains("Battery voltage: 20.9 V"));
                Assert.assertTrue(ScanToolText.getText().contains("Temperature motor: 0 °C"));
                Assert.assertTrue(ScanToolText.getText().contains("Broadcast version: 1"));
                Assert.assertTrue(ScanToolText.getText().contains("Battery type: 1"));
                Assert.assertTrue(ScanToolText.getText().contains("HW revision: 03.05"));
                Assert.assertTrue(ScanToolText.getText().contains("SW revision: 04.07"));
                Assert.assertTrue(ScanToolText.getText().contains("Fastenings during last service: 0"));
                System.out.println("Device BX 3 number 1 has been found and verified successfully");
            }catch (AssertionError e){
                System.out.println("Device BX 3 number 1 has an error");
            }
        }

        if (ScanToolText.getText().contains("name: BX 3") & ScanToolText.getText().contains("address: E6:9E:91:19:8C:4C")) {
            try {
                Assert.assertTrue(ScanToolText.getText().contains("address: E6:9E:91:19:8C:4C"));
                Assert.assertTrue(ScanToolText.getText().contains("rssi:"));
                Assert.assertTrue(ScanToolText.getText().contains("Version: V0"));
                Assert.assertTrue(ScanToolText.getText().contains("Region: ORIGINAL"));
                Assert.assertTrue(ScanToolText.getText().contains("VFE: 2161621"));
                Assert.assertTrue(ScanToolText.getText().contains("FFE: 2152381"));
                Assert.assertTrue(ScanToolText.getText().contains("Serial: 234567840"));
                Assert.assertTrue(ScanToolText.getText().contains("Total number of fastenings: 51"));
                Assert.assertTrue(ScanToolText.getText().contains("Active error: 1"));
                Assert.assertTrue(ScanToolText.getText().contains("Battery voltage: 24.0 V"));
                Assert.assertTrue(ScanToolText.getText().contains("Temperature motor: 40 °C"));
                Assert.assertTrue(ScanToolText.getText().contains("Broadcast version: 1"));
                Assert.assertTrue(ScanToolText.getText().contains("Battery type: 1"));
                Assert.assertTrue(ScanToolText.getText().contains("HW revision: 02.09"));
                Assert.assertTrue(ScanToolText.getText().contains("SW revision: 03.10"));
                Assert.assertTrue(ScanToolText.getText().contains("Fastenings during last service: 21"));
                System.out.println("Device BX3Nordic1 number 1 has been found and verified successfully");
            }catch (AssertionError e){
                System.out.println("Device BX3Nordic1 number 1 has an error");
            }
        }

        if (ScanToolText.getText().contains("name: BX 3") & ScanToolText.getText().contains("address: C3:A2:F5:94:F7:1E")) {
            try {
                Assert.assertTrue(ScanToolText.getText().contains("address: C3:A2:F5:94:F7:1E"));
                Assert.assertTrue(ScanToolText.getText().contains("rssi:"));
                Assert.assertTrue(ScanToolText.getText().contains("Version: V0"));
                Assert.assertTrue(ScanToolText.getText().contains("Region: ORIGINAL"));
                Assert.assertTrue(ScanToolText.getText().contains("VFE: 2161621"));
                Assert.assertTrue(ScanToolText.getText().contains("FFE: 2152381"));
                Assert.assertTrue(ScanToolText.getText().contains("Serial: 234567846"));
                Assert.assertTrue(ScanToolText.getText().contains("Total number of fastenings: 51"));
                Assert.assertTrue(ScanToolText.getText().contains("Active error: 0"));
                Assert.assertTrue(ScanToolText.getText().contains("Battery voltage: 24.0 V"));
                Assert.assertTrue(ScanToolText.getText().contains("Temperature motor: 40 °C"));
                Assert.assertTrue(ScanToolText.getText().contains("Broadcast version: 1"));
                Assert.assertTrue(ScanToolText.getText().contains("Battery type: 0"));
                Assert.assertTrue(ScanToolText.getText().contains("HW revision: 02.09"));
                Assert.assertTrue(ScanToolText.getText().contains("SW revision: 03.10"));
                Assert.assertTrue(ScanToolText.getText().contains("Fastenings during last service: 21"));
                System.out.println("Device BX3Nordic2 number 2 has been found and verified successfully");
            }catch (AssertionError e){
                System.out.println("Device BX3Nordic2 number 2 has an error");
            }
        }
    }
}
