package factory.settings;

import org.openqa.selenium.firefox.FirefoxOptions;


public class FirefoxBrowserSettings implements IBrowserSettings{

  @Override
  public FirefoxOptions settings(String...settings) {
    FirefoxOptions options = new FirefoxOptions();
    options.addArguments(settings);
    return options;
  }

}

