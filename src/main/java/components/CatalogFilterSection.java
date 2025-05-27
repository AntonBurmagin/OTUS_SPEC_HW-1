package components;

import annotations.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.List;


@Component("css:::main section:first-child")
public class CatalogFilterSection extends AbsComponent{
  private final WebElement catalogFilter;

  public CatalogFilterSection(WebDriver driver){
    super(driver);
    waiter.waitForCondition(ExpectedConditions.visibilityOfElementLocated(getComponentBy()));
    catalogFilter = driver.findElement(getComponentBy());
  }


  //methods
  public List<WebElement> getFilters(){
    return catalogFilter.findElements(By.xpath("div"));
  }

  public List<String> getActiveInputNotDefaultFilterValues(){
    List<String> activeFilters = new ArrayList<>();

    for(WebElement filterType : getFilters()) {
      List<WebElement> list = filterType.findElements(By.cssSelector("label"));
      if (!list.isEmpty()) {
        list.removeFirst().getText();
        for (WebElement el : list) {
          if(el.findElement(By.xpath("..//input")).isSelected())
            activeFilters.addLast(el.getText());
        }
      }
    }

    return activeFilters;
  }




}