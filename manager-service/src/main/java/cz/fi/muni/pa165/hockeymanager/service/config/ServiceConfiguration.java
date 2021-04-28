package cz.fi.muni.pa165.hockeymanager.service.config;

import cz.fi.muni.pa165.hockeymanager.PersistenceApplicationContext;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistenceApplicationContext.class)
public class ServiceConfiguration {
    @Bean
    public Mapper dozer() {
        DozerBeanMapper dozer = new DozerBeanMapper();
        return dozer;
    }
}
