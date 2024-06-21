package com.qa.rezysaveplatform.tests;

import com.qa.rezysaveplatform.base.BaseTest;
import com.qa.rezysaveplatform.constants.AppConstants;
import com.qa.rezysaveplatform.utils.ExcelUtil;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {
  @BeforeClass
  public void accSetUp() {
    loginPage = loginPage.navigate();
  }

  @DataProvider
  public Object[][] getUserExcelData() {
    return ExcelUtil.getTestData(AppConstants.ADMIN_USER_SHEET_NAME);
  }

  @Test(dataProvider = "getUserExcelData",priority = 1)
  public void loginTest(String userEmail, String password) throws InterruptedException {
    loginPage.doLogin(userEmail, password);
  }

}
