package com.applause.applause.web.rest;

import com.applause.applause.dto.TesterBugCountDTO;
import com.applause.applause.error.CountryNotFoundException;
import com.applause.applause.error.DeviceNotFoundException;
import com.applause.applause.service.DeviceService;
import com.applause.applause.service.TesterService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
* Rest Controller for Tester related data
*
*/
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@SuppressWarnings("unused")
public class TesterController {

  @Autowired
  private final TesterService testerService;

  @Autowired
  private final DeviceService deviceService;

  private  List<String> allDevices;

  private  List<String> allCountries;

  @GetMapping("/tester/bug/count")
  public ResponseEntity<List<TesterBugCountDTO>> getTesters(@RequestParam(name="country", required =false)
      List<String> countries, @RequestParam(name="device", required =false)
      List<String> devices)  {

    allDevices = deviceService.findAllDescription();
    allCountries = testerService.findAllCountry();
    if(devices == null) { devices = allDevices; }
    if(countries == null) { countries = allCountries; }

    if(!devices.stream()
        .allMatch(a -> allDevices.contains(a)))
      throw new DeviceNotFoundException();

    if(!countries.stream()
        .allMatch(a -> allCountries.contains(a)))
      throw new CountryNotFoundException();

    List<TesterBugCountDTO> testerBugCountDTOS = testerService.getTesterBugCount(countries, devices);
    return ResponseEntity.ok().body(testerBugCountDTOS);

  }

}
