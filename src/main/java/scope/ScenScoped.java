package scope;

import factory.WebDriverFactory;
import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;


@ScenarioScoped
public class ScenScoped {
  private WebDriver driver = new WebDriverFactory().create("--start-fullscreen");
  private Map<String, Object> storage = new HashMap<String, Object>();


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
