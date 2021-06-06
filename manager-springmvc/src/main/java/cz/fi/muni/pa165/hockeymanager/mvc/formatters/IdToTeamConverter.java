package cz.fi.muni.pa165.hockeymanager.mvc.formatters;

import cz.fi.muni.pa165.hockeymanager.dto.TeamDto;
import cz.fi.muni.pa165.hockeymanager.facade.TeamFacade;

import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class IdToTeamConverter implements Converter<String, TeamDto> {

    @Autowired
    private TeamFacade teamFacade;

    @Override
    public TeamDto convert(String id) {
        return teamFacade.findTeamById(Long.valueOf( id ));
    }
}
