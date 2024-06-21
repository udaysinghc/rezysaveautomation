package com.qa.rezysaveplatform.base;

import java.util.Properties;

import com.qa.rezysaveplatform.pages.DashboardPage;
import com.qa.rezysaveplatform.pages.InventoryPage;
import com.qa.rezysaveplatform.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Optional;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.rezysaveplatform.factory.DriverFactory;

public class BaseTest {

  protected WebDriver driver;
  protected Properties prop;
  protected LoginPage loginPage;
  protected DashboardPage dashboardPage;
  protected SoftAssert softAssert;
  protected DriverFactory df;
  protected InventoryPage inventoryPage;

  @Parameters("browser")
  @BeforeTest
  public void setup(@Optional("chrome") String browserName) throws InterruptedException {
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
