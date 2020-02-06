package com.applause.applause.repository;

import com.applause.applause.domain.Device;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * JPA Repository for Device entity
 */
public interface DeviceRepository extends JpaRepository<Device, Integer> {

  @Query(value = "SELECT d.description from Device d")
  List<String> findAllDescription();

}
