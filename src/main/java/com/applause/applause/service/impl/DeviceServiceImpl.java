package com.applause.applause.service.impl;

import com.applause.applause.repository.DeviceRepository;
import com.applause.applause.service.DeviceService;
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
public class DeviceServiceImpl implements DeviceService {

  @Autowired
  private final DeviceRepository deviceRepository;

  @Override
  @Cacheable(value="devices")
  public List<String> findAllDescription() {
    return deviceRepository.findAllDescription();
  }
}
