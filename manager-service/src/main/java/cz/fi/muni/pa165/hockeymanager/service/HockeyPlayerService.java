package cz.fi.muni.pa165.hockeymanager.service;

import cz.fi.muni.pa165.hockeymanager.entity.HockeyPlayer;
import cz.fi.muni.pa165.hockeymanager.entity.Match;

import java.util.List;

/**
 * @author Petr Šopf (506511)
 */
public interface HockeyPlayerService {
    /**
     * Creates new hockey player.
     *
     * @param player hockey player entity.
     */
    HockeyPlayer create(HockeyPlayer player);

    /**
     * Removes given hockey player.
     *
     * @param player hockey player entity.
     */
    void remove(HockeyPlayer player);

    /**
     * Updates given hockey player.
     *
     * @param player hockey player entity.
     */
    void update(HockeyPlayer player);

    /**
     * Returns hockey player by its ID.
     *
     * @param id hockey player Id.
     * @return a player.
     */
    HockeyPlayer findById(Long id);

    /**
     * Returns all hockey players.
     *
     * @return list of all hockey players.
     */
    List<HockeyPlayer> findAll();

    /**
     * Returns all matches of player's team.
     *
     * @return list of all player's team matches.
     */
    List<Match> findPlayerTeamMatches(HockeyPlayer player);
}
