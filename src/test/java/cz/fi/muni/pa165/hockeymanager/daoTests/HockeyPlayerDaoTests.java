package cz.fi.muni.pa165.hockeymanager.daoTests;

import cz.fi.muni.pa165.hockeymanager.PersistenceApplicationContext;
import cz.fi.muni.pa165.hockeymanager.dao.HockeyPlayerDao;
import cz.fi.muni.pa165.hockeymanager.entity.HockeyPlayer;
import cz.fi.muni.pa165.hockeymanager.entity.Team;
import cz.fi.muni.pa165.hockeymanager.enums.Championship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolationException;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Matus Jarkovic (456441)
 */

@ContextConfiguration(classes = {PersistenceApplicationContext.class})
@Transactional
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class HockeyPlayerDaoTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private HockeyPlayerDao hockeyPlayerDao;

    @Test
    public void createPlayerTest() {
        Team team = new Team("CSKA Moskva", Championship.KHL);
        HockeyPlayer hockeyPlayer = new HockeyPlayer("Sergey", "Andronov", 90, 85, team);

        hockeyPlayerDao.create(hockeyPlayer);
        assertThat(hockeyPlayerDao.findById(hockeyPlayer.getId())).isEqualTo(hockeyPlayer);
    }

    @Test
    public void removePlayerTest() {
        Team team = new Team("CSKA Moskva", Championship.KHL);
        HockeyPlayer hockeyPlayer = new HockeyPlayer("Sergey", "Andronov", 90, 85, team);

        hockeyPlayerDao.create(hockeyPlayer);
        assertThat(hockeyPlayerDao.findById(hockeyPlayer.getId())).isEqualTo(hockeyPlayer);
        hockeyPlayerDao.remove(hockeyPlayer);
        assertThat(hockeyPlayerDao.findById(hockeyPlayer.getId())).isEqualTo(null);
    }

    @Test
    public void findAllPlayersTest() {
        Team team = new Team("CSKA Moskva", Championship.KHL);
        HockeyPlayer hockeyPlayer1 = new HockeyPlayer("Sergey", "Andronov", 90, 85, team);
        HockeyPlayer hockeyPlayer2 = new HockeyPlayer("Vsevolod", "Skotnikov", 15, 95, team);

        assertThat(hockeyPlayerDao.findAll().size()).isEqualTo(0);
        hockeyPlayerDao.create(hockeyPlayer1);
        hockeyPlayerDao.create(hockeyPlayer2);
        assertThat(hockeyPlayerDao.findAll().size()).isEqualTo(2);
    }

    @Test
    public void findPlayerByIdTest() {
        Team team = new Team("CSKA Moskva", Championship.KHL);
        HockeyPlayer hockeyPlayer = new HockeyPlayer("Sergey", "Andronov", 90, 85, team);

        hockeyPlayerDao.create(hockeyPlayer);
        assertThat(hockeyPlayerDao.findById(hockeyPlayer.getId())).isEqualTo(hockeyPlayer);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createPlayerWithNullName() {
        Team team = new Team("CSKA Moskva", Championship.KHL);
        HockeyPlayer hockeyPlayer = new HockeyPlayer(null, "Andronov", 90, 85, team);

        hockeyPlayerDao.create(hockeyPlayer);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createPlayerWithNullSurname() {
        Team team = new Team("CSKA Moskva", Championship.KHL);
        HockeyPlayer hockeyPlayer = new HockeyPlayer("Sergey", null, 90, 85, team);

        hockeyPlayerDao.create(hockeyPlayer);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createPlayerWithNullOffense() {
        Team team = new Team("CSKA Moskva", Championship.KHL);
        HockeyPlayer hockeyPlayer = new HockeyPlayer("Sergey", "Andronov", null, 85, team);

        hockeyPlayerDao.create(hockeyPlayer);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createPlayerWithNullDefence() {
        Team team = new Team("CSKA Moskva", Championship.KHL);
        HockeyPlayer hockeyPlayer = new HockeyPlayer("Sergey", "Andronov", 90, null, team);

        hockeyPlayerDao.create(hockeyPlayer);
    }
}
