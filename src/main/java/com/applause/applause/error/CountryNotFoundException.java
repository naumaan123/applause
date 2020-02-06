package com.applause.applause.error;

/**
 * Thrown Error for invalid Country
 */
public class CountryNotFoundException extends RuntimeException {

  public CountryNotFoundException() {
    super("Country does not exist");
  }

}
