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
    public Long create(UserDto user);

    /**
     * Removes an user
     *
     * @param id of the user to remove
     */
    public void remove(Long id);

    /**
     * Finds user by ID
     *
     * @param id of the user
     * @return User
     */
    public UserDto getMatchById(Long id);

    /**
     * Finds all users
     *
     * @return all users
     */
    public List<UserDto> getAllUsers();
}
