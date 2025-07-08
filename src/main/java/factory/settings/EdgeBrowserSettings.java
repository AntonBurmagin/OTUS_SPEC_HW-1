package factory.settings;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeBrowserSettings implements IBrowserSettings{
  @Override
  public EdgeOptions settings(String...settings) {
    EdgeOptions options = new EdgeOptions();
    options.addArguments(settings);
    return options;
  }
}
