package cz.fi.muni.pa165.hockeymanager.dao;

import cz.fi.muni.pa165.hockeymanager.entity.HumanPlayer;
import java.util.List;

public interface HumanPlayerDao {
    /**
     * Creates HumanPlayer object in database.
     * @param p HumanPlayer to be added into database.
     */
    void create(HumanPlayer p);

    /**
     * Finds all HumanPlayer saved in database.
     * @return List of HumanPlayer.
     */
    List<HumanPlayer> findAll();

    /**
     * Finds HumanPlayer in database by id.
     * @param id of HumanPlayer searched for.
     * @return found HumanPlayer.
     */
    HumanPlayer findById(Long id);

    /**
     * Removes given HumanPlayer from database.
     * @param p HumanPlayer to be removed.
     */
    void remove(HumanPlayer p);
}
