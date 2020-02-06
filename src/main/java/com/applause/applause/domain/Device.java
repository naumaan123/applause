package com.applause.applause.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Device Entity
 */
@Slf4j
@Entity
@Data
@Table(name = "devices")
@AllArgsConstructor
@NoArgsConstructor
public class Device implements Serializable {



  @Id
  @Column(name="deviceId")
  private Long deviceId;

  @Column(name="description")
  private String description;

  @OneToMany(mappedBy = "device", fetch = FetchType.LAZY)
  private List<Bug> bugs;

  @ManyToMany(mappedBy = "devices")
  private List<Tester> testers;

}
