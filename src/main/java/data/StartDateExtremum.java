package data;

import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.util.List;

public class StartDateExtremum {
  private LocalDate nearestDate = null;
  private LocalDate latestDate = null;


  public StartDateExtremum(List<String> startDates) {
    if(startDates.isEmpty())
      throw new RuntimeException("There is no courses with correct start date!");

    for (String date : startDates) {
      String []startDateTextSplit = date.split(" ");

      LocalDate startDate = LocalDate.of(Integer.parseInt(startDateTextSplit[2]), MonthData.customValueOf(startDateTextSplit[1]).getValue(), Integer.parseInt(startDateTextSplit[0]));
      if (nearestDate == null && latestDate == null) {
        nearestDate = startDate;
        latestDate = startDate;
      }
      if (startDate.isBefore(nearestDate)) {
        nearestDate = startDate;
      }
      if (startDate.isAfter(latestDate)) {
        latestDate = startDate;
      }
    }
  }


  public LocalDate getNearestDate() {
    return nearestDate;
  }

  public LocalDate getLatestDate() {
    return latestDate;
  }

}
