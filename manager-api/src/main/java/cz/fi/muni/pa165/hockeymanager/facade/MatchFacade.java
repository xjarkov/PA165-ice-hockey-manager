package cz.fi.muni.pa165.hockeymanager.facade;

import cz.fi.muni.pa165.hockeymanager.dto.MatchCreateDto;
import cz.fi.muni.pa165.hockeymanager.dto.MatchDto;
import cz.fi.muni.pa165.hockeymanager.dto.TeamDto;
import cz.fi.muni.pa165.hockeymanager.utils.ScoreTuple;

import java.util.List;

/**
 * @author Matus Jarkovic 456441
 */
public interface MatchFacade {
    /**
     * Creates a match
     *
     * @param match to create
     */
    Long create(MatchCreateDto match);

    /**
     * Removes a match
     *
     * @param match of the match to remove
     */
    void remove(MatchDto match);

    /**
     * Updates a match
     *
     * @param match of the match to update
     */
    void update(MatchDto match);

    /**
     * Finds match by id
     *
     * @param id of the match
     * @return match
     */
    MatchDto findMatchById(Long id);

    /**
     * Finds all matches
     *
     * @return all matches
     */
    List<MatchDto> findAllMatches();

    /**
     * Finds nearest match by date
     *
     * @return nearest match
     */
    MatchDto findNearestMatch();

    /**
     * Gets score of a match
     *
     * @return score tuple of match
     */
    ScoreTuple getScoreOfMatch(Long matchId);

    /**
     * Gets the winning team of the match
     * @param matchId of the match
     * @return winning team or null if draw
     */
    TeamDto getWinningTeam(Long matchId);
}
