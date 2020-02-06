package com.applause.applause.error;

/**
 * Thrown Error for invalid Device
 */
public class DeviceNotFoundException extends RuntimeException {

  public DeviceNotFoundException() {
    super("Device does not exist");
  }

}
