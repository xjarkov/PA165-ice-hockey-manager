package cz.fi.muni.pa165.hockeymanager.mvc.formatters;

import cz.fi.muni.pa165.hockeymanager.dto.TeamDto;
import cz.fi.muni.pa165.hockeymanager.facade.TeamFacade;
import cz.fi.muni.pa165.hockeymanager.mvc.LoggedInFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class IdToTeamConverter implements Converter<String, TeamDto> {

    private final static Logger logger = LoggerFactory.getLogger(IdToTeamConverter.class);

    @Autowired
    TeamFacade teamFacade;

    @Override
    public TeamDto convert(String id) {
        logger.info("facade: " + teamFacade.getClass().getName());
        logger.info("Id: " + id);
        return teamFacade.findTeamById(Long.valueOf(id));
    }
}
