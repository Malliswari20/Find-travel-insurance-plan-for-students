package utils;

import org.openqa.selenium.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshot {

    public static String capture(WebDriver driver, String screenshotName) {

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // ✅ Ensure folder exists
        File folder = new File(System.getProperty("user.dir") + "/screenshots");
        if (!folder.exists()) folder.mkdirs();

        String filePath = folder.getAbsolutePath() + "/" + screenshotName + "_" + timestamp + ".png";

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File(filePath);

        try {
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save screenshot at: " + filePath, e);
        }

        return filePath;
    }
}