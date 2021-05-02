package cz.fi.muni.pa165.hockeymanager.service.facade;

import cz.fi.muni.pa165.hockeymanager.dto.UserDto;
import cz.fi.muni.pa165.hockeymanager.entity.Team;
import cz.fi.muni.pa165.hockeymanager.entity.User;
import cz.fi.muni.pa165.hockeymanager.facade.UserFacade;
import cz.fi.muni.pa165.hockeymanager.service.BeanMappingService;
import cz.fi.muni.pa165.hockeymanager.service.TeamService;
import cz.fi.muni.pa165.hockeymanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
    public UserDto findUserById(Long id) {
        User user = userService.findById(id);
        return (user == null) ? null : beanMappingService.mapTo(user, UserDto.class);
    }

    @Override
    public List<UserDto> findAllUsers() {
        return beanMappingService.mapTo(userService.findAll(), UserDto.class);
    }
}
