package cz.fi.muni.pa165.hockeymanager.facade;

import cz.fi.muni.pa165.hockeymanager.dto.UserDto;
import cz.fi.muni.pa165.hockeymanager.entity.User;
import cz.fi.muni.pa165.hockeymanager.enums.Role;
import cz.fi.muni.pa165.hockeymanager.service.BeanMappingService;
import cz.fi.muni.pa165.hockeymanager.service.TeamService;
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

//    @Autowired
    @InjectMocks
    private UserFacadeImpl userFacade = new UserFacadeImpl();

    private User user1;
    private User user2;
    private UserDto userDto1;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.openMocks(this);

        user1 = new User("user1", "user1@muni.cz", "user1pwd", Role.PLAYER);
        userDto1 = new UserDto("user1", "user1@muni.cz", "user1pwd", Role.PLAYER);
    }

    @Test
    public void testCreateUser() {
        when(beanMappingService.mapTo(userDto1, User.class)).thenReturn(user1);
        when(userService.create(user1)).thenReturn(user1);
        assertThat(userFacade.create(userDto1)).isEqualTo(user1.getId());
    }
}