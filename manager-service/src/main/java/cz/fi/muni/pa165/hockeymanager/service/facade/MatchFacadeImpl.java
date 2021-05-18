package cz.fi.muni.pa165.hockeymanager.service.facade;

import cz.fi.muni.pa165.hockeymanager.dto.MatchDto;
import cz.fi.muni.pa165.hockeymanager.dto.TeamDto;
import cz.fi.muni.pa165.hockeymanager.entity.Match;
import cz.fi.muni.pa165.hockeymanager.facade.MatchFacade;
import cz.fi.muni.pa165.hockeymanager.service.BeanMappingService;
import cz.fi.muni.pa165.hockeymanager.service.MatchService;

import java.util.List;
import utils.ScoreTupleImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MatchFacadeImpl implements MatchFacade {
    @Autowired
    private MatchService matchService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long create(MatchDto match) {
        Match mappedMatch = beanMappingService.mapTo(match, Match.class);
        matchService.create(mappedMatch);
        match.setId(mappedMatch.getId());
        return match.getId();
    }

    @Override
    public void remove(MatchDto match) {
        matchService.remove(beanMappingService.mapTo(match, Match.class));
    }

    @Override
    public MatchDto findMatchById(Long id) {
        Match match = matchService.findById(id);
        return (match == null) ? null : beanMappingService.mapTo(match, MatchDto.class);
    }

    @Override
    public List<MatchDto> findAllMatches() {
        return beanMappingService.mapTo(matchService.findAll(), MatchDto.class);
    }

    @Override
    public MatchDto findNearestMatch() {
        return beanMappingService.mapTo(matchService.findNearest(), MatchDto.class);
    }

    @Override
    public ScoreTupleImpl getScoreOfMatch(Long matchId) {
        Match match = matchService.findById(matchId);
        return new ScoreTupleImpl(match.getHomeTeamScore(), match.getVisitingTeamScore());
    }

    @Override
    public TeamDto getWinningTeam(Long matchId) {
        Match match = matchService.findById(matchId);
        ScoreTupleImpl score = getScoreOfMatch(matchId);
        if (score.getHomeScore() == score.getVisitingScore()) {
            return null;
        }
        return (score.getHomeScore() > score.getVisitingScore())
                ? beanMappingService.mapTo(match.getHomeTeam(), TeamDto.class)
                : beanMappingService.mapTo(match.getVisitingTeam(), TeamDto.class);
    }
}
