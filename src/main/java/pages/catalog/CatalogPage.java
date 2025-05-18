package pages.catalog;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import annotations.Path;
import data.MonthData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.AbsBasePage;
import pages.catalog.courses.AbsCoursePage;
import pages.catalog.courses.CoursePage;


import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


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
  private final By coursesDateListSelector = new By.ByCssSelector("section a > div:last-child");


  //methods
  public void clickMoreCoursesButton(){
    if(waiter.waitForCondition(ExpectedConditions.visibilityOfAllElementsLocatedBy(moreCoursesButtonLocator)))
      driver.findElement(moreCoursesButtonLocator).click();
  }

  public void acceptCookiePolicy(){
    if(waiter.waitForCondition(ExpectedConditions.visibilityOfElementLocated(cookieNotificationButtonLocator)))
      driver.findElement(cookieNotificationButtonLocator).click();
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
            .getText().equals(courseName));

    assertTrue(courseIsFound);
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

  public List<String> getCoursesCorrectStartDate() {
    scrollCatalogPage();
    String filter = Arrays.stream(MonthData.values()).map(x->x.getName())
                          .collect(Collectors.joining());

    ArrayList<String> correctDates = new ArrayList<>();
    driver.findElements(coursesDateListSelector).stream().filter(dateElem -> {
        String month = dateElem.getText().split(" ")[1];
        return filter.contains(month);
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



}
