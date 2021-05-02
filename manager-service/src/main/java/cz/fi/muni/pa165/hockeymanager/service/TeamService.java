package cz.fi.muni.pa165.hockeymanager.service;

import cz.fi.muni.pa165.hockeymanager.entity.HockeyPlayer;
import cz.fi.muni.pa165.hockeymanager.entity.Match;
import cz.fi.muni.pa165.hockeymanager.entity.Team;

import java.util.List;

/**
 * @author Matus Jarkovic 456441
 */
public interface TeamService {
    /**
     * Creates a team
     * @param team to create
     */
    void create(Team team);

    /**
     * Removes a team
     * @param team to remove
     */
    void remove(Team team);

    /**
     * Returns team by name
     * @param name of the team
     */
    Team findByName(String name);

    /**
     * Returns team by id
     * @param id of the team
     */
    Team findById(Long id);

    /**
     * Find and return all teams
     */
    List<Team> findAll();

    /**
     * Adds the player to the team
     * @param team to add the player to
     * @param player to add to the team
     */
    void addPlayer(Team team, HockeyPlayer player);

    /**
     * Removes the player from the team
     * @param team to remove the player from
     * @param player to remove from the team
     */
    void removePlayer(Team team, HockeyPlayer player);

    /**
     * Adds the match to the team
     * @param team to add the match to
     * @param match to add to the team
     */
    void addMatch(Team team, Match match);

    /**
     * Removes the next match that team is participating in (according to date)
     * @param team to remove the match from
     */
    void removeTheNearestMatch(Team team);
}
