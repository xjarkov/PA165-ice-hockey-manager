package cz.fi.muni.pa165.hockeymanager.facade;

import cz.fi.muni.pa165.hockeymanager.dto.MatchDto;
import cz.fi.muni.pa165.hockeymanager.utils.ScoreTuple;

import java.util.List;

/**
 * @author Matus Jarkovic 456441
 */
public interface MatchFacade {
    /**
     * Creates a match
     * @param match to create
     */
    public Long create(MatchDto match);

    /**
     * Removes a match
     * @param matchId of the match to remove
     */
    public void remove(Long matchId);

    /**
     * Finds match by id
     * @param id of the match
     * @return match
     */
    public MatchDto findMatchById(Long id);

    /**
     * Finds all matches
     * @return all matches
     */
    public List<MatchDto> findAllMatches();

    /**
     * Finds nearest match by date
     * @return nearest match
     */
    public MatchDto findNearestMatch();

    /**
     * Gets score of a match
     * @return
     */
    public ScoreTuple getScoreOfMatch(long matchId);
}
