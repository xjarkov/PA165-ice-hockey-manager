package cz.fi.muni.pa165.hockeymanager.dao;

import cz.fi.muni.pa165.hockeymanager.entity.User;
import java.util.List;

/**
 * @author Lukas Machalek (485196)
 */
public interface UserDao {
    /**
     * Creates HumanPlayer object in database.
     *
     * @param user HumanPlayer to be added into database.
     */
    void create(User user);

    /**
     * Finds all HumanPlayer saved in database.
     *
     * @return List of HumanPlayer.
     */
    List<User> findAll();

    /**
     * Finds HumanPlayer in database by id.
     *
     * @param id of HumanPlayer searched for.
     * @return found HumanPlayer.
     */
    User findById(Long id);

    /**
     * Removes given HumanPlayer from database.
     *
     * @param player HumanPlayer to be removed.
     */
    void remove(User user);
}
