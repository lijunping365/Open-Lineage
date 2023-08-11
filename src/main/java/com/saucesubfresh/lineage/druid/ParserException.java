package com.saucesubfresh.lineage.druid;

/**
 * ParserException.
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 20:57
 */
public class ParserException extends RuntimeException{

  public ParserException(String message, Throwable cause) {
    super(message, cause);
  }

  public ParserException(String message) {
    super(message);
  }

  public ParserException(String format, Object... args) {
    super(String.format(format, args));
  }

  public ParserException(Throwable cause) {
    super(cause);
  }

}
