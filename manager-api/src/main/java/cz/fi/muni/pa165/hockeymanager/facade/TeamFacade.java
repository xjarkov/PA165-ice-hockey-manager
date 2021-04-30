package cz.fi.muni.pa165.hockeymanager.facade;

import cz.fi.muni.pa165.hockeymanager.dto.TeamDto;

import java.util.List;

/**
 * @author Kristian Kosorin (456620)
 */
public interface TeamFacade {
    /**
     * Creates a team.
     *
     * @param team object to create.
     * @return Long ID of object.
     */
    Long create(TeamDto team);

    /**
     * Removes team.
     *
     * @param team object to remove.
     */
    void remove(TeamDto team);

    /**
     * Finds team by ID.
     *
     * @param teamId ID of team to find.
     * @return TeamDto object of found team.
     */
    TeamDto findTeamById(Long teamId);

    /**
     * Finds all teams.
     *
     * @return List of all teams.
     */
    List<TeamDto> findAllTeams();
}
