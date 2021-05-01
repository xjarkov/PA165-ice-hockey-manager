package cz.fi.muni.pa165.hockeymanager.service;

import cz.fi.muni.pa165.hockeymanager.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kristian Kosorin (456620)
 */
@Service
public interface UserService {

    /**
     * Creates a user.
     *
     * @param user object to create.
     * @return created User object.
     */
    User create(User user);

    /**
     * Updates a user.
     *
     * @param user object to update.
     */
    void update(User user);

    /**
     * Removes a user.
     *
     * @param user object to be removed.
     */
    void remove(User user);

    /**
     * Finds all users.
     *
     * @return List of all users.
     */
    List<User> findAll();

    /**
     * Finds user by its ID.
     *
     * @param id of the user.
     * @return User object.
     */
    User findById(Long id);
}
