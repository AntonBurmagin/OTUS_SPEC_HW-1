package factory;

import exceptions.BrowserNotSupportedException;
import org.openqa.selenium.WebDriver;

public interface IDriverFactory {
  WebDriver create(String...settings) throws BrowserNotSupportedException;
}
