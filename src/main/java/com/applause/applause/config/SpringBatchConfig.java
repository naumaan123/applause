
package com.applause.applause.config;

import com.applause.applause.domain.Bug;
import com.applause.applause.mapper.BugMapper;
import javax.sql.DataSource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


/**
 * Spring Batch Configuration to ingest a csv
 * file for the purposes of loading into in memory
 * database.
 *
 * Code modeled closely after spring.io documents on
 * Spring Batching
 */

@Slf4j
@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig extends DefaultBatchConfigurer {

  @Autowired
  public JobBuilderFactory jobBuilderFactory;

  @Autowired
  public StepBuilderFactory stepBuilderFactory;

  @Override
  public void setDataSource(DataSource dataSource) {
    // override to do not set datasource even if a datasource exist.
    // initialize will use a Map based JobRepository (instead of database)
  }

  @Bean
  public FlatFileItemReader<Bug> reader() {
    return new FlatFileItemReaderBuilder<Bug>()
        .name("BugItemReader")
        .resource(new ClassPathResource("com/bugs.csv"))
        .delimited()
        .names(new String[]{"bugId", "tester", "device"})
        .fieldSetMapper(new BugMapper())
        .build();
  }


  @Bean
  public JdbcBatchItemWriter<Bug> writer(DataSource dataSource) {
    return new JdbcBatchItemWriterBuilder<Bug>()
        .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
        .sql("INSERT INTO bugs (BUG_ID, DEVICE_ID, TESTER_ID) "
            + "VALUES (:bugId, :device.deviceId, :tester.testerId)")
        .dataSource(dataSource)
        .build();
  }

  @Bean
  public Job importBugJob(JobCompletionNotificationListener listener, Step step1) {
    return jobBuilderFactory.get("importBugJob")
        .incrementer(new RunIdIncrementer())
        .listener(listener)
        .flow(step1)
        .end()
        .build();
  }

  @Bean
  public Step step1(JdbcBatchItemWriter<Bug> writer) {
    return stepBuilderFactory.get("step1")
        .<Bug, Bug> chunk(10)
        .reader(reader())
        .writer(writer)
        .build();
  }

}
