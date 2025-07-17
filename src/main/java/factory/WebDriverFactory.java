package factory;

import exceptions.BrowserNotSupportedException;
import factory.settings.ChromeBrowserSettings;
import factory.settings.FirefoxBrowserSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.ActionsListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;


public class WebDriverFactory implements IDriverFactory {
  private String browser = System.getProperty("browser");


  @Override
  public WebDriver create(String...settings){
    setup();
    return switch (browser) {
      case "chrome" -> new EventFiringDecorator<>(new ActionsListener()).decorate(new ChromeDriver(new ChromeBrowserSettings().settings(settings)));
      case "firefox" -> new EventFiringDecorator<>(new ActionsListener()).decorate(new FirefoxDriver(new FirefoxBrowserSettings().settings(settings)));
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
      case "firefox":
        WebDriverManager.firefoxdriver().setup();
        break;
      default:
        throw new BrowserNotSupportedException(String.format("%s is not supported!", browser));
    }
  }

  public void changeBrowser(String browserName) {
    this.browser = browserName;
  }


}
