package exceptions;

public class PathNotDeclaredException extends RuntimeException {
  public PathNotDeclaredException(String message) {
    super(message);
  }
}
