package cz.fi.muni.pa165.hockeymanager.service;

import cz.fi.muni.pa165.hockeymanager.dao.MatchDao;
import cz.fi.muni.pa165.hockeymanager.entity.Match;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cz.fi.muni.pa165.hockeymanager.exceptions.ManagerServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * @author Lukas Machalek (485196)
 */
@Service
public class MatchServiceImpl implements MatchService {
    @Autowired
    private MatchDao matchDao;

    @Override
    public Match createMatch(Match match) {
        try {
            matchDao.create(match);
        } catch (DataAccessException e) {
            throw new ManagerServiceException("Could not create match " + match + " exception: " + e);
        }

        return match;
    }

    @Override
    public void removeMatch(Match match) {
        try {
            matchDao.remove(match);
        } catch (DataAccessException e) {
            throw new ManagerServiceException("Could not create remove " + match + " exception: " + e);
        }
    }

    @Override
    public void updateMatch(Match match) {
        try {
            matchDao.update(match);
        } catch (DataAccessException e) {
            throw new ManagerServiceException("Could not update match " + match + " exception: " + e);
        }
    }

    @Override
    public Match getById(Long matchId) {
        try {
            return matchDao.findById(matchId);
        } catch (DataAccessException e) {
            throw new ManagerServiceException("Could not get match by id " + matchId + " exception: " + e);
        }
    }

    @Override
    public List<Match> getAllMatches() {
        try {
            return matchDao.findAll();
        } catch (DataAccessException e) {
            throw new ManagerServiceException("Could not get all matches exception: " + e);
        }
    }

    @Override
    public Match getNearestMatch() {
        try {
            return Collections.min(matchDao.findAll(), Comparator.comparing(Match::getDateTime));
        } catch (DataAccessException e) {
            throw new ManagerServiceException("Could not get nearest match exception: " + e);
        }
    }
}
