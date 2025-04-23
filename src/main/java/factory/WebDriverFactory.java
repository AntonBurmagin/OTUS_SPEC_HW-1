package factory;

import exceptions.BrowserNotSupportedException;
import factory.settings.ChromeBroserSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class WebDriverFactory {
  private String browser = System.getProperty("browser");

  public WebDriver create(String...settings){
    return switch (browser) {
      case "chrome" -> new ChromeDriver(new ChromeBroserSettings().settings(settings));
      case "edge" -> new EdgeDriver();
      default -> throw new BrowserNotSupportedException(String.format("%s is not supported!", browser));
    };
  }

  public void setup(){
    switch (browser) {
      case "chrome":
        WebDriverManager.chromedriver().setup();
        break;
      case "edge":
        WebDriverManager.edgedriver().setup();
      default:
        throw new BrowserNotSupportedException(String.format("%s is not supported!", browser));
    }
  }

}
