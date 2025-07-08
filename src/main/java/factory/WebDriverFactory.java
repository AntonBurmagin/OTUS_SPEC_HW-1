package factory;

import exceptions.BrowserNotSupportedException;
import factory.settings.ChromeBroserSettings;
import factory.settings.EdgeBrowserSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.ActionsListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

public class WebDriverFactory implements IDriverFactory {
  private final String browser = System.getProperty("browser");


  @Override
  public WebDriver create(String...settings){
    setup();
    return switch (browser) {
      case "chrome" -> new EventFiringDecorator<>(new ActionsListener()).decorate(new ChromeDriver(new ChromeBroserSettings().settings(settings)));
      case "edge" -> new EventFiringDecorator<>(new ActionsListener()).decorate(new EdgeDriver(new EdgeBrowserSettings().settings(settings)));
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
        break;
      default:
        throw new BrowserNotSupportedException(String.format("%s is not supported!", browser));
    }
  }

}
