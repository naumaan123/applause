package com.applause.applause.repository;

import com.applause.applause.domain.Bug;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA Repository for Bug entity
 */
@Repository
@Transactional
public interface BugRepository extends JpaRepository<Bug, Integer> {

  @Override
  List<Bug> findAll();

}
