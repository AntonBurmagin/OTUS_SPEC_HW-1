package main.otus.steps;

import com.google.inject.Inject;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import pages.catalog.CatalogPage;
import scope.ScenScoped;

public class CatalogPageSteps {
  @Inject
  private ScenScoped scenScoped;
  @Inject
  private CatalogPage catalogPage;



  @Тогда("Фильтр соответствует выбранной категории")
  public void chosenCategoryShouldMatchFilter() {
    String categoryElementText = scenScoped.storageGet("randomCourseCategoryElementText");
    catalogPage.chosenCategoryShouldMatchCatalogFilter(categoryElementText);
  }

}
