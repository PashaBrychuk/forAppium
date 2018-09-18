package sdktest;

import org.openqa.selenium.Dimension;

public class ScreenSize extends AppiumSetUp{

    private Dimension size;

    public int y1;
    public int y2;
    public int x;

    public void updateSize() {
        size = this.driver.manage().window().getSize();
        y1 = (int) ((double) size.height * 0.7D);
        y2 = (int) ((double) size.height * 0.20);
        x = (int) ((double) size.width * 0.50);
    }
}
