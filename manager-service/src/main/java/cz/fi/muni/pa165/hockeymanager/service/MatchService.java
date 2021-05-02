package cz.fi.muni.pa165.hockeymanager.service;

import cz.fi.muni.pa165.hockeymanager.entity.Match;

import java.util.List;

/**
 * @author Lukas Machalek (485196)
 */
public interface MatchService {

    /**
     * Create match in database.
     *
     * @param match Match to be created in database.
     */
    Match createMatch(Match match);

    /**
     * Remove match from database.
     *
     * @param match Match to be removed.
     */
    void removeMatch(Match match);

    /**
     * Update match that's already in database.
     *
     * @param match Match to be updated.
     */
    void updateMatch(Match match);


    /**
     * Find match in database by it's ID.
     *
     * @param matchId Id of the match.
     * @return Found match.
     */
    Match getById(Long matchId);

    /**
     * Find all matches that were added to database.
     *
     * @return List of all matches.
     */
    List<Match> getAllMatches();

    /**
     * Find nearest match according to date
     * @return Found match
     */
    Match getNearestMatch();
}
