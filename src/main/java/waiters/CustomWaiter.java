package waiters;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class CustomWaiter {
  private final WebDriverWait waiter;

  public CustomWaiter(WebDriver driver) {
    waiter = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  public boolean waitForCondition(ExpectedCondition condition) {
    try {
      waiter.until(condition);
    } catch (TimeoutException exception) {
      System.out.println(exception);
      return false;
    }
    return true;
  }

  public boolean waitForConditionNoMessage(ExpectedCondition condition) {
    try {
      waiter.until(condition);
    } catch (TimeoutException exception) {
      return false;
    }
    return true;
  }

}
