package sdktest.findme;

import org.junit.Test;
import sdktest.AppiumSetUp;
import sdktest.BLESDKProjectMethods;
import sdktest.CommandTab;

import java.io.IOException;

public class FindMeCommand extends AppiumSetUp {
    private int ElementsNumber;
    private String PathToElement = "";
    private String ElementName = "BX";
    @Test
    public void FindMe() throws IOException {

        CommandTab commandTab = new CommandTab(driver);

        BLESDKProjectMethods.StartScanElements(driver);
        ElementsNumber = BLESDKProjectMethods.FindSpecificElement(ElementName);

        commandTab.MoveToCommandTabForElement(ElementsNumber);
        BLESDKProjectMethods.ConnectToTool(driver, PathToElement);

        BLESDKProjectMethods.FindMe(driver);

    }
}
