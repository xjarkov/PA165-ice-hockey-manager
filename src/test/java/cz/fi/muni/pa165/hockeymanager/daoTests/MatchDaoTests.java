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
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;

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

    @Test
    public void createMatchTest() {
        Team omskTeam = new Team("Omsk", Championship.KHL);
        Team moskvaTeam = new Team("CSKA Moskva", Championship.KHL);
        Match match = new Match(omskTeam, moskvaTeam, LocalDateTime.of(2021, Month.AUGUST, 24, 18, 0));

        matchDao.create(match);
        assertThat(matchDao.findById(match.getId())).isEqualTo(match);
    }

    @Test
    public void findAllMatchesTest() {
        Team omskTeam = new Team("Omsk", Championship.KHL);
        Team moskvaTeam = new Team("CSKA Moskva", Championship.KHL);
        Team petersburgTeam = new Team("SKA Petersburg", Championship.KHL);
        Team sochiTeam = new Team("Sochi", Championship.KHL);

        assertThat(matchDao.findAll().size()).isEqualTo(0);
        matchDao.create(new Match(omskTeam, moskvaTeam, LocalDateTime.of(2021, Month.AUGUST, 24, 18, 0)));
        matchDao.create(new Match(petersburgTeam, sochiTeam, LocalDateTime.of(2021, Month.AUGUST, 25, 18, 0)));
        matchDao.create(new Match(omskTeam, petersburgTeam, LocalDateTime.of(2021, Month.AUGUST, 26, 18, 0)));
        assertThat(matchDao.findAll().size()).isEqualTo(3);
        matchDao.create(new Match(moskvaTeam, petersburgTeam, LocalDateTime.of(2021, Month.AUGUST, 27, 18, 0)));
        matchDao.create(new Match(moskvaTeam, sochiTeam, LocalDateTime.of(2021, Month.AUGUST, 28, 18, 0)));
        assertThat(matchDao.findAll().size()).isEqualTo(5);
    }

    @Test
    public void findMatchesByIdTest() {
        Team omskTeam = new Team("Omsk", Championship.KHL);
        Team moskvaTeam = new Team("CSKA Moskva", Championship.KHL);
        Team petersburgTeam = new Team("SKA Petersburg", Championship.KHL);
        Team sochiTeam = new Team("Sochi", Championship.KHL);

        Match lookupMatch = new Match(omskTeam, petersburgTeam, LocalDateTime.of(2021, Month.AUGUST, 26, 18, 0));

        matchDao.create(new Match(omskTeam, moskvaTeam, LocalDateTime.of(2021, Month.AUGUST, 24, 18, 0)));
        matchDao.create(new Match(petersburgTeam, sochiTeam, LocalDateTime.of(2021, Month.AUGUST, 25, 18, 0)));
        matchDao.create(lookupMatch);
        matchDao.create(new Match(moskvaTeam, petersburgTeam, LocalDateTime.of(2021, Month.AUGUST, 27, 18, 0)));
        matchDao.create(new Match(moskvaTeam, sochiTeam, LocalDateTime.of(2021, Month.AUGUST, 28, 18, 0)));

        assertThat(matchDao.findById(lookupMatch.getId())).isEqualTo(lookupMatch);
    }

    @Test
    public void removeMatchTest() {
        Team omskTeam = new Team("Omsk", Championship.KHL);
        Team moskvaTeam = new Team("CSKA Moskva", Championship.KHL);
        Match match = new Match(omskTeam, moskvaTeam, LocalDateTime.of(2021, Month.AUGUST, 24, 18, 0));

        matchDao.create(match);
        assertThat(matchDao.findById(match.getId())).isEqualTo(match);
        matchDao.remove(match);
        assertThat(matchDao.findById(match.getId())).isEqualTo(null);
    }
}
