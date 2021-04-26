package cz.fi.muni.pa165.hockeymanager.daoTests;

import cz.fi.muni.pa165.hockeymanager.PersistenceApplicationContext;
import cz.fi.muni.pa165.hockeymanager.dao.UserDao;
import cz.fi.muni.pa165.hockeymanager.entity.User;
import cz.fi.muni.pa165.hockeymanager.enums.Role;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Kristian Kosorin (456620)
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@Transactional
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class UserDaoTests extends AbstractTestNGSpringContextTests {
    @Autowired
    private UserDao UserDao;
    private User user1;
    private User user2;
    private User user3;

    @BeforeMethod
    public void setup() {
        user1 = new User("Jozef Mrkva", "jozef@muni.cz", "password123", Role.PLAYER, null);
        user2 = new User("Ján Petržlen", "jan@muni.cz", "password321", Role.PLAYER, null);
        user3 = new User("Fero Novák", "fero@muni.cz", "password", Role.ADMIN, null);
    }

    @Test
    public void createHumanPlayerTest() {
        UserDao.create(user1);
        assertThat(UserDao.findById(user1.getId())).isEqualTo(user1);
    }

    @Test
    public void findAllHumanPlayersTest() {
        assertThat(UserDao.findAll().size()).isEqualTo(0);
        UserDao.create(user1);
        UserDao.create(user2);
        assertThat(UserDao.findAll().size()).isEqualTo(2);
        UserDao.create(user3);
        assertThat(UserDao.findAll().size()).isEqualTo(3);
    }

    @Test
    public void findHumanPlayerByIdTest() {
        UserDao.create(user1);
        UserDao.create(user2);
        UserDao.create(user3);

        assertThat(UserDao.findById(user2.getId())).isEqualTo(user2);
    }

    @Test
    public void removeHumanPlayerTest() {
        UserDao.create(user1);
        assertThat(UserDao.findById(user1.getId())).isEqualTo(user1);
        UserDao.remove(user1);
        assertThat(UserDao.findById(user1.getId())).isEqualTo(null);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void nullNameExceptionTest() {
        User userErrorName = new User(null, "jozef@muni.cz", "password123", Role.PLAYER, null);
        UserDao.create(userErrorName);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void nullEmailExceptionTest() {
        User userErrorMail = new User("Jozef Mrkva", null, "password123", Role.PLAYER, null);
        UserDao.create(userErrorMail);
    }
}
