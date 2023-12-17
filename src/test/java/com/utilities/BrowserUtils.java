package com.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class BrowserUtils {
    public static void waitForFileToDownload(String downloadFolderPath) {
        Path filePath = Paths.get(downloadFolderPath);
        long endTime = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(500);

        while (System.currentTimeMillis() < endTime) {
            if (java.nio.file.Files.exists(filePath)) {
                return;
            }

            try {
                Thread.sleep(1000); // 1 saniye bekle
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void wait(int sec) {
        sec = sec * 1000;
        try {
            Thread.sleep(sec);
        } catch (InterruptedException e) {
            System.out.println("fail");
        }
    }

    public static void wait(double sec) {
        sec = sec * 1000;
        try {
            Thread.sleep((long) sec);
        } catch (InterruptedException e) {
            System.out.println("fail");
        }
    }

    public static String runTesseract() {
        try {
            // Komutu oluştur
            ProcessBuilder builder = new ProcessBuilder("C:\\Program Files\\Tesseract-OCR\\Tesseract", "C:\\Users\\Noob\\Desktop\\trySomething\\screenshot.png", "sonuc");

            // Çalışma dizinini ayarla (gerektiğinde değiştirin)
            builder.directory(new File("C:\\Users\\Noob"));

            // Çıktı ve hata akışlarını birleştir
            builder.redirectErrorStream(true);

            // İşlemi başlat
            Process process = builder.start();

            // İşlem çıktısını oku
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }

            // Dosyadaki metni oku
            String textResult = "";
            try (BufferedReader fileReader = new BufferedReader(new FileReader("C:\\Users\\Noob\\sonuc.txt"))) {
                String fileLine;
                while ((fileLine = fileReader.readLine()) != null) {
                    textResult += fileLine + "\n";
                }
            }

            return textResult;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void takeScreenShotElement(WebElement capthaImage) {
        int x = capthaImage.getLocation().getX();
        int y = capthaImage.getLocation().getY();
        int width = capthaImage.getSize().getWidth();
        int height = capthaImage.getSize().getHeight();
        File screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            BufferedImage fullImage = ImageIO.read(screenshot);
            BufferedImage elementScreenshot = fullImage.getSubimage(x, y, width, height);
            ImageIO.write(elementScreenshot, "png", new File("C:\\Users\\Noob\\Desktop\\trySomething\\screenshot.png"));
        } catch (IOException e) {
            System.out.println("fail");
        }
    }

    public static void clickElementJS(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void scrollByJs(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public enum ActionType {
        CLICK, HOVER, DOUBLE_CLICK, RIGHT_CLICK
    }

    public static void performAction(WebElement element, ActionType actionType) {
        if (element != null && element.isDisplayed()) {
            Actions actions = new Actions(Driver.getDriver());
            switch (actionType) {
                case CLICK:
                    actions.click(element).perform();
                    break;
                case HOVER:
                    actions.moveToElement(element).perform();
                    break;
                case DOUBLE_CLICK:
                    actions.doubleClick(element).perform();
                    break;
                case RIGHT_CLICK:
                    actions.contextClick(element).perform();
                    break;
                default:
                    System.out.println("Unsupported action type.");
                    break;
            }
        } else {
            System.out.println("Element null or not visible.");
        }
    }
    public static void scrollDown(WebDriver driver, int pixels) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String script = "window.scrollBy(0, " + pixels + ");";
        jsExecutor.executeScript(script);
    }
    public static String convertRGBAtoHex(String rgbaColor) {
        String[] rgbaValues = rgbaColor.replace("rgba(", "").replace(")", "").split(",");
        int r = Integer.parseInt(rgbaValues[0].trim());
        int g = Integer.parseInt(rgbaValues[1].trim());
        int b = Integer.parseInt(rgbaValues[2].trim());

        Color color = new Color(r, g, b);
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }
}