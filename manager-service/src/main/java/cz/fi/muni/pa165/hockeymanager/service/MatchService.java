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
    Match create(Match match);

    /**
     * Remove match from database.
     *
     * @param match Match to be removed.
     */
    void remove(Match match);

    /**
     * Update match that's already in database.
     *
     * @param match Match to be updated.
     */
    void update(Match match);


    /**
     * Find match in database by it's ID.
     *
     * @param matchId Id of the match.
     * @return Found match.
     */
    Match findById(Long matchId);

    /**
     * Find all matches that were added to database.
     *
     * @return List of all matches.
     */
    List<Match> findAll();

    /**
     * Find nearest match according to date
     * @return Found match
     */
    Match findNearest();
}
