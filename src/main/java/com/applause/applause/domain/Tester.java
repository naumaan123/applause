package com.applause.applause.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Tester Entity
 */
@Slf4j
@Entity
@Data
@Table(name = "testers")
@AllArgsConstructor
@NoArgsConstructor
public class Tester implements Serializable {

  @Id
  @Column(name="testerId")
  private Long testerId;

  @Column(name="firstName")
  private String firstName;

  @Column(name="lastName")
  private String lastName;

  @Column(name="country")
  private String country;

  @OneToMany(mappedBy = "tester", fetch = FetchType.LAZY)
  private List<Bug> bugs;

  @ManyToMany
  @JoinTable(
      name="TESTERS_DEVICES",
      joinColumns = @JoinColumn( name="testerId"),
      inverseJoinColumns = @JoinColumn( name="deviceId")
  )
  @ToString.Exclude
  private List<Device> devices;

}
