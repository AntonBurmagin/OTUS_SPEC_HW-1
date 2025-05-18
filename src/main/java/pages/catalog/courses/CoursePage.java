package pages.catalog.courses;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class CoursePage extends AbsCoursePage{

  public CoursePage(WebDriver driver){
    super(driver);
  }

  private final By titleSelector = new By.ByCssSelector("main section h1");

  @Override
  public String getCourseTitle() {
    waiter.waitForCondition(ExpectedConditions.visibilityOfElementLocated(titleSelector));
    return driver.findElement(titleSelector).getText();
  }

}
