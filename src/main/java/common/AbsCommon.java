package common;

import org.openqa.selenium.WebDriver;
import waiters.CustomWaiter;

public abstract class AbsCommon {
  protected WebDriver driver;
  protected CustomWaiter waiter;

  protected AbsCommon(WebDriver driver){
    this.driver = driver;
    waiter = new CustomWaiter(driver);
  }
}
