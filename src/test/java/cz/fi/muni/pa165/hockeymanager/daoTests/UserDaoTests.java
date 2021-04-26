package cz.fi.muni.pa165.hockeymanager.daoTests;

import cz.fi.muni.pa165.hockeymanager.PersistenceApplicationContext;
import cz.fi.muni.pa165.hockeymanager.dao.UserDao;
import cz.fi.muni.pa165.hockeymanager.entity.User;
import cz.fi.muni.pa165.hockeymanager.enums.Role;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Kristian Kosorin (456620)
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@Transactional
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class UserDaoTests extends AbstractTestNGSpringContextTests {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserDao UserDao;

    private User user1;
    private User user2;
    private User user3;

    @BeforeMethod
    public void setup() {
        user1 = new User("Jozef Mrkva", "jozef@muni.cz", "password123", Role.PLAYER, null);
        em.persist(user1);

        user2 = new User("Ján Petržlen", "jan@muni.cz", "password321", Role.PLAYER, null);
        em.persist(user2);

        //Not persisted user object.
        user3 = new User("Fero Novák", "fero@muni.cz", "password", Role.ADMIN, null);
    }

    @Test
    public void createUserTest() {
        UserDao.create(user3);
        assertThat(UserDao.findAll().size()).isEqualTo(3);
        assertThat(UserDao.findById(user3.getId())).isEqualTo(user3);
    }

    @Test
    public void removeUserTest() {
        assertThat(UserDao.findById(user1.getId())).isEqualTo(user1);
        UserDao.remove(user1);
        assertThat(UserDao.findById(user1.getId())).isEqualTo(null);
        assertThat(UserDao.findAll().size()).isEqualTo(1);
    }

    @Test
    public void removeNonExistingUserTest() {
        UserDao.remove(user3);
        assertThat(UserDao.findAll().size()).isEqualTo(2);
    }

    @Test
    public void updateUserTest() {
        assertThat(UserDao.findById(user1.getId())).isEqualTo(user1);
        user1.setEmail("marek@muni.cz");
        UserDao.update(user1);
        assertThat(UserDao.findById(user1.getId()).getEmail()).isEqualTo("marek@muni.cz");
        assertThat(UserDao.findById(user1.getId()).getName()).isEqualTo("Jozef Mrkva");
        assertThat(UserDao.findById(user1.getId()).getPassword()).isEqualTo("password123");

        user1.setName("Marek Mrkva");
        UserDao.update(user1);
        assertThat(UserDao.findById(user1.getId()).getEmail()).isEqualTo("marek@muni.cz");
        assertThat(UserDao.findById(user1.getId()).getName()).isEqualTo("Marek Mrkva");
        assertThat(UserDao.findById(user1.getId()).getPassword()).isEqualTo("password123");

        user1.setPassword("321password");
        UserDao.update(user1);
        assertThat(UserDao.findById(user1.getId()).getEmail()).isEqualTo("marek@muni.cz");
        assertThat(UserDao.findById(user1.getId()).getName()).isEqualTo("Marek Mrkva");
        assertThat(UserDao.findById(user1.getId()).getPassword()).isEqualTo("321password");
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void updateNonExistingUserTest() {
        user3.setEmail("marek@muni.cz");
        UserDao.update(user3);
        assertThat(UserDao.findById(user3.getId())).isEqualTo(null);
    }

    @Test
    public void findAllUsersTest() {
        assertThat(UserDao.findAll().size()).isEqualTo(2);
        UserDao.create(user3);
        assertThat(UserDao.findAll().size()).isEqualTo(3);
    }

    @Test
    public void findAllUsersEmptyTest() {
        UserDao.create(user3);
        assertThat(UserDao.findAll().size()).isEqualTo(3);
        UserDao.remove(user1);
        UserDao.remove(user2);
        assertThat(UserDao.findAll().size()).isEqualTo(1);
        UserDao.remove(user3);
        assertThat(UserDao.findAll().size()).isEqualTo(0);
    }

    @Test
    public void findUserByIdTest() {
        assertThat(UserDao.findById(user2.getId())).isEqualTo(user2);
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void findNonExistingUserByIdTest() {
        assertThat(UserDao.findById(user3.getId())).isEqualTo(null);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void nullUserNameExceptionTest() {
        User userErrorName = new User(null, "jozef@muni.cz", "password123", Role.PLAYER, null);
        UserDao.create(userErrorName);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void nullUserEmailExceptionTest() {
        User userErrorMail = new User("Jozef Mrkva", null, "password123", Role.PLAYER, null);
        UserDao.create(userErrorMail);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void nullUserPasswordExceptionTest() {
        User userErrorPassword = new User("Jozef Mrkva", "jozef@muni.cz", null, Role.PLAYER, null);
        UserDao.create(userErrorPassword);
    }
}
