package cz.fi.muni.pa165.hockeymanager.service.facade;

import cz.fi.muni.pa165.hockeymanager.dto.MatchDto;
import cz.fi.muni.pa165.hockeymanager.facade.MatchFacade;
import cz.fi.muni.pa165.hockeymanager.utils.ScoreTuple;

import java.util.List;

public class MatchFacadeImpl implements MatchFacade {
    @Override
    public Long create(MatchDto match) {
        return null;
    }

    @Override
    public void remove(Long matchId) {

    }

    @Override
    public MatchDto findMatchById(Long id) {
        return null;
    }

    @Override
    public List<MatchDto> findAllMatches() {
        return null;
    }

    @Override
    public MatchDto findNearestMatch() {
        return null;
    }

    @Override
    public ScoreTuple getScoreOfMatch(Long matchId) {
        return null;
    }
}
