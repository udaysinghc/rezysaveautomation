package com.qa.rezysaveplatform.tests;

import com.qa.rezysaveplatform.base.BaseTest;
import com.qa.rezysaveplatform.constants.AppConstants;
import com.qa.rezysaveplatform.utils.ExcelUtil;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class InventoryPageTest extends BaseTest {

  @BeforeClass
  @DataProvider
  public Object[][] getUserExcelData() {
    return ExcelUtil.getTestData(AppConstants.ADMIN_USER_SHEET_NAME);
  }

  @Test(dataProvider = "getUserExcelData", priority = 1)
  public void accSetUp(String userEmail, String password) throws InterruptedException {
    dashboardPage = loginPage.loginApp(userEmail, password);
    inventoryPage = loginPage.searchOption();
  }

  @Test(priority = 2)
  public void InventoryLinkTest() throws InterruptedException {
    inventoryPage.clickOnInventoryOption("Inventory");
  }

  @DataProvider
  public Object[][] inventoryExcelData() {
    return ExcelUtil.getTestData(AppConstants.INVENTORY_ADD_NEW_DEVICES);
  }

  @Test(dataProvider = "inventoryExcelData", priority = 3)
  public void InventoryTest(String deviceCategory, String deviceName, String serialNumber)
      throws InterruptedException {
    inventoryPage.addNewInventory(deviceCategory, deviceName, serialNumber);
  }

 
}
