package com.applause.applause.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for holding Tester Bug Count data
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TesterBugCountDTO {

  private String firstName;

  private String lastName;

  private Long count;

}
