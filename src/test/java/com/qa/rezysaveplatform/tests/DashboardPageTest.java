package com.qa.rezysaveplatform.tests;

import com.qa.rezysaveplatform.base.BaseTest;
import com.qa.rezysaveplatform.constants.AppConstants;
import com.qa.rezysaveplatform.utils.ExcelUtil;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DashboardPageTest extends BaseTest {

  @BeforeClass
  @DataProvider
  public Object[][] getUserExcelData() {
    return ExcelUtil.getTestData(AppConstants.ADMIN_USER_SHEET_NAME);
  }

  @Test(dataProvider = "getUserExcelData", priority = 1)
  public void accSetUp(String userEmail, String password) throws InterruptedException {
    dashboardPage = loginPage.loginApp(userEmail, password);
  }

  @Test(priority = 2)
  public void raiseNewConnectionTest() throws InterruptedException {
    dashboardPage.raiseNewConnection(
        "Smoke Detector / Fire Alarm", "Manu Prakash", "Smoke Detector");
  }
}
