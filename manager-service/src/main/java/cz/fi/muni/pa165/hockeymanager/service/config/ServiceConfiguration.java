package cz.fi.muni.pa165.hockeymanager.service.config;

import cz.fi.muni.pa165.hockeymanager.PersistenceApplicationContext;
import cz.fi.muni.pa165.hockeymanager.service.MatchServiceImpl;
import cz.fi.muni.pa165.hockeymanager.service.UserServiceImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistenceApplicationContext.class)
@ComponentScan(basePackageClasses={UserServiceImpl.class, MatchServiceImpl.class})
public class ServiceConfiguration {
    @Bean
    public Mapper dozer() {
        DozerBeanMapper dozer = new DozerBeanMapper();
        return dozer;
    }
}
