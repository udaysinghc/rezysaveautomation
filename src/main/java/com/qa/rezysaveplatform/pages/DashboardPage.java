package com.qa.rezysaveplatform.pages;

import com.qa.rezysaveplatform.constants.AppConstants;
import com.qa.rezysaveplatform.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DashboardPage {
  private final WebDriver driver;
  private final ElementUtil eleUtil;

  private final By dashboardIcon = By.cssSelector("[alt='header']");
  private final By connectionOption = By.cssSelector("[class*='connection']+span");
  private final By propertyAddress =
      By.cssSelector("[class*='propertiesOuter'] [class*='property']");

  private final By allAssetListExpand =
      By.cssSelector("[class*='propertiesOuter'] div[class*='MuiBox-root'] a");

  private final By detectorType = By.xpath("//span[contains(@class, '_headerText')]");
  private final By raise = By.cssSelector("[class*='MuiButton-endIcon']");
  private final By typeOfDevices = By.cssSelector("[role*='combobox']");

  private final By installerType = By.cssSelector("li[class*='MuiButtonBase'] div span");
  private final By startDateNumber = By.cssSelector("input[placeholder='Start Date']+div img");
  private final By endDateNumber = By.cssSelector("input[placeholder='End Date']+div img");

  private final By addBuildingButton =
      By.cssSelector("[class*='MuiDialogContent-root'] [class*='MuiButton-root']");
  private final By building = By.cssSelector("[name='building']");

  public DashboardPage(WebDriver driver) {
    this.driver = driver;
    eleUtil = new ElementUtil(this.driver);
  }

  public void raiseNewConnection(String detector, String nameInstaller, String device)
      throws InterruptedException {
    eleUtil.waitForVisibilityOfElement(dashboardIcon, AppConstants.MEDIUM_DEFAUTT_WAIT);
    eleUtil.waitForVisibilityOfElement(connectionOption, 10);
    eleUtil.doClick(connectionOption);
    eleUtil.waitForPresenceOfElements(propertyAddress, 50);
    eleUtil.doIndexElementClick(allAssetListExpand, 1);
    eleUtil.waitForPresenceOfElements(detectorType, 50);

    List<WebElement> eleList = driver.findElements(detectorType);
    for (WebElement e : eleList) {
      if (e.getText().contains(detector)) {
        eleUtil.waitForPresenceOfElements(raise, 50);
        List<WebElement> eleList1 = driver.findElements(raise);
        for (WebElement e1 : eleList1) {
          e1.click();
          break;
        }
      }
    }
    raiseInstallation(nameInstaller, device);
  }

  public void raiseInstallation(String installerName, String deviceType)
      throws InterruptedException {
    eleUtil.waitForPresenceOfElements(typeOfDevices, 20);
    eleUtil.doIndexElementClick(typeOfDevices, 0);
    eleUtil.waitForPresenceOfElements(installerType, 50);
    eleUtil.clickOnElement(installerType, installerName);
    eleUtil.doIndexElementClick(typeOfDevices, 1);
    eleUtil.waitForPresenceOfElements(installerType, 50);
    eleUtil.clickOnElement(installerType, deviceType);
    date();
    eleUtil.doClick(addBuildingButton);
    eleUtil.waitForPresenceOfElements(building, 20);
    eleUtil.doIndexElementClick(building, 1);
    Thread.sleep(5000);
  }

  public void date() throws InterruptedException {
    LocalDate currentDate = LocalDate.now();
    LocalDate futureDate = currentDate.plusDays(4);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    String startDate = currentDate.format(formatter);
    String endDate = futureDate.format(formatter);
    String[] startDates = startDate.split("-");
    String[] endDates = endDate.split("-");

    eleUtil.doClick(startDateNumber);
    Thread.sleep(1000);
    List<WebElement> startDateElements =
        driver.findElements(By.cssSelector("[class*='MuiPickersDay']+button"));
    selectDate(startDates[0], startDateElements);

    // Select end date
    eleUtil.doClick(endDateNumber);
    Thread.sleep(1000);
    List<WebElement> endDateElements =
        driver.findElements(By.cssSelector("[class*='MuiPickersDay']+button"));
    selectDate(endDates[0], endDateElements);
  }

  private static void selectDate(String day, List<WebElement> dateElements)
      throws InterruptedException {
    for (WebElement element : dateElements) {
      System.out.println(element.getText());
      if (element.getText().equals(day)) {
        element.click();
        Thread.sleep(5000);
        break;
      }
    }
  }
}
