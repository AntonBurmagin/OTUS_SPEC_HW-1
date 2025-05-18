package data;


public enum MonthData {
  JANUARY("янвяря,"),
  FEBRUARY("февраля,"),
  MARCH("марта,"),
  APRIL("апреля,"),
  MAY("мая,"),
  JUNE("июня,"),
  JULY("июля,"),
  AUGUST("августа,"),
  SEPTEMBER("сентября,"),
  OCTOBER("октября,"),
  NOVEMBER("ноября,"),
  DECEMBER("декабря,");


  private String name;

  MonthData(String name) {
    this.name = name;
  }

  public String getName(){
    return name;
  }

  public Integer getValue(){
    return switch (name){
      case("января,") -> 1;
      case("февраля,") -> 2;
      case("марта,") -> 3;
      case("апреля,") -> 4;
      case("мая,") -> 5;
      case("июня,") -> 6;
      case("июля,") -> 7;
      case("августа,") -> 8;
      case("сентября,") -> 9;
      case("октября,") -> 10;
      case("ноября,") -> 11;
      case("декабря,") -> 12;
      default -> throw new RuntimeException(String.format("There is no such month(%s) in data", name));
    };
  }

  static public MonthData customValueOf(String name){
    return switch (name){
      case("января,") -> MonthData.JANUARY;
      case("февраля,") -> MonthData.FEBRUARY;
      case("марта,") -> MonthData.MARCH;
      case("апреля,") -> MonthData.APRIL;
      case("мая,") -> MonthData.MAY;
      case("июня,") -> MonthData.JUNE;
      case("июля,") -> MonthData.JULY;
      case("августа,") -> MonthData.AUGUST;
      case("сентября,") -> MonthData.SEPTEMBER;
      case("октября,") -> MonthData.OCTOBER;
      case("ноября,") -> MonthData.NOVEMBER;
      case("декабря,") -> MonthData.DECEMBER;
      default -> throw new RuntimeException(String.format("There is no such month(%s) in data", name));
    };
  }

  static public MonthData customValueOf(Integer index){
    return switch (index){
      case(1) -> MonthData.JANUARY;
      case(2) -> MonthData.FEBRUARY;
      case(3) -> MonthData.MARCH;
      case(4) -> MonthData.APRIL;
      case(5) -> MonthData.MAY;
      case(6) -> MonthData.JUNE;
      case(7) -> MonthData.JULY;
      case(8) -> MonthData.AUGUST;
      case(9) -> MonthData.SEPTEMBER;
      case(10) -> MonthData.OCTOBER;
      case(11) -> MonthData.NOVEMBER;
      case(12) -> MonthData.DECEMBER;
      default -> throw new RuntimeException(String.format("There is no month with index (%s) in data", index));
    };
  }
}