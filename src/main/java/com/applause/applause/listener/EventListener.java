package com.applause.applause.listener;

import com.applause.applause.service.DeviceService;
import com.applause.applause.service.TesterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.stereotype.Component;

/**
 * Event Listener to cache Testers and Devices
 * on startup
 */
@Component
@Slf4j
public class EventListener {

  @Autowired
  TesterService testerService;

  @Autowired
  DeviceService deviceService;

  @org.springframework.context.event.EventListener
  public void onApplicationReady(ApplicationReadyEvent ready) {
      testerService.findAllCountry();
      deviceService.findAllDescription();
  }
}
