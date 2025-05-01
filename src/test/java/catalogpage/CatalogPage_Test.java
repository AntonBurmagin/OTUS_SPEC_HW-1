package catalogpage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import extensions.UIExtension;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.catalog.CatalogPage;
import pages.catalog.courses.AbsCoursePage;
import pages.catalog.courses.CoursePage;
import java.time.Duration;




@ExtendWith(UIExtension.class)
public class CatalogPage_Test {

  @Inject
  public CatalogPage page;


  @Test
  public void findCourseTest() throws InterruptedException {
    String courseName = "QA Automation Engineer";
    page.open();

    assertTrue(page.findCourse(page.courseByFromName(courseName)));
    AbsCoursePage p = page.clickCourse(page.courseByFromName(courseName));
    assertThat(p.getCourseTitle()).isEqualTo(courseName);
    System.out.println(p.getCourseTitle());
  }

}
