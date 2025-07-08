package main.otus.steps;

import io.cucumber.java.ru.Пусть;

public class DriverSteps {

  @Пусть("Выбор браузера (.*)$")
  public void setBrowser(String chosenBrowser) {
    System.setProperty("browser", chosenBrowser);
  }
}
