package core.browser;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;

import java.io.File;

public class MEDriverManager extends DriverManager {
    private EdgeDriverService edgeDriverService;

    @Override
    public void startService() {
        if (null == edgeDriverService) {
            try {
                edgeDriverService = new EdgeDriverService.Builder()
                        .usingDriverExecutable(new File("C:\\Windows\\System32\\MicrosoftWebDriver.exe"))
                        .usingAnyFreePort()
                        .build();
                edgeDriverService.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopService() {
        if (null != edgeDriverService && edgeDriverService.isRunning())
            edgeDriverService.stop();
    }

    @Override
    public void createDriver() {
        driver = new EdgeDriver(edgeDriverService);
    }
}
