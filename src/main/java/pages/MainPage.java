package pages;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import annotations.Path;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.catalog.CatalogPage;
import java.util.List;
import java.util.Random;



@Path("/")
public class MainPage extends AbsBasePage {

  //locators
  private final By headerLearningViewBoxSelector = By.cssSelector("[title=\"Обучение\"]");
  private final By learningViewBoxCourseCategoriesLocator = By.xpath("//p[text()=\"Все курсы\"]/../div/a");
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
  }

  public List<WebElement> getHeaderCourseCategories(){
    waiter.waitForCondition(ExpectedConditions.visibilityOfAllElementsLocatedBy(learningViewBoxCourseCategoriesLocator));
    return driver.findElements(learningViewBoxCourseCategoriesLocator);
  }

  public WebElement getRandomHeaderCourseCategory() {
    List<WebElement> categories = getHeaderCourseCategories();
    if (categories.isEmpty())
      throw new RuntimeException("Header categories list is empty!");
    int randIndex = new Random().nextInt(categories.size());
    return categories.get(randIndex);
  }

  public void moveToCategoryShouldMatchCatalogFilter(WebElement moveToCategory){
    String categoryName = moveToCategory.getText().split(" \\(")[0];
    actions.moveToElement(moveToCategory).build().perform();
    moveToCategory.click();
    CatalogPage moveToPage = new CatalogPage(driver);
    String filter = String.join("", moveToPage.getActiveFilters());
    assertThat(categoryName).isEqualTo(filter);
  }


}
