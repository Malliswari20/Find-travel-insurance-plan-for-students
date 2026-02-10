package utils;


import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;

public class AllureReport {

    public static void attachText(String name, String content) {
        Allure.addAttachment(name, content);
    }

    public static void attachScreenshot(String name, byte[] screenshotBytes) {
        Allure.addAttachment(name, new ByteArrayInputStream(screenshotBytes));
    }

    public static void attachHtml(String name, String htmlContent) {
        Allure.addAttachment(name, "text/html", htmlContent, ".html");
    }
}
