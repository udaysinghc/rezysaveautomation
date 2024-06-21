package com.qa.rezysaveplatform.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {

  public static final String LOGIN_PAGE_TITLE = "Account Login";

  public static final String ACCOUNTS_PAGE_TITLE = "My Account";

  public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";
  public static final String ACC_PAGE_URL_FRACTION = "route=account/account";

  public static final int SHORT_DEFAUTT_WAIT = 5;
  public static final int MEDIUM_DEFAUTT_WAIT = 30;
  public static final int LONG_DEFAUTT_WAIT = 20;

  public static final int POLLING_DEFAUTT_WAIT = 2;

  public static final int ACCOUNTS_PAGE_HEADERS_COUNT = 4;

  public static final List<String> ACCOUNTS_PAGE_HEADERS_LIST =
      Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter");



  public static final String INVENTORY_ADD_NEW_DEVICES = "Inventory";
  public static final String ADMIN_USER_SHEET_NAME = "admin user";
  public static final String INVENTORY1_ADD_NEW_DEVICES = "inventroy1";
  // error data
  public static final String INVALID_USER_SHEET_NAME = "invalid user";

}
