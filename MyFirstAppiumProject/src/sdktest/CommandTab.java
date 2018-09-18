package sdktest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CommandTab {
    private AppiumDriver mDriver;


    public CommandTab(AppiumDriver driver) {
        mDriver = driver;
        PageFactory.initElements(driver, this);
    }

    public void MoveToCommandTabForElement (int ElementNumber){

        List<WebElement> ElementsList = (List<WebElement>) mDriver.findElements(By.id("com.hilti.hiltibletestapp:id/text"));
        ElementsList.get(ElementNumber).click();

    }

    public void EnterBlockAdress (String BlockAdress) {
        WebElement numbersEditText = mDriver.findElement(By.id("com.hilti.hiltibletestapp:id/numbersEditText"));
        numbersEditText.clear();
        numbersEditText.sendKeys(BlockAdress);
        ((AndroidDriver)mDriver).pressKeyCode(AndroidKeyCode.BACK);
    }

    public void ConnectToTool (){
        String RequestStatus = "Request status: SUCCESS";
        String CallBack = "Callback: onConnected";
        String ToolStatus = "Tool status: CONNECTED";

        WebElement ConnectButton = mDriver.findElement(By.id("com.hilti.hiltibletestapp:id/connect"));
        ConnectButton.click();
        try {
            Thread.sleep(5000);
        } catch (Exception ignore) {
        }
        ;

        WebElement ConnectRequestStatus = mDriver.findElement(By.id("com.hilti.hiltibletestapp:id/requestStatusTextView"));
        assertEquals(RequestStatus, ConnectRequestStatus.getText());

        WebElement ConnectCallback = mDriver.findElement(By.id("com.hilti.hiltibletestapp:id/callbackTextView"));
        assertEquals(CallBack, ConnectCallback.getText());

        WebElement ConnectToolStatus = mDriver.findElement(By.id("com.hilti.hiltibletestapp:id/toolStatusTextView"));
        assertEquals(ToolStatus, ConnectToolStatus.getText());
        System.out.println("Device has been connected successfully");

    }

    public void EnterBlockLenght (String BlockLenght) {

        WebElement numbersBlockLenght = mDriver.findElement(By.id("com.hilti.hiltibletestapp:id/periodEditText"));
        numbersBlockLenght.clear();
        numbersBlockLenght.sendKeys(BlockLenght);
        ((AndroidDriver)mDriver).pressKeyCode(AndroidKeyCode.BACK);
    }

    public void ReadLogButtonClick ( ) {

        WebElement ReadLogButton = mDriver.findElement(By.id("com.hilti.hiltibletestapp:id/readLogs"));
        ReadLogButton.click();
    }

    public void CheckActivityTrackerLogResult (String LogType) {
        String TrackerInput = "";
        String TrackerOutput = "";
        String TrackerResponse = "";


        if (LogType.equals("Daily")){
           TrackerInput = "Input: 0D 00 40 08 00 45";
           TrackerOutput = "Output: 0D 00 40 08 00 45 00 00 00 00 68 AC 00 00 D2 25";
           TrackerResponse = "Parsed Response:\n fastenings: 2 date:" ;
        }
        if (LogType.equals("Hourly")){
            TrackerInput = "Input: 0D 00 20 08 00 25";
            TrackerOutput = "Output: 0D 00 20 08 00 25 00 00 00 00 68 AC 00 00 D2 25";
            TrackerResponse = "Parsed Response:\n fastenings: 2 date:";
        }

        if (LogType.equals("DaysDX9Overheat")){
            TrackerInput = "Input: 0D 00 C2 08 00 C7";
            TrackerOutput = "Output: 0D 00 C2 08 00 C7 1C FC D3 04 00 00 00 00 9C 83";
            TrackerResponse ="Parsed Response:\n\nfastenings: 0\ndate: Thu Jul 26 15:25:00 EEST 2018";
        }

        WebElement ActivityTrackerInput = mDriver.findElement(By.id("com.hilti.hiltibletestapp:id/input"));
        WebElement ActivityTrackerOutput = mDriver.findElement(By.id("com.hilti.hiltibletestapp:id/output"));
        WebElement ActivityTrackerResponse = mDriver.findElement(By.id("com.hilti.hiltibletestapp:id/response"));

        assertEquals(TrackerInput, ActivityTrackerInput.getText());
        assertEquals(TrackerOutput,ActivityTrackerOutput.getText());
        assertEquals(TrackerResponse,ActivityTrackerResponse.getText());

        //assertTrue(ActivityTrackerResponse.getText().contains(TrackerResponse));
        //assertTrue(ActivityTrackerResponse.getText().contains(TrackerResponse));
    }

    public void SetHoursActivityLog () {
        WebElement HoursCheck = mDriver.findElement(By.id("com.hilti.hiltibletestapp:id/hourRadioButton"));
        HoursCheck.click();

    }

    public void SetOverheatMode () {
        WebElement OverheatCheck = mDriver.findElement(By.id("com.hilti.hiltibletestapp:id/overheatCheckBox"));
        OverheatCheck.click();
    }

}
