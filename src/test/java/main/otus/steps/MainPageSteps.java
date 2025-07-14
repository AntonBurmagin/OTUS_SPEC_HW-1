package main.otus.steps;

import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Пусть;
import org.openqa.selenium.WebElement;
import pages.MainPage;
import scope.ScenScoped;


public class MainPageSteps {

  @Inject
  private ScenScoped scenScoped;
  @Inject
  private MainPage mainPage;


  @Пусть("Открыть Главная страница")
  public void openMainPage() {
    mainPage.open();
  }

  @Если("Навестись на меню Обучение")
  public void hoverOverLearning() {
    mainPage.hoverOverLearning();
  }

  @И("Выбрать случайную категорию курсов")
  public void clickRandomHeaderCourseCategory() {
    WebElement courseCategoryElement = mainPage.getRandomHeaderCourseCategory();
    scenScoped.storagePut("randomCourseCategoryElementText", courseCategoryElement.getText());
    mainPage.clickCourseCategory(courseCategoryElement);
  }

  @И("Выбрать категорию курсов (.*)$")
  public void clickCourseCategory(String category) {
    mainPage.clickCourseCategory(mainPage.getCourseCategoryByName(category));
  }




}
