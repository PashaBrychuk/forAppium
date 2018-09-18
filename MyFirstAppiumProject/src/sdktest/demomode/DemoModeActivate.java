package sdktest.demomode;

import org.junit.Test;
import sdktest.AppiumSetUp;
import sdktest.BLESDKProjectMethods;
import sdktest.CommandTab;

import java.io.IOException;

public class DemoModeActivate extends AppiumSetUp {
    int ElementsNumber;
    String PathToElement = "";
    String ElementName = "DX 5";

    @Test
    public void ActivateDemoMode() throws IOException {


    CommandTab commandTab = new CommandTab(driver);

    BLESDKProjectMethods.StartScanElements(driver);
    ElementsNumber = BLESDKProjectMethods.FindSpecificElement(ElementName);

    commandTab.MoveToCommandTabForElement(ElementsNumber);
    BLESDKProjectMethods.ConnectToTool(driver, PathToElement);
    BLESDKProjectMethods.ActivateDemoMode(driver);

    }


    }
