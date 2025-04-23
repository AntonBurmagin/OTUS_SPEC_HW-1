package exceptions;

public class BrowserNotSupportedException extends RuntimeException {
  public BrowserNotSupportedException(String message) {
    super(message);
  }
}
