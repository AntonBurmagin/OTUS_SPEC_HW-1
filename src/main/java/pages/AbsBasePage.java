package pages;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import annotations.Path;
import common.AbsCommon;
import exceptions.PathNotDeclaredException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


public abstract class AbsBasePage extends AbsCommon {
  private String baseUrl = System.getProperty("base.url");


  public AbsBasePage(WebDriver driver) {
    super(driver);
  }

  public String getAddPath(){
    Class cl = this.getClass();
    if (cl.isAnnotationPresent(Path.class)) {
      Path path = (Path) cl.getDeclaredAnnotation(Path.class);
      return path.value();
    }
    throw new PathNotDeclaredException(String.format("Path annotation is not declared for page %s", cl.getCanonicalName()));
  }

  public void open(){
    baseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.lastIndexOf("/")) : baseUrl;
    driver.get(baseUrl + getAddPath());
  }

  public void urlShouldContainBaseUrlAndPath(){
    String expectedUrl = baseUrl + getAddPath();
    waiter.waitForCondition(ExpectedConditions.urlContains(expectedUrl));
    String currentUrl = driver.getCurrentUrl();
    assertThat(currentUrl).containsPattern(expectedUrl);
  }

}
