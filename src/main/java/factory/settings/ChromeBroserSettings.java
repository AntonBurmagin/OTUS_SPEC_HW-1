package factory.settings;

import org.openqa.selenium.chrome.ChromeOptions;


public class ChromeBroserSettings implements IBrowserSettings{

  @Override
  public ChromeOptions settings(String...settings) {
    ChromeOptions options = new ChromeOptions();
    options.addArguments(settings);
    return options;
  }
}
