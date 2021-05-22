package cz.fi.muni.pa165.hockeymanager.facade;

import cz.fi.muni.pa165.hockeymanager.dto.UserDto;

import java.util.List;

/**
 * @author Petr Šopf (506511)
 */
public interface UserFacade {
    /**
     * Creates an user
     *
     * @param user to create
     */
    Long create(UserDto user);

    /**
     * Removes an user
     *
     * @param user to be removed
     */
    void remove(UserDto user);

    /**
     * Updates an user
     *
     * @param user to be updated
     */
    void update(UserDto user);

    /**
     * Finds user by ID
     *
     * @param id of the user
     * @return User
     */
    UserDto findUserById(Long id);

    /**
     * Finds all users
     *
     * @return all users
     */
    List<UserDto> findAllUsers();
}
