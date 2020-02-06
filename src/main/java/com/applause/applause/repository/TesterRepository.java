package com.applause.applause.repository;

import com.applause.applause.domain.Tester;
import com.applause.applause.dto.TesterBugCountDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * JPA Repository for Tester entity
 */
public interface TesterRepository extends JpaRepository<Tester, Integer> {

  @Query(value = "SELECT t.country from Tester t")
  List<String> findAllCountry();

  @Query(value = "SELECT new com.applause.applause.dto.TesterBugCountDTO("
      + "t.firstName, t.lastName, COUNT(*)) "
      + "from Tester t "
      + "JOIN t.bugs b "
      + "JOIN b.device q "
      + "ON q.description IN :devices "
      + "WHERE t.country IN :countries "
      + "GROUP by t.firstName "
      + "ORDER BY COUNT(*) DESC")
  List<TesterBugCountDTO> findBugCount(@Param("countries") List<String> countries,
      @Param("devices") List<String> devices);

}
