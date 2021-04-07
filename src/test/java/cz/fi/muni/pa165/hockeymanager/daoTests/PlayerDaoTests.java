package cz.fi.muni.pa165.hockeymanager.daoTests;

import cz.fi.muni.pa165.hockeymanager.PersistenceApplicationContext;
import cz.fi.muni.pa165.hockeymanager.dao.PlayerDao;
import cz.fi.muni.pa165.hockeymanager.entity.Player;
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
public class PlayerDaoTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private PlayerDao playerDao;

    @Test
    public void createPlayerTest() {
        Team team = new Team("CSKA Moskva", Championship.KHL);
        Player player = new Player("Sergey", "Andronov", 90, 85, team);

        playerDao.create(player);
        assertThat(playerDao.findById(player.getId())).isEqualTo(player);
    }

    @Test
    public void removePlayerTest() {
        Team team = new Team("CSKA Moskva", Championship.KHL);
        Player player = new Player("Sergey", "Andronov", 90, 85, team);

        playerDao.create(player);
        assertThat(playerDao.findById(player.getId())).isEqualTo(player);
        playerDao.remove(player);
        assertThat(playerDao.findById(player.getId())).isEqualTo(null);
    }

    @Test
    public void findAllPlayersTest() {
        Team team = new Team("CSKA Moskva", Championship.KHL);
        Player player1 = new Player("Sergey", "Andronov", 90, 85, team);
        Player player2 = new Player("Vsevolod", "Skotnikov", 15, 95, team);

        assertThat(playerDao.findAll().size()).isEqualTo(0);
        playerDao.create(player1);
        playerDao.create(player2);
        assertThat(playerDao.findAll().size()).isEqualTo(2);
    }

    @Test
    public void findPlayerByIdTest() {
        Team team = new Team("CSKA Moskva", Championship.KHL);
        Player player = new Player("Sergey", "Andronov", 90, 85, team);

        playerDao.create(player);
        assertThat(playerDao.findById(player.getId())).isEqualTo(player);
    }

//    @Test(expectedExceptions = ConstraintViolationException.class)
//    public void createPlayerWithNullName() {
//        Team team = new Team("CSKA Moskva", Championship.KHL);
//        Player player = new Player(null, "Andronov", 90, 85, team);
//
//        playerDao.create(player);
//    }
//
//    @Test(expectedExceptions = ConstraintViolationException.class)
//    public void createPlayerWithNullSurname() {
//        Team team = new Team("CSKA Moskva", Championship.KHL);
//        Player player = new Player("Sergey", null, 90, 85, team);
//
//        playerDao.create(player);
//    }
}
