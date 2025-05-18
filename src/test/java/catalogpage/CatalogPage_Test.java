package catalogpage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import data.StartDateExtremum;
import extensions.UIExtension;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebElement;
import pages.catalog.CatalogPage;
import pages.catalog.courses.AbsCoursePage;

import java.io.IOException;
import java.util.List;


@ExtendWith(UIExtension.class)
public class CatalogPage_Test {

  @Inject
  public CatalogPage page;


  @Test
  public void courseTitleTest() {
    String courseName = "QA Automation Engineer";
    page.open();

    page.courseShouldPresent(courseName);
    AbsCoursePage coursePage = page.clickCourse(courseName);
    coursePage.courseTitleShouldBe(courseName);
  }

  @Test
  public void nearestAndLatestCoursesPageInfoTest() throws IOException {
    page.open();

    StartDateExtremum extremumDates = new StartDateExtremum(page.getCoursesCorrectStartDate());

    page.catalogAndCourseInfoShouldMatch(page.getCoursesByDate(extremumDates.getLatestDate()));
    page.catalogAndCourseInfoShouldMatch(page.getCoursesByDate(extremumDates.getNearestDate()));
  }

}
