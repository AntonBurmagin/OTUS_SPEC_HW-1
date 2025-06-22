package data;

import java.time.LocalDate;
import java.util.List;


public class StartDateExtremum {
  private LocalDate nearestDate = null;
  private LocalDate latestDate = null;


  public void initialize(List<String> startDates) {
    if (!startDates.isEmpty()) {
      List<LocalDate> localDates = startDates.stream().map((date) -> {
        String []dateArr = date.split(" ");
        return LocalDate.of(Integer.parseInt(dateArr[2]), MonthData.customValueOf(dateArr[1]).getValue(), Integer.parseInt(dateArr[0]));
      }).toList();
      nearestDate = localDates.stream().reduce((firstLocal, secondLocal) -> {
        return firstLocal.isBefore(secondLocal) ? firstLocal : secondLocal;
      }).orElse(null);
      latestDate = localDates.stream().reduce((firstLocal, secondLocal) -> {
        return firstLocal.isAfter(secondLocal) ? firstLocal : secondLocal;
      }).orElse(null);
    }
  }

  public LocalDate getNearestDate() {
    return nearestDate;
  }

  public LocalDate getLatestDate() {
    return latestDate;
  }

}
