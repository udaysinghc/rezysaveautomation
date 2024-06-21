package com.qa.rezysaveplatform.tests;

import com.qa.rezysaveplatform.base.BaseTest;
import com.qa.rezysaveplatform.constants.AppConstants;
import com.qa.rezysaveplatform.utils.ExcelUtil;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class InvalidUserLoginPageTest extends BaseTest {
    @BeforeClass
    public void accSetUp() {
        loginPage = loginPage.navigate();
    }
    @DataProvider
    public Object[][] invalidUserData() {
        return ExcelUtil.getTestData(AppConstants.INVALID_USER_SHEET_NAME);
    }

    @Test(dataProvider = "invalidUserData", priority = 1)
    public void LoginTestInvalidUser(String userEmail, String password, String expectedText) {
        loginPage.doLoginInvalidUser(userEmail, password, expectedText);
    }

}
