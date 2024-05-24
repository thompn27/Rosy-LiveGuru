package com.panda.commons;


import lombok.Getter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

public class BaseTest {
    protected final Log log;

    //protected static WebDriver driver;
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    public BaseTest() {
        log = LogFactory.getLog(getClass());
    }

    public WebDriver getBrowserDriver(String browserName) {
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
        switch (browser) {
            case FIREFOX:
                //driver = new FirefoxDriver();
                driver.set(new FirefoxDriver());
                break;
            case H_FIREFOX:
                FirefoxOptions optionsFirefox = new FirefoxOptions();
                optionsFirefox.addArguments("--headless");
                optionsFirefox.addArguments("window-size=1920x1080");
                //driver = new FirefoxDriver(optionsFirefox);
                driver.set(new FirefoxDriver(optionsFirefox));
                break;
            case CHROME:
                //driver = new ChromeDriver();
                driver.set(new ChromeDriver());
                break;
            case H_CHROME:
                ChromeOptions optionsChrome = new ChromeOptions();
                optionsChrome.addArguments("--headless");
                optionsChrome.addArguments("window-size=1920x1080");
                //driver = new ChromeDriver(optionsChrome);
                driver.set(new ChromeDriver(optionsChrome));
                break;
            case EDGE:
                //driver = new EdgeDriver();
                driver.set(new EdgeDriver());
                break;
            case SAFARI:
                //driver = new SafariDriver();
                driver.set(new SafariDriver());
                break;
            default:
                throw new RuntimeException("Wrong browser!");
        }
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get().manage().window().maximize();
        driver.get().get(GlobalConstants.getGlobalConstants().getuserPandaPageUrl());
        return driver.get();
    }
    public static WebDriver getDriver() {
        return driver.get();
    }
    public String getRandNumber() {
        Random rand = new Random();
        return String.valueOf(rand.nextInt(9999));
    }

    protected void closeBrowserDriver() {
        String cmd = null;
        try {
            String osName = System.getProperty("os.name").toLowerCase();
            log.info("OS name = " + osName);

            String driverInstanceName = driver.toString().toLowerCase();
            log.info("Driver instance name = " + driverInstanceName);

            String browserDriverName = null;

            if (driverInstanceName.contains("chrome")) {
                browserDriverName = "chromedriver";
            } else if (driverInstanceName.contains("internetexplorer")) {
                browserDriverName = "IEDriverServer";
            } else if (driverInstanceName.contains("firefox")) {
                browserDriverName = "geckodriver";
            } else if (driverInstanceName.contains("edge")) {
                browserDriverName = "msedgedriver";
            } else if (driverInstanceName.contains("opera")) {
                browserDriverName = "operadriver";
            } else {
                browserDriverName = "safaridriver";
            }
            if (osName.contains("window")) {
                cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
            } else {
                cmd = "pkill " + browserDriverName;
            }

            if (driver != null) {
                driver.get().manage().deleteAllCookies();
                driver.get().quit();

                driver.remove();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @BeforeSuite
    public void deleteFileInReport() {
        // Remove all file in Allure attachment (json file)
        deleteAllFileInFolder("allure-json");
    }

    public void deleteAllFileInFolder(String folderName) {
        try {
            String pathFolderDownload = GlobalConstants.getGlobalConstants().getProjectPath() + File.separator + folderName;
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            if (listOfFiles.length != 0) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("environment.properties")) {
                        new File(listOfFiles[i].toString()).delete();
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}
