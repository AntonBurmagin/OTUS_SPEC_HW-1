package pages.catalog;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import annotations.Path;
import com.google.inject.Inject;
import components.CatalogFilterSection;
import data.MonthData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.AbsBasePage;
import pages.catalog.courses.AbsCoursePage;
import pages.catalog.courses.CoursePage;
import scope.ScenScoped;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Path("/catalog/courses")
public class CatalogPage extends AbsBasePage {

  @Inject
  public CatalogPage(ScenScoped scenScoped){
    super(scenScoped.getDriver());
  }


  //selectors
  private final By moreCoursesButtonLocator = new By.ByXPath("//button[contains(text(),\"Показать еще\")]");
  private final By cookieNotificationButtonLocator = new By.ByXPath("//span[text()=\"Посещая наш сайт, вы принимаете\"]//..//button");
  private final By coursesListSelector = new By.ByCssSelector("main section a");
  private final By stickyBannerSelector = new By.ByCssSelector("[class=\"sticky-banner__close js-sticky-banner-close\"]");
  private final By coursesDateListSelector = new By.ByCssSelector("section a > div:last-child");


  //methods
  public void clickMoreCoursesButton(){
    if(waiter.waitForCondition(ExpectedConditions.visibilityOfAllElementsLocatedBy(moreCoursesButtonLocator)))
      driver.findElement(moreCoursesButtonLocator).click();
  }

  public void acceptCookiePolicy(){
    if(waiter.waitForCondition(ExpectedConditions.visibilityOfElementLocated(cookieNotificationButtonLocator)))
      actions.moveToElement(driver.findElement(cookieNotificationButtonLocator)).click().build().perform();
  }

  public void closeStickyBanner(){
    if (waiter.waitForCondition(ExpectedConditions.visibilityOfElementLocated(stickyBannerSelector)))
      driver.findElement(stickyBannerSelector).click();
  }

  public void scrollCatalogPage(){
    acceptCookiePolicy();
    closeStickyBanner();
    while(waiter.waitForCondition(ExpectedConditions.visibilityOfAllElementsLocatedBy(moreCoursesButtonLocator)))
      clickMoreCoursesButton();
  }

  public void courseShouldPresent(String courseName){
    scrollCatalogPage();
    boolean courseIsFound = getDisplayedCourses().stream()
        .anyMatch(tile -> tile.findElement(By.cssSelector("h6"))
            .getText().contentEquals(courseName));

    assertTrue(courseIsFound);
  }

  public List<WebElement> getDisplayedCourses(){
    return driver.findElements(coursesListSelector);
  }

  public void clickCourse(String courseName){
    if(waiter.waitForCondition(ExpectedConditions.visibilityOfAllElementsLocatedBy(courseByFromName(courseName))))
      actions.moveToElement(driver.findElement(courseByFromName(courseName))).click().build().perform();
//    return new CoursePage((ScenScoped) driver);
  }

  public By courseByFromName(String courseName){
    return new By.ByXPath(String.format("//section//div/text()[. =\"%s\"]//ancestor::a", courseName));
  }

  public List<String> getCoursesCorrectStartDate() {
    scrollCatalogPage();
    String monthDataFilter = Arrays.stream(MonthData.values()).map(x->x.getName())
                          .collect(Collectors.joining());

    ArrayList<String> correctDates = new ArrayList<>();
    driver.findElements(coursesDateListSelector).stream().filter(dateElem -> {
      String month = dateElem.getText().split(" ")[1];
      return monthDataFilter.contains(month);
    }).forEach(dateElem -> {
      correctDates.add(dateElem.getText());
    });
    return correctDates;
  }

  public List<WebElement> getCoursesByDate(LocalDate date) {
    scrollCatalogPage();
    String dateFilter = String.format("%s %s %s", date.getDayOfMonth(),
                                      MonthData.customValueOf(date.getMonthValue()).getName(),
                                      date.getYear());

    return getDisplayedCourses().stream().filter(x -> x.getText().contains(dateFilter)).toList();
  }

  public void catalogAndCourseInfoShouldMatch(List<WebElement> courses) throws IOException {
    Document coursePage;
    for (WebElement course : courses) {
      String catalogName = course.findElement(By.cssSelector("h6")).getText();
      String catalogDate = course.findElement(By.xpath("div[2]")).getText();

      String href = System.getProperty("base.url") + course.getDomAttribute("href");
      coursePage = Jsoup.connect(href).get();
      String coursePageName = coursePage.selectFirst("section h1").text();
      String coursePageDate = coursePage.select("section > div > div > div > p").get(1).text();

      assertThat(catalogName).isEqualTo(coursePageName);
      assertTrue(catalogDate.contains(coursePageDate));
    }
  }

  public void getMinPriceCourses(List<WebElement> courses) throws IOException {
    Document coursePage;
    Integer minPrice = null;
    for (WebElement course : courses) {
      String href = System.getProperty("base.url") + course.getDomAttribute("href");
      coursePage = Jsoup.connect(href).get();
      String strPrice = coursePage.selectXpath("*//div[contains(text(),'Стоимость')]/../div[2]")
                                  .text().replaceAll("[^0-9]", "");
      if (strPrice.length() > 0) {
        Integer price = Integer.parseInt(strPrice);
        if (minPrice == null || price < minPrice)
          minPrice = price;
      }
    }
    System.out.println("Min price is " + minPrice);
  }

  public String getPrepCoursePrice(WebElement prepCourseTile) {
    Document coursePage;
    String href = System.getProperty("base.url") + prepCourseTile.getDomAttribute("href");
    try {
      coursePage = Jsoup.connect(href).get();
      String strPrice = coursePage.selectXpath("*//div[contains(text(),'Стоимость')]/../div[2]")
          .text().replaceAll("[^0-9]", "");
      return strPrice;
    } catch (IOException e) {
      throw new RuntimeException("Couldn't get jsoup connect for: " + href);
    }
  }

  public void chosenCategoryShouldMatchCatalogFilter(String chosenCategoryText){
    String categoryClearName = chosenCategoryText.split(" \\(")[0];
    CatalogFilterSection pageFilter = new CatalogFilterSection(driver);
    String actualFilter = String.join("", pageFilter.getActiveInputNotDefaultFilterValues());
    assertThat(categoryClearName).isEqualTo(actualFilter);
  }



}
