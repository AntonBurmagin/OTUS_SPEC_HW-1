package pages;

import annotations.Path;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

@Path("/")
public class MainPage extends AbsBasePage {
  Actions actions = new Actions(driver, Duration.ofSeconds(1));


  //locators
  private final By headerLearningViewBoxSelector = By.cssSelector("[title=\"Обучение\"]");
  private final By learningViewBoxCoursesLiseLocator = By.xpath("//p[text()=\"Все курсы\"]/../div/a");
  private final By cookieNotificationButtonLocator = new By.ByXPath("//span[text()=\"Посещая наш сайт, вы принимаете\"]//..//button");



  //methods
  public MainPage(WebDriver driver){
    super(driver);
  }

  public void acceptCookiePolicy(){
    if(waiter.waitForCondition(ExpectedConditions.visibilityOfElementLocated(cookieNotificationButtonLocator)))
      driver.findElement(cookieNotificationButtonLocator).click();
  }

  public void hoverOverLearning(){
    acceptCookiePolicy();

    WebElement learningViewBox = driver.findElement(headerLearningViewBoxSelector);
    actions.moveToElement(learningViewBox).build().perform();

    waiter.waitForCondition(ExpectedConditions.visibilityOfAllElementsLocatedBy(learningViewBoxCoursesLiseLocator));
    driver.findElements(learningViewBoxCoursesLiseLocator).stream()
        .forEach(el -> System.out.println(el.getDomProperty("href")));
  }


}
