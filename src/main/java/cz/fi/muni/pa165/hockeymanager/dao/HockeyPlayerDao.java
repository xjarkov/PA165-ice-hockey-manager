package cz.fi.muni.pa165.hockeymanager.dao;

import cz.fi.muni.pa165.hockeymanager.entity.HockeyPlayer;

import java.util.List;

/**
 * @author Petr Šopf (506511)
 */
public interface HockeyPlayerDao {
    /**
     * Creates new player
     *
     * @param hockeyPlayer Player entity
     */
    void create(HockeyPlayer hockeyPlayer);

    /**
     * Removes player
     *
     * @param hockeyPlayer to remove
     */
    void remove(HockeyPlayer hockeyPlayer);

    /**
     * @return List of all players
     */
    List<HockeyPlayer> findAll();

    /**
     * @return Player by its id
     */
    HockeyPlayer findById(Long id);
}
