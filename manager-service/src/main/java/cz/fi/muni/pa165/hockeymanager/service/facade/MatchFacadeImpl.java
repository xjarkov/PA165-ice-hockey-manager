package cz.fi.muni.pa165.hockeymanager.service.facade;

import cz.fi.muni.pa165.hockeymanager.dto.MatchDto;
import cz.fi.muni.pa165.hockeymanager.dto.TeamDto;
import cz.fi.muni.pa165.hockeymanager.entity.Match;
import cz.fi.muni.pa165.hockeymanager.facade.MatchFacade;
import cz.fi.muni.pa165.hockeymanager.service.BeanMappingService;
import cz.fi.muni.pa165.hockeymanager.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import utils.ScoreTupleImpl;

import java.util.List;

public class MatchFacadeImpl implements MatchFacade {

    @Autowired
    private MatchService matchService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long create(MatchDto match) {
        Match mappedMatch = beanMappingService.mapTo(match, Match.class);
        matchService.createMatch(mappedMatch);
        match.setId(mappedMatch.getId());
        return match.getId();
    }

    @Override
    public void remove(Long matchId) {
        matchService.removeMatch(matchService.getById(matchId));
    }

    @Override
    public MatchDto findMatchById(Long id) {
        Match match = matchService.getById(id);
        return (match == null) ? null : beanMappingService.mapTo(match, MatchDto.class);
    }

    @Override
    public List<MatchDto> findAllMatches() {
        return beanMappingService.mapTo(matchService.getAllMatches(), MatchDto.class);
    }

    @Override
    public MatchDto findNearestMatch() {
        return beanMappingService.mapTo(matchService.getNearestMatch(), MatchDto.class);
    }

    @Override
    public ScoreTupleImpl getScoreOfMatch(Long matchId) {
        Match match = matchService.getById(matchId);
        return new ScoreTupleImpl(match.getHomeTeamScore(), match.getVisitingTeamScore());
    }

    @Override
    public TeamDto getWinningTeam(Long matchId) {
        Match match = matchService.getById(matchId);
        ScoreTupleImpl score = getScoreOfMatch(matchId);
        if (score.getHomeScore() == score.getVisitingScore()) {
            return null;
        }
        return (score.getHomeScore() > score.getVisitingScore())
                ? beanMappingService.mapTo(match.getHomeTeam(), TeamDto.class)
                : beanMappingService.mapTo(match.getVisitingTeam(), TeamDto.class);
    }
}
