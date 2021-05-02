package cz.fi.muni.pa165.hockeymanager.service;

import cz.fi.muni.pa165.hockeymanager.dao.HockeyPlayerDao;
import cz.fi.muni.pa165.hockeymanager.dao.MatchDao;
import cz.fi.muni.pa165.hockeymanager.entity.HockeyPlayer;
import cz.fi.muni.pa165.hockeymanager.entity.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Petr Šopf (506511)
 */
@Service
public class HockeyPlayerServiceImpl implements HockeyPlayerService {
    @Autowired
    private HockeyPlayerDao hockeyPlayerDao;

    @Autowired
    private MatchDao matchDao;

    @Override
    public HockeyPlayer create(HockeyPlayer player) {
        hockeyPlayerDao.create(player);
        return player;
    }

    @Override
    public void remove(HockeyPlayer player) {
        hockeyPlayerDao.remove(player);
    }

    @Override
    public void update(HockeyPlayer player) {
        hockeyPlayerDao.update(player);
    }

    @Override
    public HockeyPlayer findById(Long id) {
        return hockeyPlayerDao.findById(id);
    }

    @Override
    public List<HockeyPlayer> getAllPlayers() {
        return hockeyPlayerDao.findAll();
    }

    @Override
    public List<Match> getPlayerTeamMatches(HockeyPlayer player) {
        return matchDao.findByTeam(player.getTeam());
    }
}
