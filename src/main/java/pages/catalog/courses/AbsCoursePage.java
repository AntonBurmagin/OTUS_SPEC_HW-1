package pages.catalog.courses;

import org.openqa.selenium.WebDriver;
import pages.AbsBasePage;


public abstract class AbsCoursePage extends AbsBasePage {

  public AbsCoursePage(WebDriver driver) {
    super(driver);
  }

  //methods
  public abstract String getCourseTitle();



}
