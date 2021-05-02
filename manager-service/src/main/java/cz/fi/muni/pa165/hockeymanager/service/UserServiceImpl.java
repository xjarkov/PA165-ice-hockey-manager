package cz.fi.muni.pa165.hockeymanager.service;

import cz.fi.muni.pa165.hockeymanager.dao.UserDao;
import cz.fi.muni.pa165.hockeymanager.entity.User;
import cz.fi.muni.pa165.hockeymanager.exceptions.ManagerServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kristian Kosorin (456620)
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User create(User user) {
        try {
        userDao.create(user);
        } catch(DataAccessException e) {
            throw new ManagerServiceException(String.format(
                    "DataAccessException caught while creating user with message: %s",
                    e.getMessage()
            ));
        }
        return user;
    }

    @Override
    public void remove(User user) {
        try {
            userDao.remove(user);
        } catch(DataAccessException e) {
            throw new ManagerServiceException(String.format(
                    "DataAccessException caught while removing user with message: %s",
                    e.getMessage()
            ));
        }
    }

    @Override
    public void update(User user) {
        try {
            userDao.update(user);
        } catch(DataAccessException e) {
            throw new ManagerServiceException(String.format(
                    "DataAccessException caught while updating user with message: %s",
                    e.getMessage()
            ));
        }
    }

    @Override
    public User findById(Long id) {
        try {
            return userDao.findById(id);
        } catch(DataAccessException e) {
            throw new ManagerServiceException(String.format(
                    "DataAccessException caught while finding user by id: %d, with message: %s",
                    id,
                    e.getMessage()
            ));
        }
    }

    @Override
    public List<User> findAll() {
        try{
            return userDao.findAll();
        }catch(DataAccessException e){
            throw new ManagerServiceException(String.format(
                    "DataAccessException caught while finding all users with message: %s",
                    e.getMessage()
            ));
        }
    }
}
