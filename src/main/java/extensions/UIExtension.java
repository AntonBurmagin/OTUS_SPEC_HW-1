package extensions;

import com.google.inject.Guice;
import com.google.inject.Injector;
import factory.WebDriverFactory;
import modules.GuiceComponentsModule;
import modules.GuicePagesModule;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

public class UIExtension implements BeforeEachCallback, AfterEachCallback {

  private Injector injector = null;

  @Override
  public void afterEach(ExtensionContext context) {
    WebDriver driver = injector.getInstance(WebDriver.class);
    if (driver != null)
      driver.quit();
  }

  @Override
  public void beforeEach(ExtensionContext context) {
    WebDriver driver = new WebDriverFactory().create("--start-fullscreen");

    injector = Guice.createInjector(new GuicePagesModule(driver), new GuiceComponentsModule(driver));
    injector.injectMembers(context.getTestInstance().get());
  }
}
