package com.qa.rezysaveplatform.pages;


import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import com.qa.rezysaveplatform.constants.AppConstants;
import com.qa.rezysaveplatform.utils.ElementUtil;
import org.testng.Assert;

public class LoginPage {

  private final WebDriver driver;
  private final ElementUtil eleUtil;
  private final By searchIcon = By.cssSelector("[alt*='search']");
  private final By logoImage = By.cssSelector("[class*='_companyLogo']");
  private final By emailTextFiled = By.id("username");
  private final By passwordTextFiled = By.id("password");
  private final By submitButton = By.cssSelector("[type='submit']");

  public final By errorTextTitle = By.cssSelector("[class*='_inputErrorMessage']");

  // page const...
  public LoginPage(WebDriver driver) {
    this.driver = driver;
    eleUtil = new ElementUtil(this.driver);
  }

  public LoginPage navigate() {
    eleUtil.waitForVisibilityOfElement(logoImage, AppConstants.MEDIUM_DEFAUTT_WAIT).isDisplayed();
    return new LoginPage(driver);
  }

  public void doLogin(String email, String password) {
    eleUtil.waitForVisibilityOfElement(emailTextFiled, 10);
    eleUtil.doSendKeys(this.emailTextFiled, email);
    eleUtil.doSendKeys(this.passwordTextFiled, password);
    eleUtil.doClick(submitButton);
  }

  public void emptyUser(String email, String password, String expectedText) {

    eleUtil.waitForVisibilityOfElement(emailTextFiled, 30);
    eleUtil.clearInput(emailTextFiled);
    eleUtil.doSendKeys(this.emailTextFiled, email);
    eleUtil.clearInput(passwordTextFiled);
    eleUtil.doSendKeys(this.passwordTextFiled, password);
    eleUtil.doClick(submitButton);
    eleUtil.waitForVisibilityOfElement(errorTextTitle, 10);
    System.out.println("title" + eleUtil.doElementGetText(errorTextTitle));
    Assert.assertEquals(expectedText, eleUtil.doElementGetText(errorTextTitle));
  }

  public void doLoginInvalidUser(String email, String password, String expectedText) {

    eleUtil.waitForVisibilityOfElement(emailTextFiled, 30);
    eleUtil.clearInput(emailTextFiled);
    eleUtil.doSendKeys(this.emailTextFiled, email);
    eleUtil.clearInput(passwordTextFiled);
    eleUtil.doSendKeys(this.passwordTextFiled, password);
    eleUtil.doClick(submitButton);
    eleUtil.waitForVisibilityOfElement(errorTextTitle, 10);
    System.out.println("title" + eleUtil.doElementGetText(errorTextTitle));
    Assert.assertEquals(expectedText, eleUtil.doElementGetText(errorTextTitle));
  }

  public DashboardPage loginApp(String email, String password) throws InterruptedException {
    eleUtil.waitForVisibilityOfElement(emailTextFiled, 10);
    eleUtil.doSendKeys(this.emailTextFiled, email);
    eleUtil.doSendKeys(this.passwordTextFiled, password);
    eleUtil.doClick(submitButton);
    Thread.sleep(5000);
    return new DashboardPage(driver);
  }

  public InventoryPage searchOption() throws InterruptedException {
    eleUtil.waitForPresenceOfElement(searchIcon, 20);
    return new InventoryPage(driver);
  }
}
