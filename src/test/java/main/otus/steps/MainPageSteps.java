package main.otus.steps;

import com.google.inject.Inject;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import pages.MainPage;
import scope.ScenScoped;


public class MainPageSteps {

  @Inject
  private ScenScoped scenScoped;
  @Inject
  private MainPage mainPage;


  @Пусть("Открыта Главная страница")
  public void openMainPage() {
    mainPage.open();
    System.out.println("Properties:");
    System.out.println(System.getProperty("base.url"));
    System.out.println(System.getProperty("browser"));
  }

  @Тогда("Откроется страница Каталог курсов")
  public void openCatalogPage() {
    System.out.println("Catalog page open step");
  }

//  @Если("Навестись на меню Обучение")
//  public void hoverOverLearning() {
//    mainPage.hoverOverLearning();
//  }

//  @И("Выбрать случайную категорию курсов")
//  public void {
//
//  }
}
