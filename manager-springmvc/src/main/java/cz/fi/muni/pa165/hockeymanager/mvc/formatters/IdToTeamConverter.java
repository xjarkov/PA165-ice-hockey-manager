package cz.fi.muni.pa165.hockeymanager.mvc.formatters;

import cz.fi.muni.pa165.hockeymanager.dto.TeamDto;
import cz.fi.muni.pa165.hockeymanager.facade.TeamFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IdToTeamConverter implements Converter<String, TeamDto> {

    private final static Logger logger = LoggerFactory.getLogger(IdToTeamConverter.class);

    @Autowired
    private TeamFacade teamFacade;

    @Override
    public TeamDto convert(String id) {
        //logger.info("facade: " + (teamFacade == null ? "null" : "not null"));
        //logger.info("Id: " + name);
        //logger.info("Test:" + teamFacade.findTeamById(Long.valueOf(name)).getName());
        return teamFacade.findTeamById(Long.valueOf( id ));
    }
}
