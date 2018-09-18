package sdktest.demomode;

import org.junit.Test;
import sdktest.AppiumSetUp;
import sdktest.BLESDKProjectMethods;
import sdktest.CommandTab;

import java.io.IOException;

public class DemoModeDeactivate extends AppiumSetUp {
    private int ElementsNumber;
    private String PathToElement = "";
    private String ElementName = "DX 5";
    @Test
    public void DemoModeDeactivate () throws IOException {

        CommandTab commandTab = new CommandTab(driver);

        BLESDKProjectMethods.StartScanElements(driver);
        ElementsNumber = BLESDKProjectMethods.FindSpecificElement(ElementName);
        System.out.println(ElementsNumber);
        commandTab.MoveToCommandTabForElement(ElementsNumber);
        BLESDKProjectMethods.ConnectToTool(driver, PathToElement);

        BLESDKProjectMethods.ActivateDemoMode(driver);

        BLESDKProjectMethods.DeactivateDemoMode(driver);

    }
}
