package cz.fi.muni.pa165.hockeymanager.facade;

import cz.fi.muni.pa165.hockeymanager.dto.UserDto;
import cz.fi.muni.pa165.hockeymanager.entity.User;
import cz.fi.muni.pa165.hockeymanager.enums.Role;
import cz.fi.muni.pa165.hockeymanager.service.BeanMappingService;
import cz.fi.muni.pa165.hockeymanager.service.UserService;
import cz.fi.muni.pa165.hockeymanager.service.config.ServiceConfiguration;
import cz.fi.muni.pa165.hockeymanager.service.facade.UserFacadeImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Matus Jarkovic 456441
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class UserFacadeTest extends AbstractTransactionalTestNGSpringContextTests {
    @Mock
    private UserService userService;

    @Mock
    private BeanMappingService beanMappingService;

    @InjectMocks
    private UserFacadeImpl userFacade = new UserFacadeImpl();

    final private User user1 = new User("user1", "user1@muni.cz", "user1pwd", Role.PLAYER);
    final private UserDto userDto1 = new UserDto("user1", "user1@muni.cz", "user1pwd", Role.PLAYER);

    final private List<User> userList = List.of(user1);
    final private List<UserDto> userDtoList = List.of(userDto1);

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createUserTest() {
        when(beanMappingService.mapTo(userDto1, User.class)).thenReturn(user1);
        when(userService.create(user1)).thenReturn(user1);
        assertThat(userFacade.create(userDto1)).isEqualTo(user1.getId());
    }

    @Test
    public void removeUserTest() {
        when(beanMappingService.mapTo(userDto1, User.class)).thenReturn(user1);
        userFacade.remove(userDto1);
        verify(userService).remove(user1);
    }

    @Test
    public void getAllUsersTest() {
        when(userService.findAll()).thenReturn(userList);
        when(beanMappingService.mapTo(userList, UserDto.class)).thenReturn(userDtoList);
        assertThat(userFacade.findAllUsers()).isEqualTo(userDtoList);
    }

    @Test
    public void getUserByIdTest() {
        when(userService.findById(user1.getId())).thenReturn(user1);
        when(beanMappingService.mapTo(user1, UserDto.class)).thenReturn(userDto1);
        assertThat(userFacade.findUserById(user1.getId())).isEqualTo(userDto1);
    }
}