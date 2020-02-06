package com.applause.applause.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Bug Entity
 */
@Slf4j
@Entity
@Data
@Table(name = "bugs")
@AllArgsConstructor
@NoArgsConstructor
public class Bug {

  @Id
  @Column(name="bugId")
  private Integer bugId;

  @ManyToOne(targetEntity = Device.class)
  @JoinColumn(name = "deviceId")
  private Device device;

  @ManyToOne(targetEntity = Tester.class)
  @JoinColumn(name = "testerId")
  private Tester tester;

}
