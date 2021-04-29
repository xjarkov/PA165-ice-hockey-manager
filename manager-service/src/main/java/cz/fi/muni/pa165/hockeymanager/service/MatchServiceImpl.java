package cz.fi.muni.pa165.hockeymanager.service;

import cz.fi.muni.pa165.hockeymanager.dao.MatchDao;
import cz.fi.muni.pa165.hockeymanager.entity.Match;
import java.util.List;
import org.dozer.inject.Inject;

/**
 * @author Lukas Machalek (485196)
 */
public class MatchServiceImpl implements MatchService {

    @Inject
    private MatchDao matchDao;

    @Override
    public void createMatch(Match match) {
        matchDao.create(match);
    }

    @Override
    public void updateMatch(Match match) {
        matchDao.update(match);
    }

    @Override
    public void removeMatch(Match match) {
        matchDao.remove(match);
    }

    @Override
    public Match getById(Long matchId) {
        return matchDao.findById(matchId);
    }

    @Override
    public List<Match> getAllMatches() {
        return matchDao.findAll();
    }
}
