package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import scope.ScenScoped;
import waiters.CustomWaiter;
import java.time.Duration;


public abstract class AbsCommon {
  protected WebDriver driver;
  protected CustomWaiter waiter;
  protected Actions actions;

  protected AbsCommon(WebDriver driver){
    this.driver = driver;
    waiter = new CustomWaiter(driver);
    actions = new Actions(driver, Duration.ofSeconds(1));
  }
}
