package components;

import annotations.Component;
import common.AbsCommon;
import exceptions.ComponentByTypeNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public abstract class AbsComponent extends AbsCommon {

  public AbsComponent(WebDriver driver){
    super(driver);
  }

  public By getComponentBy(){
    Class cl = getClass();
    if (cl.isAnnotationPresent(Component.class)) {
      Component component = (Component) cl.getDeclaredAnnotation(Component.class);
      String[] value = component.value().split(":::");
      return switch (value[0]) {
        case "css" -> By.cssSelector(value[1]);
        case "xpath" -> By.xpath(value[1]);
        case "id" -> By.id(value[1]);
        default -> throw new RuntimeException(String.format("Mistake in locator type By '%s'", value[0]));
      };
    }
    throw new ComponentByTypeNotFoundException(String.format("Component annotation is not declared for %s", cl.getCanonicalName()));
  }
}
