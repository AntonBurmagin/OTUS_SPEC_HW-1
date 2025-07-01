package main.otus.hooks;

import com.google.inject.Inject;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import scope.ScenScoped;

public class Hooks {
  @Inject
  private ScenScoped scenScoped;

  @After
  public void after() {
    WebDriver driver = scenScoped.getDriver();
    if (driver != null)
      driver.quit();
  }

//  @Before
//  public void before() {
//    WebDriver driver = scenScoped.getDriver();
//  }

}
