package cz.fi.muni.pa165.hockeymanager.service.facade;

import cz.fi.muni.pa165.hockeymanager.dto.MatchCreateDto;
import cz.fi.muni.pa165.hockeymanager.dto.MatchDto;
import cz.fi.muni.pa165.hockeymanager.dto.TeamDto;
import cz.fi.muni.pa165.hockeymanager.entity.Match;
import cz.fi.muni.pa165.hockeymanager.facade.MatchFacade;
import cz.fi.muni.pa165.hockeymanager.service.BeanMappingService;
import cz.fi.muni.pa165.hockeymanager.service.MatchService;
import cz.fi.muni.pa165.hockeymanager.utils.ScoreTuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * @author Matus Jarkovic 456441
 */
@Service
@Transactional
public class MatchFacadeImpl implements MatchFacade {
    @Autowired
    private MatchService matchService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long create(MatchCreateDto matchDto) {
        Match mappedMatch = beanMappingService.mapTo(matchDto, Match.class);
        matchService.create(mappedMatch);
        return mappedMatch.getId();
    }

    @Override
    public void remove(MatchDto matchDto) {
        matchService.remove(beanMappingService.mapTo(matchDto, Match.class));
    }

    @Override
    public void update(MatchDto matchDto) {
        matchService.update(beanMappingService.mapTo(matchDto, Match.class));
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
    public ScoreTuple getScoreOfMatch(Long matchId) {
        Match match = matchService.findById(matchId);
        return new ScoreTuple(match.getHomeTeamScore(), match.getVisitingTeamScore());
    }

    @Override
    public TeamDto getWinningTeam(Long matchId) {
        Match match = matchService.findById(matchId);
        ScoreTuple score = getScoreOfMatch(matchId);
        if (score.getHomeScore() == score.getVisitingScore()) {
            return null;
        }
        return (score.getHomeScore() > score.getVisitingScore())
                ? beanMappingService.mapTo(match.getHomeTeam(), TeamDto.class)
                : beanMappingService.mapTo(match.getVisitingTeam(), TeamDto.class);
    }
}
