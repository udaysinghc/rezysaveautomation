package com.qa.rezysaveplatform.pages;

import com.qa.rezysaveplatform.utils.ElementUtil;
import com.qa.rezysaveplatform.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class InventoryPage {
  private final WebDriver driver;
  private final ElementUtil eleUtil;
  private final JavaScriptUtil javaScriptUtil;

  private final By listOfDashboard = By.cssSelector("div [class*='MuiTypography']");
  private final By addButton =
      By.cssSelector("[class*='MuiCardHeader-root'] button[class*='MuiButton-containedPrimary']");
  private final By deviceCategoryFiled = By.id("combo-box-categpry");
  private final By listOfDevicesFiled = By.cssSelector("[role*='option']");
  private final By devicesFiled = By.id("combo-box-device");
  private final By serialFiled = By.id("outlined-serial");

  private final By addInventory =
      By.cssSelector(
          "[class*='MuiDialogActions-root'] [class*='css-20vywf-MuiButtonBase-root-MuiButton-root']");

  private final By successMessage = By.cssSelector("[class*='MuiAlert-message']");

  private final By productName = By.cssSelector("[class*='productName']");

  private final By serialNumberText = By.xpath("//table//tbody//tr//td[2]");

  private final By delete = By.cssSelector("[alt*='delete']");
  private final By backIcon = By.cssSelector("[alt='back']");
  private final By yesButton = By.cssSelector("[class*='delete-yes']");

  public InventoryPage(WebDriver driver) {
    this.driver = driver;
    eleUtil = new ElementUtil(this.driver);
    javaScriptUtil = new JavaScriptUtil(driver);
  }

  public void clickOnInventoryOption(String nameOfDashboard) throws InterruptedException {
    List<WebElement> dashboardItems = driver.findElements(listOfDashboard);
    for (WebElement item : dashboardItems) {
      System.out.println("test of list" + item.getText());
      if (item.getText().contains(nameOfDashboard)) {
        item.click();
        break;
      }
    }
  }

  public void addNewInventory(String deviceCategory, String deviceName, String serialNumber)
      throws InterruptedException {
    eleUtil.waitForPresenceOfElement(addButton, 20);
    eleUtil.doClick(addButton);
    eleUtil.waitForPresenceOfElement(deviceCategoryFiled, 20);
    eleUtil.doClick(deviceCategoryFiled);
    List<WebElement> category = driver.findElements(listOfDevicesFiled);
    for (WebElement item : category) {
      if (item.getText().contains(deviceCategory)) {
        item.click();
        break;
      }
    }
    eleUtil.waitForPresenceOfElement(devicesFiled, 30);
    eleUtil.doClick(devicesFiled);
    List<WebElement> devices = driver.findElements(listOfDevicesFiled);
    for (WebElement item : devices) {
      if (item.getText().contains(deviceName)) {
        item.click();
        break;
      }
    }
    eleUtil.doClick(serialFiled);
    eleUtil.doSendKeys(serialFiled, serialNumber);
    eleUtil.doClick(addInventory);
    eleUtil.waitForPresenceOfElements(successMessage, 20);
    WebElement text = driver.findElement(successMessage);
    if (text.getText().contains("Failed to update data")) {
      eleUtil.waitForINVisibilityOfElement(successMessage, 40);
      eleUtil.waitForPresenceOfElement(productName, 20);
      List<WebElement> product = driver.findElements(productName);
      for (WebElement item : product) {
        if (item.getText().contains(deviceCategory)) {
          item.click();
          break;
        }
      }
      eleUtil.waitForPresenceOfElement(serialNumberText, 20);
      List<WebElement> serial = driver.findElements(serialNumberText);
      eleUtil.waitForPresenceOfElement(serialNumberText, 50);
      for (WebElement item1 : serial) {
        if (item1.getText().contains(serialNumber)) {
          WebElement deleteButton =
              driver.findElement(
                  By.xpath(
                      "//td[contains(text(), '"
                          + serialNumber
                          + "')]/following-sibling::td//img[@alt='delete']"));
          javaScriptUtil.flash(deleteButton);
          deleteButton.click();
        }
      }
      eleUtil.waitForVisibilityOfElement(yesButton, 10);
      eleUtil.doClick(yesButton);
      System.out.println("delete inventory");
      eleUtil.waitForPresenceOfElements(successMessage, 20);
      eleUtil.verifyText(successMessage, "Device has been deleted successfully".trim());
      eleUtil.waitForINVisibilityOfElement(successMessage, 40);
      WebElement backButton = driver.findElement(backIcon);
      javaScriptUtil.scrollIntoView(backButton);
      Thread.sleep(3000);
      javaScriptUtil.clickElementByJS(backButton);
      Thread.sleep(3000);
    } else {
      eleUtil.waitForPresenceOfElements(successMessage, 20);
      eleUtil.verifyText(successMessage, "Device created Successfully.");
      eleUtil.waitForINVisibilityOfElement(successMessage, 40);
      List<WebElement> product = driver.findElements(productName);
      for (WebElement item : product) {
        if (item.getText().contains(deviceCategory)) {
          item.click();
          break;
        }
      }
      eleUtil.waitForPresenceOfElement(serialNumberText, 20);
      List<WebElement> serial = driver.findElements(serialNumberText);
      eleUtil.waitForPresenceOfElement(serialNumberText, 50);
      for (WebElement item1 : serial) {
        if (item1.getText().contains(serialNumber)) {
          WebElement deleteButton =
              driver.findElement(
                  By.xpath(
                      "//td[contains(text(), '"
                          + serialNumber
                          + "')]/following-sibling::td//img[@alt='delete']"));
          javaScriptUtil.flash(deleteButton);
          deleteButton.click();
        }
      }
      eleUtil.waitForVisibilityOfElement(yesButton, 10);
      eleUtil.doClick(yesButton);
      System.out.println("delete inventory");
      eleUtil.waitForPresenceOfElements(successMessage, 20);
      eleUtil.verifyText(successMessage, "Device has been deleted successfully".trim());
      eleUtil.waitForINVisibilityOfElement(successMessage, 40);
      WebElement backButton = driver.findElement(backIcon);
      javaScriptUtil.scrollIntoView(backButton);
      Thread.sleep(3000);
      javaScriptUtil.clickElementByJS(backButton);
      Thread.sleep(3000);
    }
  }
}
