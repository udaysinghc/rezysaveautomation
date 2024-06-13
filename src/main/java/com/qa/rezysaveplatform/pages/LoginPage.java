package com.qa.rezysaveplatform.pages;

import com.qa.rezysaveplatform.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.qa.rezysaveplatform.constants.AppConstants;
import com.qa.rezysaveplatform.utils.ElementUtil;
import org.testng.Assert;

public class LoginPage {

  private final WebDriver driver;
  private final ElementUtil eleUtil;

  private final By logoImage = By.cssSelector("[class*='_companyLogo']");
  private final By emailTextFiled = By.id("username");
  private final By passwordTextFiled = By.id("password");
  private final By submitButton = By.cssSelector("[type='submit']");

  public final By errorTextTitle = By.cssSelector("[class*='_inputErrorMessage']");

  public final By username=By.cssSelector("[alt*='upArrow']");

  public final By logout=By.xpath("(//div[contains(@class,'_innerText')])[3]");

  // page const...
  public LoginPage(WebDriver driver) {
    this.driver = driver;
    eleUtil = new ElementUtil(this.driver);
  }

  public LoginPage navigate() {
    eleUtil.waitForVisibilityOfElement(logoImage, AppConstants.MEDIUM_DEFAUTT_WAIT).isDisplayed();
    return new LoginPage(driver);
  }

  public void loginToApplication(String email, String password){
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

  public void invalidUser(String email, String password, String expectedText) {

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
}
