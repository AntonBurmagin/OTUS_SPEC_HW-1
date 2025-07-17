package scope;

import factory.WebDriverFactory;
import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;
import java.util.HashMap;
import java.util.Map;


@ScenarioScoped
public class ScenScoped {
  private final WebDriverFactory factory = new WebDriverFactory();
  private WebDriver driver = factory.create("--start-fullscreen");
  private Map<String, Object> storage = new HashMap<String, Object>();


  public void changeBrowser(String chosenBrowser) {
    if (driver != null)
      driver.quit();
    factory.changeBrowser(chosenBrowser);
    driver = factory.create("--start-fullscreen");
  }

  public WebDriver getDriver() {
    return this.driver;
  }


  public <T> void storagePut(String name, T t) {
    storage.put(name, t);
  }

  public <T> T storageGet(String name) {
    return (T) storage.get(name);
  }
}
