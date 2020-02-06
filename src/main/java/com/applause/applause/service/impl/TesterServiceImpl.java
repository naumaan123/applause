package com.applause.applause.service.impl;

import com.applause.applause.dto.TesterBugCountDTO;
import com.applause.applause.repository.TesterRepository;
import com.applause.applause.service.DeviceService;
import com.applause.applause.service.TesterService;
import java.util.List;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class TesterServiceImpl implements TesterService {

  @Autowired
  private final DeviceService deviceService;

  @Autowired
  private final TesterRepository testerRepository;


  @Override
  public List<TesterBugCountDTO> getTesterBugCount(List<String> countries,
      List<String> devices) {

////    if(devices == null) {
////      devices = new ArrayList<>();
////      List<String> defaultDevices = deviceService.findAllDescription();
////      defaultDevices.stream().forEachOrdered(devices::add);
////    }
//    if(countries == null) {
//      countries = new ArrayList<>();
//      List<String> defaultCountries = findAllCountry();
//      defaultCountries.stream().forEachOrdered(countries::add);
//    }

    List<TesterBugCountDTO> testerBugCountDTOSs = testerRepository.findBugCount(countries, devices);
    return testerBugCountDTOSs;
  }

  @Override
  @Cacheable(value = "testers")
  public List<String> findAllCountry() {
    return testerRepository.findAllCountry();
  }
}
