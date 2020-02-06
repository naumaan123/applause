package com.applause.applause.mapper;

import com.applause.applause.domain.Bug;
import com.applause.applause.domain.Device;
import com.applause.applause.domain.Tester;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 * Mapper class to map Spring Batch field set to custom
 * Bug object. Needed to map empty object with only Id set
 * so Spring Batch can insert Ids into H2 database without
 * hitting a type mismatch
 */

public class BugMapper implements FieldSetMapper<Bug> {
  @Override
  public Bug mapFieldSet(FieldSet fieldSet) throws BindException {
    Bug mc = new Bug();
    mc.setBugId(fieldSet.readInt(0));
    mc.setDevice(new Device((fieldSet.readLong(1)), null, null, null));
    mc.setTester(new Tester((fieldSet.readLong(2)), null, null, null, null, null));
    return mc;
  }
}