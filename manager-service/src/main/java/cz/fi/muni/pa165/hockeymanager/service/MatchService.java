package cz.fi.muni.pa165.hockeymanager.service;

import cz.fi.muni.pa165.hockeymanager.entity.Match;
import java.util.List;

public interface MatchService {

    void createMatch(Match match);
    void removeMatch(Match match);

    Match getById(Long matchId);

    List<Match> getAllMatches();
}
