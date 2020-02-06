package com.applause.applause.service;

import com.applause.applause.dto.TesterBugCountDTO;
import java.util.List;

/**
 * Tester Service Interface
 */
public interface TesterService {

  List<TesterBugCountDTO> getTesterBugCount(List<String> countries,
      List<String> devices);

  List<String> findAllCountry();

}
