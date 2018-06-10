package com.es.nf.batch.configuration;

import com.es.nf.batch.dto.PersonnageDTO;
import com.es.nf.batch.processor.EmptyProcessor;
import com.es.nf.batch.processor.HealthProcessor;
import com.es.nf.batch.reader.HealthReader;
import com.es.nf.batch.reader.PersonnageReader;
import com.es.nf.batch.writer.EmptyWriter;
import com.es.nf.batch.writer.PersonnageWriter;
import com.es.nf.commons.ws.Auth0RestTemplate;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.activation.DataSource;

import static java.lang.String.format;


@Configuration
@EnableBatchProcessing
public class BatchConfiguration extends DefaultBatchConfigurer {

    private static final String K_CLIENT_CREDENTIALS = "client_credentials";

    private int size = 10;

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Value("${com.es.nf.batch.oauth2.tokenUrl}")
    private String accessTokenURL;

    @Value("${com.es.nf.batch.oauth2.portAccessTokenUrl}")
    private String portAccessTokenURL;

    @Bean
    public DataSource dataSource() {
        return null;
    }

    @Bean
    public PlatformTransactionManager getTransactionManager() {
        return new ResourcelessTransactionManager();
    }

    @Bean
    public Job job() throws Exception {
        return jobBuilderFactory.get("job").incrementer(new RunIdIncrementer()).listener(listener()).flow(loadStep())
                .next(stepParallelProcessing()).end().build();
    }


    @Bean
    public Step loadStep() throws Exception {
        return stepBuilderFactory.get("loadStep").<PersonnageDTO, PersonnageDTO>chunk(1).reader(reader())
                .processor(processEmpty()).writer(writerEmpty()).build();
    }

    @Bean
    public ItemProcessor<PersonnageDTO, PersonnageDTO> processEmpty() {
        return new EmptyProcessor();
    }

    @Bean
    public ItemWriter<PersonnageDTO> writerEmpty() {
        return new EmptyWriter();
    }


    @Bean
    public Step stepParallelProcessing() throws Exception {
        return stepBuilderFactory.get("Healthstep").<PersonnageDTO, PersonnageDTO>chunk(1)
                .reader(readerHealth()).processor(processor()).writer(writer()).throttleLimit(size)
                .taskExecutor(taskExecutor()).build();
    }

    @Bean
    public ItemReader<? extends PersonnageDTO> readerHealth() {
        return new HealthReader();
    }

    @Bean
    public PersonnageReader reader() {
        return new PersonnageReader();
    }

    @Bean
    public HealthProcessor processor() {
        return new HealthProcessor();
    }

    @Bean
    public PersonnageWriter writer() {
        return new PersonnageWriter();
    }

    @Bean
    public JobExecutionListener listener() {
        return new JobExecutionListenerSupport();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(size);
        taskExecutor.setCorePoolSize(size);
        taskExecutor.afterPropertiesSet();
        return taskExecutor;
    }

    @Bean
    public Auth0RestTemplate restTemplate() {
        {
            return new Auth0RestTemplate();
        }


    }

    public String getAccessTokenURL() {
        return accessTokenURL;
    }

    public void setAccessTokenURL(String accessTokenURL) {
        this.accessTokenURL = accessTokenURL;

    }

    public String getPortAccessTokenURL() {
        return portAccessTokenURL;
    }

    public void setPortAccessTokenURL(String portAccessTokenURL) {
        this.portAccessTokenURL = portAccessTokenURL;
    }

}