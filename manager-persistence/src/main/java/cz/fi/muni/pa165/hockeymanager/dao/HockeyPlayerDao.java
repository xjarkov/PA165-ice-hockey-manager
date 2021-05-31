package cz.fi.muni.pa165.hockeymanager.dao;

import cz.fi.muni.pa165.hockeymanager.entity.HockeyPlayer;

import java.util.List;

/**
 * @author Petr Šopf (506511)
 */
public interface HockeyPlayerDao {
    /**
     * Creates new player.
     *
     * @param hockeyPlayer Player entity.
     */
    void create(HockeyPlayer hockeyPlayer);

    /**
     * Removes player.
     *
     * @param hockeyPlayer to remove.
     */
    void remove(HockeyPlayer hockeyPlayer);

    /**
     * Updates entity with the same ID in the table.
     *
     * @param hockeyPlayer to update.
     */
    void update(HockeyPlayer hockeyPlayer);

    /**
     * @return List of all players.
     */
    List<HockeyPlayer> findAll();

    /**
     * @return Player by its id.
     */
    HockeyPlayer findById(Long id);

    /**
     * @return List of players without team
     */
    List<HockeyPlayer> findPlayersWithoutTeam();
}
