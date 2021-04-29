package cz.fi.muni.pa165.hockeymanager.service;

import cz.fi.muni.pa165.hockeymanager.entity.User;

import java.util.List;

/**
 * @author Kristian Kosorin (456620)
 */
public interface UserService {
    void create(User user);
    void update(User user);
    void remove(User user);

    List<User> findAll();
    User findById(Long id);
}
