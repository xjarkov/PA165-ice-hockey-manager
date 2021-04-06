package cz.fi.muni.pa165.hockeymanager.dao;

import cz.fi.muni.pa165.hockeymanager.entity.Player;

import java.util.List;

/**
 * @author Petr Šopf (506511)
 */
public interface PlayerDao {
    /**
     * Creates new player
     * @param player Player entity
     */
    void create(Player player);

    /**
     * Removes player by ID
     * @param playerId Player ID
     */
    void remove(Long playerId);

    /**
     * @return List of all players
     */
    List<Player> findAll();

    /**
     * @return Player by it's id
     */
    Player findById(Long id);
}
