package sdktest.activitytrackerlogs;

import org.junit.Test;
import sdktest.AppiumSetUp;
import sdktest.BLESDKProjectMethods;
import sdktest.CommandTab;

import java.io.IOException;

public class ReadActivityLogDays extends AppiumSetUp {
    String ElementName = "DX 5";
    boolean CheckElement = false;
    int ElementsNumber;
    String LogType = "Daily";

    @Test
    public void ReadActivityLogDaysDX5 () throws IOException {

        CommandTab commandTab = new CommandTab(driver);

        BLESDKProjectMethods.StartScanElements(driver);
        ElementsNumber = BLESDKProjectMethods.FindSpecificElement(ElementName);

        commandTab.MoveToCommandTabForElement(ElementsNumber);
        commandTab.ConnectToTool();
        commandTab.EnterBlockAdress("0");
        commandTab.EnterBlockLenght("1");
        commandTab.ReadLogButtonClick();

        commandTab.CheckActivityTrackerLogResult(LogType);
    }
}
