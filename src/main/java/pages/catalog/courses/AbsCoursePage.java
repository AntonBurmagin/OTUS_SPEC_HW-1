package pages.catalog.courses;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.openqa.selenium.WebDriver;
import pages.AbsBasePage;


public abstract class AbsCoursePage extends AbsBasePage {

  public AbsCoursePage(WebDriver driver) {
    super(driver);
  }

  //methods
  public abstract String getCourseTitle();

  public void courseTitleShouldBe(String courseName) {
    assertThat(getCourseTitle()).isEqualTo(courseName);
  }


}
