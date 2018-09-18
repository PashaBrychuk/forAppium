package sdktest.demomode;

import org.junit.Test;
import sdktest.AppiumSetUp;
import sdktest.BLESDKProjectMethods;
import sdktest.CommandTab;

import java.io.IOException;

public class DemoModeBadCommand extends AppiumSetUp {
    private int ElementsNumber;
    private String PathToElement = "";
    private String ElementName = "DX 5";

    @Test
    public void DemoMode() throws IOException {

        CommandTab commandTab = new CommandTab(driver);

        BLESDKProjectMethods.StartScanElements(driver);
        ElementsNumber = BLESDKProjectMethods.FindSpecificElement(ElementName);

        commandTab.MoveToCommandTabForElement(ElementsNumber);

        BLESDKProjectMethods.ConnectToTool(driver, PathToElement);

        BLESDKProjectMethods.ActivateDemoMode(driver);

        BLESDKProjectMethods.BadCommandDemoMode(driver);

        BLESDKProjectMethods.DeactivateDemoMode(driver);

        BLESDKProjectMethods.BadCommandDemoMode(driver);

    }
}
