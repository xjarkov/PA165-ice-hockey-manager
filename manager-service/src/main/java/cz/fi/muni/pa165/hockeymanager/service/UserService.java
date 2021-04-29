package cz.fi.muni.pa165.hockeymanager.service;

import cz.fi.muni.pa165.hockeymanager.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void create(User user);
    void update(User user);
    void remove(User user);

    List<User> findAll();
    User findById(Long id);
}
