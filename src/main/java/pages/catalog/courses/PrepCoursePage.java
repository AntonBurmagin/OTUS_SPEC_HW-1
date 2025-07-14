package pages.catalog.courses;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import scope.ScenScoped;

public class PrepCoursePage extends AbsCoursePage {

  @Inject
  public PrepCoursePage(ScenScoped scenScoped){
    super(scenScoped.getDriver());
  }

  private final By titleSelector = new By.ByCssSelector("main div div div div h3");
  @Override
  public String getCourseTitle() {
    waiter.waitForCondition(ExpectedConditions.visibilityOfElementLocated(titleSelector));
    return driver.findElement(titleSelector).getText();
  }
}
