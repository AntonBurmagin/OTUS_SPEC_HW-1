package main.otus.steps;

import com.google.inject.Inject;
import io.cucumber.java.ru.Тогда;
import pages.catalog.courses.CoursePage;
import scope.ScenScoped;

public class CoursePageSteps {
  @Inject
  private ScenScoped scenScoped;
  @Inject
  private CoursePage coursePage;


  @Тогда("Заголовок страницы курса должен соответствовать названию курса (.*)$")
  public void courseTitleShouldMatch(String courseName) {
    coursePage.courseTitleShouldBe(courseName);
  }

}
