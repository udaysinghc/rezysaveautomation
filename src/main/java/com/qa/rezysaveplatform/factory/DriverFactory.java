package com.qa.rezysaveplatform.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.rezysaveplatform.exception.FrameworkException;

public class DriverFactory {
  Properties prop;
  OptionsManager optionsManager;
  public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
  public static String highlight = null;

  public WebDriver initDriver(Properties prop) {
    String browserName = prop.getProperty("browser");
    highlight = prop.getProperty("highlight");
    optionsManager = new OptionsManager(prop);
    switch (browserName.toLowerCase().trim()) {
      case "chrome":
        tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
        break;

      case "firefox":
        tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
        break;
      case "edge":
        tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));

        break;

      case "safari":
        tlDriver.set(new SafariDriver());
        break;
      default:
        throw new FrameworkException("No Browser Found...");
    }
    getDriver().manage().deleteAllCookies();
    getDriver().manage().window().maximize();
    getDriver().get(prop.getProperty("url"));
    return getDriver();
  }

  public static WebDriver getDriver() {
    return tlDriver.get();
  }

  public Properties initProp() {
    FileInputStream ip = null;
    prop = new Properties();
    String envName = System.getProperty("env");
    try {
      if (envName == null) {
        ip = new FileInputStream("./src/test/resources/config/config.dev.properties");
      } else {
        switch (envName.toLowerCase().trim()) {
          case "qa":
            ip = new FileInputStream("./src/test/resources/config/config.dev.properties");
            break;

          default:
            throw new FrameworkException("Wrong Env Name: " + envName);
        }
      }
    } catch (FileNotFoundException e) {

    }

    try {
      prop.load(ip);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return prop;
  }

  /** take screenshot */
  public static String getScreenshot(String methodName) {

    File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

    String path =
        System.getProperty("user.dir")
            + "/screenshot/"
            + methodName
            + "_"
            + System.currentTimeMillis()
            + ".png";

    File destination = new File(path);

    try {
      FileHandler.copy(srcFile, destination);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return path;
  }
}
