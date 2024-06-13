package com.qa.rezysaveplatform.exception;

import org.testng.annotations.Test;

public class FrameworkException extends RuntimeException {

  public FrameworkException(String mesg) {
    super(mesg);
  }
}
