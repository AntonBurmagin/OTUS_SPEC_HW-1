package main.otus.steps;

import com.google.inject.Inject;
import io.cucumber.java.ru.Пусть;
import scope.ScenScoped;

public class DriverSteps {
  @Inject
  private ScenScoped scenScoped;

  @Пусть("Я открываю браузер (chrome|firefox)$")
  public void setBrowser(String chosenBrowser) {
    scenScoped.changeBrowser(chosenBrowser);
  }
}
