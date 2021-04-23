package cz.fi.muni.pa165.hockeymanager.daoTests;

import cz.fi.muni.pa165.hockeymanager.PersistenceApplicationContext;
import cz.fi.muni.pa165.hockeymanager.dao.HumanPlayerDao;
import cz.fi.muni.pa165.hockeymanager.entity.HumanPlayer;
import cz.fi.muni.pa165.hockeymanager.enums.Role;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeClass;
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
public class HumanPlayerDaoTests extends AbstractTestNGSpringContextTests {
    @Autowired
    private HumanPlayerDao HumanPlayerDao;

    @BeforeMethod
    public void setup() {
        HumanPlayer humanPlayer1 = new HumanPlayer("Jozef Mrkva", "jozef@muni.cz", "password123", Role.PLAYER, null);
        HumanPlayer humanPlayer2 = new HumanPlayer("Ján Petržlen", "jan@muni.cz", "password321", Role.PLAYER, null);
        HumanPlayer humanPlayer3 = new HumanPlayer("Fero Novák", "fero@muni.cz", "password", Role.ADMIN, null);
    }

    @Test
    public void createHumanPlayerTest() {
        HumanPlayer humanPlayer = new HumanPlayer("Jozef Mrkva", "jozef@muni.cz", "password123", Role.PLAYER, null);

        HumanPlayerDao.create(humanPlayer);
        assertThat(HumanPlayerDao.findById(humanPlayer.getId())).isEqualTo(humanPlayer);
    }

    @Test
    public void findAllHumanPlayersTest() {
        HumanPlayer humanPlayer1 = new HumanPlayer("Jozef Mrkva", "jozef@muni.cz", "password123", Role.PLAYER, null);
        HumanPlayer humanPlayer2 = new HumanPlayer("Ján Petržlen", "jan@muni.cz", "password321", Role.PLAYER, null);
        HumanPlayer humanPlayer3 = new HumanPlayer("Fero Novák", "fero@muni.cz", "password", Role.ADMIN, null);

        assertThat(HumanPlayerDao.findAll().size()).isEqualTo(0);
        HumanPlayerDao.create(humanPlayer1);
        HumanPlayerDao.create(humanPlayer2);
        assertThat(HumanPlayerDao.findAll().size()).isEqualTo(2);
        HumanPlayerDao.create(humanPlayer3);
        assertThat(HumanPlayerDao.findAll().size()).isEqualTo(3);
    }

    @Test
    public void findHumanPlayerByIdTest() {
        HumanPlayer humanPlayer1 = new HumanPlayer("Jozef Mrkva", "jozef@muni.cz", "password123", Role.PLAYER, null);
        HumanPlayer humanPlayer2 = new HumanPlayer("Ján Petržlen", "jan@muni.cz", "password321", Role.PLAYER, null);
        HumanPlayer humanPlayer3 = new HumanPlayer("Fero Novák", "fero@muni.cz", "password", Role.ADMIN, null);

        HumanPlayerDao.create(humanPlayer1);
        HumanPlayerDao.create(humanPlayer2);
        HumanPlayerDao.create(humanPlayer3);

        assertThat(HumanPlayerDao.findById(humanPlayer2.getId())).isEqualTo(humanPlayer2);
    }

    @Test
    public void removeHumanPlayerTest() {
        HumanPlayer humanPlayer1 = new HumanPlayer("Jozef Mrkva", "jozef@muni.cz", "password123", Role.PLAYER, null);

        HumanPlayerDao.create(humanPlayer1);
        assertThat(HumanPlayerDao.findById(humanPlayer1.getId())).isEqualTo(humanPlayer1);
        HumanPlayerDao.remove(humanPlayer1);
        assertThat(HumanPlayerDao.findById(humanPlayer1.getId())).isEqualTo(null);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void nullNameExceptionTest() {
        HumanPlayer humanPlayer1 = new HumanPlayer(null, "jozef@muni.cz", "password123", Role.PLAYER, null);
        HumanPlayerDao.create(humanPlayer1);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void nullEmailExceptionTest() {
        HumanPlayer humanPlayer1 = new HumanPlayer("Jozef Mrkva", null, "password123", Role.PLAYER, null);
        HumanPlayerDao.create(humanPlayer1);
    }
}
