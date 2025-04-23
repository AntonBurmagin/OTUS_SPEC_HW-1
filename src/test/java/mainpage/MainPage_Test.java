package mainpage;

import extensions.UIExtension;
import factory.WebDriverFactory;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(UIExtension.class)
public class MainPage_Test {

  @Inject
  public WebDriver driver;

  @Test
  void test1() throws InterruptedException {
    driver.get("https://otus.ru/");
    Thread.sleep(1000);
  }


}
