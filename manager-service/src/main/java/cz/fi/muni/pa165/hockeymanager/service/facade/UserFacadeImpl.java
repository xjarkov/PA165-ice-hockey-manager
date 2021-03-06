package cz.fi.muni.pa165.hockeymanager.service.facade;

import cz.fi.muni.pa165.hockeymanager.dto.UserDto;
import cz.fi.muni.pa165.hockeymanager.entity.Team;
import cz.fi.muni.pa165.hockeymanager.entity.User;
import cz.fi.muni.pa165.hockeymanager.facade.UserFacade;
import cz.fi.muni.pa165.hockeymanager.service.BeanMappingService;
import cz.fi.muni.pa165.hockeymanager.service.UserService;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Petr Šopf (506511)
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade {
    @Autowired
    UserService userService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long create(UserDto user) {
        User mappedUser = beanMappingService.mapTo(user, User.class);

        mappedUser.setName(user.getName());
        mappedUser.setEmail(user.getEmail());
        mappedUser.setPassword(user.getPassword());
        mappedUser.setRole(user.getRole());
        mappedUser.setTeam(beanMappingService.mapTo(user.getTeam(), Team.class));
        User newUser = userService.create(mappedUser);
        return newUser.getId();
    }

    @Override
    public void remove(UserDto user) {
        userService.remove(beanMappingService.mapTo(user, User.class));
    }

    @Override
    public void update(UserDto user) {
        userService.update(beanMappingService.mapTo(user, User.class));
    }

    @Override
    public UserDto findUserById(Long id) {
        User user = userService.findById(id);
        return (user == null) ? null : beanMappingService.mapTo(user, UserDto.class);
    }

    @Override
    public UserDto findUserByEmail(String email) {
        User user = userService.findByEmail(email);
        return (user == null) ? null : beanMappingService.mapTo(user, UserDto.class);
    }

    @Override
    public List<UserDto> findAllUsers() {
        return beanMappingService.mapTo(userService.findAll(), UserDto.class);
    }

    @Override
    public boolean authenticate(UserDto user) {
        return userService.authenticate(userService.findByEmail(user.getEmail()), user.getPassword());
    }
}
