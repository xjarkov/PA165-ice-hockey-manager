package cz.fi.muni.pa165.hockeymanager.sampledata;

import cz.fi.muni.pa165.hockeymanager.service.config.ServiceConfiguration;

import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Kristian Kosorin (456620)
 */
@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses = {SampleDataLoadingFacadeImpl.class})
public class SampleDataConfiguration {
    @Autowired
    SampleDataLoadingFacade sampleDataLoadingFacade;

    @PostConstruct
    public void dataLoading() {
        sampleDataLoadingFacade.loadSampleData();
    }
}
