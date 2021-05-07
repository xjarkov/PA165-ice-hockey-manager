package cz.fi.muni.pa165.hockeymanager.dao;

import cz.fi.muni.pa165.hockeymanager.entity.User;

import java.util.List;

/**
 * @author Lukas Machalek (485196)
 */
public interface UserDao {
    /**
     * Creates User object in database.
     *
     * @param user User to be added into database.
     */
    void create(User user);

    /**
     * Finds all Users saved in database.
     *
     * @return List of Users.
     */
    List<User> findAll();

    /**
     * Finds User in database by id.
     *
     * @param id of User searched for.
     * @return found User.
     */
    User findById(Long id);

    /**
     * Finds User in database by name.
     *
     * @param name of User searched for.
     * @return found User.
     */
    User findByName(String name);

    /**
     * Finds User in database by email.
     *
     * @param email of User searched for.
     * @return found User.
     */
    User findByEmail(String email);

    /**
     * Updates User object.
     *
     * @param user updated User object.
     */
    void update(User user);

    /**
     * Removes given User from database.
     *
     * @param user User to be removed.
     */
    void remove(User user);
}
