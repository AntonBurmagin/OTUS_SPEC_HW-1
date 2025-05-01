package pages.catalog;

import annotations.Path;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.AbsBasePage;
import pages.catalog.courses.AbsCoursePage;
import pages.catalog.courses.CoursePage;

@Path("/catalog/courses")
public class CatalogPage extends AbsBasePage {
  public CatalogPage(WebDriver driver){
    super(driver);
  }

  //selectors
  private final By moreCoursesButtonLocator = new By.ByXPath("//button[contains(text(),\"Показать еще\")]");
  private final By cookieNotificationButtonBy = new By.ByXPath("//span[text()=\"Посещая наш сайт, вы принимаете\"]//..//button");

  //methods
  public void clickMoreCoursesButton(){
    if(waiter.waitForCondition(ExpectedConditions.elementToBeClickable(moreCoursesButtonLocator)))
      driver.findElement(moreCoursesButtonLocator).click();
  }

  public void acceptCookiePolicy(){
    waiter.waitForCondition(ExpectedConditions.visibilityOfElementLocated(cookieNotificationButtonBy));
    driver.findElement(cookieNotificationButtonBy).click();
  }

  public boolean findCourse(By courseBy){
    acceptCookiePolicy();
    
    while(!waiter.waitForConditionNoMessage(ExpectedConditions.visibilityOfElementLocated(courseBy))) {
      clickMoreCoursesButton();
    }
    return true;
  }

  public AbsCoursePage clickCourse(By courseBy){
    driver.findElement(courseBy).click();
    return new CoursePage(driver);
  }

  public By courseByFromName(String courseName){
    return new By.ByXPath(String.format("//section//div/text()[. =\"%s\"]//ancestor::a", courseName));
  }



}
