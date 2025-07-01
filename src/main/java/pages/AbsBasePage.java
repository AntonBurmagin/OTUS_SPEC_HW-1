package pages;

import annotations.Path;
import common.AbsCommon;
import exceptions.PathNotDeclaredException;
import org.openqa.selenium.WebDriver;


public abstract class AbsBasePage extends AbsCommon {
//  private String baseUrl = System.getProperty("base.url");
  private String baseUrl = "https://otus.ru";

  public AbsBasePage(WebDriver driver) {
    super(driver);
  }

  public String getAddPath(){
    Class cl = this.getClass();
    if (cl.isAnnotationPresent(Path.class)) {
      Path path = (Path) cl.getDeclaredAnnotation(Path.class);
      return path.value();
    }
    throw new PathNotDeclaredException(String.format("Path annotation is not declared for page %s", cl.getCanonicalName()));
  }

  public void open(){
    baseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.lastIndexOf("/")) : baseUrl;
    driver.get(baseUrl + getAddPath());
  }

}
