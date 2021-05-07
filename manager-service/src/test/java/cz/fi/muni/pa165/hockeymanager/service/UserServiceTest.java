package cz.fi.muni.pa165.hockeymanager.service;

import cz.fi.muni.pa165.hockeymanager.dao.UserDao;
import cz.fi.muni.pa165.hockeymanager.entity.User;
import cz.fi.muni.pa165.hockeymanager.enums.Role;
import cz.fi.muni.pa165.hockeymanager.exceptions.ManagerServiceException;
import cz.fi.muni.pa165.hockeymanager.service.config.ServiceConfiguration;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Matus Jarkovic 456441
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class UserServiceTest extends AbstractTestNGSpringContextTests {
    @Mock
    private UserDao userDao;

    @Autowired
    @InjectMocks
    private UserService userService;

    final private User user1 = new User("user1", "user1@muni.cz", "user1pwd", Role.PLAYER);
    final private User user2 = new User("user2", "user2@muni.cz", "user2pwd", Role.PLAYER);

    final private List<User> userList = List.of(user1, user2);

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createUserTest() {
        userService.create(user1);
        verify(userDao).create(user1);
    }

    @Test
    public void removeUserTest() {
        userService.remove(user1);
        verify(userDao).remove(user1);
    }

    @Test
    public void removeNullUserTest() {
        when(userDao.findById(user1.getId())).thenReturn(null);
        doNothing().when(userDao).remove(user1);
        userService.remove(user1);
        verify(userDao).remove(user1);
    }

    @Test
    public void updateUserTest() {
        userService.update(user1);
        verify(userDao).update(user1);
    }

    @Test
    public void findUserByIdTest() {
        when(userDao.findById(69L)).thenReturn(user1);
        User user = userService.findById(69L);
        verify(userDao).findById(69L);
        assertThat(user).isEqualTo(user1);
    }

    @Test
    public void findAllUsersTest() {
        when(userDao.findAll()).thenReturn(userList);
        List<User> users = userService.findAll();
        verify(userDao).findAll();
        assertThat(users).isEqualTo(userList);
        assertThat(users).hasSize(2);
    }

    @Test(expectedExceptions = ManagerServiceException.class)
    public void exceptionThrowTest() {
        doThrow(new DuplicateKeyException("error")).when(userDao).create(user1);
        userService.create(user1);
    }
}
