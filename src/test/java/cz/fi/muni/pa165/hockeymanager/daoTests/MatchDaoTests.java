package cz.fi.muni.pa165.hockeymanager.daoTests;

import cz.fi.muni.pa165.hockeymanager.PersistenceApplicationContext;
import cz.fi.muni.pa165.hockeymanager.dao.MatchDao;
import cz.fi.muni.pa165.hockeymanager.entity.Match;
import cz.fi.muni.pa165.hockeymanager.entity.Team;
import cz.fi.muni.pa165.hockeymanager.enums.Championship;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Petr Šopf (506511)
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@Transactional
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class MatchDaoTests extends AbstractTestNGSpringContextTests {
    @Autowired
    private MatchDao matchDao;

    private Team omskTeam;
    private Team moskvaTeam;
    private Team petersburgTeam;
    private Team sochiTeam;

    private Match match;
    private Match match2;
    private Match match3;
    private Match match4;
    private Match match5;

    @BeforeMethod
    public void setup() {
        omskTeam = new Team("team1", Championship.SHL);
        moskvaTeam = new Team("team2", Championship.SHL);
        petersburgTeam = new Team("SKA Petersburg", Championship.KHL);
        sochiTeam = new Team("Sochi", Championship.KHL);

        match = new Match(omskTeam, moskvaTeam, LocalDateTime.of(2021, Month.AUGUST, 24, 18, 0));
        match2 = new Match(omskTeam, petersburgTeam, LocalDateTime.of(2021, Month.AUGUST, 26, 18, 0));
        match3 = new Match(moskvaTeam, petersburgTeam, LocalDateTime.of(2021, Month.AUGUST, 27, 18, 0));
        match4 = new Match(petersburgTeam, omskTeam, LocalDateTime.of(2021, Month.AUGUST, 21, 19, 0));
        match5 = new Match(moskvaTeam, sochiTeam, LocalDateTime.of(2021, Month.AUGUST, 28, 18, 0));
    }

    @Test
    public void createMatchTest() {
        matchDao.create(match);
        assertThat(matchDao.findById(match.getId())).isEqualTo(match);
    }

    @Test
    public void findAllMatchesTest() {
        assertThat(matchDao.findAll().size()).isEqualTo(0);
        matchDao.create(match);
        matchDao.create(match2);
        matchDao.create(match3);
        assertThat(matchDao.findAll().size()).isEqualTo(3);
        matchDao.create(match4);
        matchDao.create(match5);
        assertThat(matchDao.findAll().size()).isEqualTo(5);
    }

    @Test
    public void findMatchesByIdTest() {
        matchDao.create(match);
        matchDao.create(match2);
        matchDao.create(match3);
        matchDao.create(match4);
        matchDao.create(match5);

        assertThat(matchDao.findById(match2.getId())).isEqualTo(match2);
    }

    @Test
    public void removeMatchTest() {
        matchDao.create(match);
        assertThat(matchDao.findById(match.getId())).isEqualTo(match);
        matchDao.remove(match);
        assertThat(matchDao.findById(match.getId())).isEqualTo(null);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void nullDateTimeExceptionTest() {
        match.setDateTime(null);
        matchDao.create(match);
    }
}
