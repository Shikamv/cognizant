package utils;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.time.LocalDateTime;

public class ScreenshotUtils {

    public static void takeElementScreenshot(WebElement element, String fileName) {

        try {
            // Create screenshots directory if it doesn't exist
            File screenshotDir = new File("screenshots");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            File src = element.getScreenshotAs(OutputType.FILE);

            String timestamp = LocalDateTime.now()
                    .toString().replace(":", "-");

            File dest = new File(
                    screenshotDir,
                    fileName + "_" + timestamp + ".png"
            );

            FileHandler.copy(src, dest);

            System.out.println("Screenshot saved: " + dest.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}