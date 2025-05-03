package pages.catalog;

import annotations.Path;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.AbsBasePage;
import pages.catalog.courses.AbsCoursePage;
import pages.catalog.courses.CoursePage;
import java.util.List;
import java.util.Optional;


@Path("/catalog/courses")
public class CatalogPage extends AbsBasePage {
  public CatalogPage(WebDriver driver){
    super(driver);
  }

  //selectors
  private final By moreCoursesButtonLocator = new By.ByXPath("//button[contains(text(),\"Показать еще\")]");
  private final By cookieNotificationButtonLocator = new By.ByXPath("//span[text()=\"Посещая наш сайт, вы принимаете\"]//..//button");
  private final By coursesListSelector = new By.ByCssSelector("main section a");
  private final By stickyBannerSelector = new By.ByCssSelector("[class=\"sticky-banner__close js-sticky-banner-close\"]");


  //methods
  public void clickMoreCoursesButton(){
    if(waiter.waitForCondition(ExpectedConditions.visibilityOfAllElementsLocatedBy(moreCoursesButtonLocator)))
      driver.findElement(moreCoursesButtonLocator).click();
  }

  public void acceptCookiePolicy(){
    waiter.waitForCondition(ExpectedConditions.visibilityOfElementLocated(cookieNotificationButtonLocator));
    driver.findElement(cookieNotificationButtonLocator).click();
  }

  public void closeStickyBanner(){
    waiter.waitForCondition(ExpectedConditions.visibilityOfElementLocated(stickyBannerSelector));
    driver.findElement(stickyBannerSelector).click();
  }

  public boolean findCourse(String courseName){
    acceptCookiePolicy();
    closeStickyBanner();

    while(waiter.waitForCondition(ExpectedConditions.visibilityOfAllElementsLocatedBy(moreCoursesButtonLocator))) {
      clickMoreCoursesButton();
    }

    System.out.println(getDisplayedCourses().stream()
        .anyMatch(tile -> tile.findElement(By.cssSelector("h6"))
            .getText().equals(courseName)));

    return getDisplayedCourses().stream()
        .anyMatch(tile -> tile.findElement(By.cssSelector("h6"))
            .getText().equals(courseName));
  }

  public List<WebElement> getDisplayedCourses(){
    return driver.findElements(coursesListSelector);
  }

  public AbsCoursePage clickCourse(String courseName){
    driver.findElement(courseByFromName(courseName)).click();
    return new CoursePage(driver);
  }

  public By courseByFromName(String courseName){
    return new By.ByXPath(String.format("//section//div/text()[. =\"%s\"]//ancestor::a", courseName));
  }



}
