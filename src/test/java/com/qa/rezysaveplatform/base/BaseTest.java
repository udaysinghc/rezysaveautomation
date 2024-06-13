package com.qa.rezysaveplatform.base;

import java.util.Properties;

import com.qa.rezysaveplatform.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.rezysaveplatform.factory.DriverFactory;

public class BaseTest {

  protected WebDriver driver;
  protected Properties prop;
  protected LoginPage loginPage;
  protected SoftAssert softAssert;
  protected DriverFactory df;

  @Parameters("browser")
  @BeforeTest
  public void setup(String browserName) throws InterruptedException {
    df = new DriverFactory();
    prop = df.initProp();

    if (browserName != null) {
      prop.setProperty("browser", browserName);
    }

    driver = df.initDriver(prop);
    loginPage = new LoginPage(driver);
    softAssert = new SoftAssert();
  }

  @AfterTest
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}
