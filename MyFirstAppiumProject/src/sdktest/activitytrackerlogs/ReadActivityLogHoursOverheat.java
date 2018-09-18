package sdktest.activitytrackerlogs;

import org.junit.Test;
import sdktest.AppiumSetUp;
import sdktest.BLESDKProjectMethods;
import sdktest.CommandTab;

import java.io.IOException;

public class ReadActivityLogHoursOverheat extends AppiumSetUp {
    String ElementName = "DX 9";
    boolean CheckElement = false;
    int ElementsNumber;
    String LogType = "DaysDX9Overheat";

    @Test
    public void ReadActivityLogDaysDX9Overheat () throws IOException {

        CommandTab commandTab = new CommandTab(driver);

        BLESDKProjectMethods.StartScanElements(driver);
        ElementsNumber = BLESDKProjectMethods.FindSpecificElement(ElementName);

        commandTab.MoveToCommandTabForElement(ElementsNumber);
        commandTab.ConnectToTool();
        commandTab.EnterBlockAdress("0");
        commandTab.EnterBlockLenght("1");

        commandTab.SetOverheatMode();
        commandTab.ReadLogButtonClick();

        commandTab.CheckActivityTrackerLogResult(LogType);
    }
}
