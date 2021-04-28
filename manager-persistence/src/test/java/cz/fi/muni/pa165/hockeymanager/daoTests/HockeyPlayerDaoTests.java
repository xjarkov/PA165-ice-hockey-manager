package cz.fi.muni.pa165.hockeymanager.daoTests;

import cz.fi.muni.pa165.hockeymanager.PersistenceApplicationContext;
import cz.fi.muni.pa165.hockeymanager.dao.HockeyPlayerDao;
import cz.fi.muni.pa165.hockeymanager.entity.HockeyPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Matus Jarkovic (456441)
 */

@ContextConfiguration(classes = {PersistenceApplicationContext.class})
@Transactional
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class HockeyPlayerDaoTests extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private HockeyPlayerDao hockeyPlayerDao;

    private HockeyPlayer hockeyPlayer1;
    private HockeyPlayer hockeyPlayer2;
    private HockeyPlayer hockeyPlayer3;

    @BeforeMethod
    public void setup() {
        hockeyPlayer1 = new HockeyPlayer("Sergey", "Andronov", 90, 85);
        em.persist(hockeyPlayer1);

        hockeyPlayer2 = new HockeyPlayer("Vsevolod", "Skotnikov", 15, 95);
        em.persist(hockeyPlayer2);

        // not persisting intentionally
        hockeyPlayer3 = new HockeyPlayer("Nikita", "Soshnikov", 84, 73);
    }

    @Test
    public void createPlayerTest() {
        hockeyPlayerDao.create(hockeyPlayer3);
        assertThat(hockeyPlayerDao.findById(hockeyPlayer3.getId())).isEqualTo(hockeyPlayer3);
        assertThat(hockeyPlayerDao.findAll().size()).isEqualTo(3);
    }

    @Test
    public void removePlayerTest() {
        hockeyPlayerDao.remove(hockeyPlayer1);
        assertThat(hockeyPlayerDao.findById(hockeyPlayer1.getId())).isEqualTo(null);
        assertThat(hockeyPlayerDao.findAll().size()).isEqualTo(1);
    }

    @Test
    public void findAllPlayersTest() {
        assertThat(hockeyPlayerDao.findAll().size()).isEqualTo(2);
    }

    @Test
    public void findPlayerByIdTest() {
        assertThat(hockeyPlayerDao.findById(hockeyPlayer2.getId())).isEqualTo(hockeyPlayer2);
    }

    @Test
    void updateExistingPlayerTest() {
        hockeyPlayer1.setFirstName("Voloda");
        hockeyPlayerDao.update(hockeyPlayer1);
        assertThat(hockeyPlayerDao.findById(hockeyPlayer1.getId()).getFirstName()).isEqualTo("Voloda");
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    void updateNotExistingPlayerTest() {
        hockeyPlayer3.setFirstName("Josif");
        hockeyPlayerDao.update(hockeyPlayer3);
        assertThat(hockeyPlayerDao.findById(hockeyPlayer3.getId())).isEqualTo(null);
    }

    @Test
    public void createExistingPlayerTest() {
        hockeyPlayerDao.create(hockeyPlayer1);
        assertThat(hockeyPlayerDao.findAll().size()).isEqualTo(2);
    }

    @Test
    public void removeNotExistingPlayerTest() {
        hockeyPlayerDao.remove(hockeyPlayer3);
        assertThat(hockeyPlayerDao.findAll().size()).isEqualTo(2);
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void findNotExistingPlayerByIdTest() {
        assertThat(hockeyPlayerDao.findById(hockeyPlayer3.getId())).isEqualTo(null);
    }

    @Test
    public void findAllEmptyList() {
        hockeyPlayerDao.remove(hockeyPlayer1);
        hockeyPlayerDao.remove(hockeyPlayer2);
        assertThat(hockeyPlayerDao.findAll().size()).isEqualTo(0);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createPlayerWithNullName() {
        HockeyPlayer hockeyPlayer = new HockeyPlayer(null, "Andronov", 50, 50);
        hockeyPlayerDao.create(hockeyPlayer);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createPlayerWithNullSurname() {
        HockeyPlayer hockeyPlayer = new HockeyPlayer("Sergey", null, 90, 85);
        hockeyPlayerDao.create(hockeyPlayer);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createPlayerWithNullOffense() {
        HockeyPlayer hockeyPlayer = new HockeyPlayer("Sergey", "Andronov", null, 85);
        hockeyPlayerDao.create(hockeyPlayer);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createPlayerWithNullDefence() {
        HockeyPlayer hockeyPlayer = new HockeyPlayer("Sergey", "Andronov", 90, null);
        hockeyPlayerDao.create(hockeyPlayer);
    }
}
