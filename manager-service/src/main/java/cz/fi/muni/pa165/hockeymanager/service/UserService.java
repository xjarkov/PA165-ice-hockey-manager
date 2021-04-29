package cz.fi.muni.pa165.hockeymanager.service;

import cz.fi.muni.pa165.hockeymanager.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kristian Kosorin (456620)
 */
@Service
public interface UserService {
    User create(User user);
    void update(User user);
    void remove(User user);

    List<User> findAll();
    User findById(Long id);
}
