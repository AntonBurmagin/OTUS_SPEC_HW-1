package catalogpage;

import data.StartDateExtremum;
import extensions.UIExtension;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.catalog.CatalogPage;
import pages.catalog.courses.AbsCoursePage;
import java.io.IOException;


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
