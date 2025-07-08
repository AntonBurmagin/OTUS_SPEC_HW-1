package main.otus.steps;

import com.google.inject.Inject;
import data.StartDateExtremum;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import pages.catalog.CatalogPage;
import scope.ScenScoped;

import java.io.IOException;
import java.time.LocalDate;

public class CatalogPageSteps {
  @Inject
  private ScenScoped scenScoped;
  @Inject
  private CatalogPage catalogPage;


  @Тогда("Должна быть открыта Страница каталога курсов")
  public void catalogPageShouldBeOpened(){
    catalogPage.urlShouldContainBaseUrlAndPath();
  }

  @Тогда("Фильтр соответствует выбранной категории")
  public void chosenCategoryShouldMatchFilter() {
    String categoryElementText = scenScoped.storageGet("randomCourseCategoryElementText");
    catalogPage.chosenCategoryShouldMatchCatalogFilter(categoryElementText);
  }

  @Пусть("Открыть Страница каталога курсов")
  public void openCatalogPage() {
    catalogPage.open();
  }

  @Если("Присутствует курс (.*)$")
  public void courseShouldPresent(String courseName){
    catalogPage.courseShouldPresent(courseName);
    System.out.println("Course " + courseName + " is found");
  }

  @И("Кликнуть по плитке курса (.*)$")
  public void clickCourseByName(String courseName) {
    catalogPage.clickCourse(courseName);
  }

  @Если("Получить даты самого раннего и позднего старта курса")
  public void getCoursesStartDateExtremum() {
    catalogPage.open();
    StartDateExtremum extremumDates = new StartDateExtremum();
    extremumDates.initialize(catalogPage.getCoursesCorrectStartDate());
    scenScoped.storagePut("startDatesExtremum", extremumDates);
  }

  @Тогда("Проверить совпадение информации на плитках каталога и на страницах ближайших и самых дальних курсов")
  public void checkEarliestAndLatestCourseInfo() throws IOException {
    StartDateExtremum startDateExtremum = (StartDateExtremum)scenScoped.storageGet("startDatesExtremum");
    LocalDate nearestDate = startDateExtremum.getNearestDate();
    LocalDate latestDate = startDateExtremum.getLatestDate();
    catalogPage.catalogAndCourseInfoShouldMatch(catalogPage.getCoursesByDate(nearestDate));
    if (nearestDate != latestDate)
      catalogPage.catalogAndCourseInfoShouldMatch(catalogPage.getCoursesByDate(latestDate));
  }





}
