package cz.fi.muni.pa165.hockeymanager.daoTests;

import cz.fi.muni.pa165.hockeymanager.PersistenceApplicationContext;
import cz.fi.muni.pa165.hockeymanager.dao.TeamDao;
import cz.fi.muni.pa165.hockeymanager.entity.Team;
import cz.fi.muni.pa165.hockeymanager.enums.Championship;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Lukas Machalek (485196)
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@Transactional
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class TeamDaoTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private TeamDao teamDao;

    private Team team1;
    private Team team2;
    private Team team3;
    private Team team4;


    @BeforeMethod
    public void setup() {
        team1 = new Team("team1", Championship.SHL);
        team2 = new Team("team2", Championship.SHL);
        team3 = new Team("team3", Championship.SHL);
        team4 = new Team("team4", Championship.SHL);
    }

    @Test
    public void createTeamTest() {
        teamDao.create(team1);
        assertThat(teamDao.findById(team1.getId())).isEqualTo(team1);
    }

    @Test
    public void findAllTeamsTest() {
        assertThat(teamDao.findAll().size()).isEqualTo(0);

        teamDao.create(team1);
        teamDao.create(team2);
        teamDao.create(team3);

        assertThat(teamDao.findAll().size()).isEqualTo(3);

        teamDao.create(team4);

        assertThat(teamDao.findAll().size()).isEqualTo(4);
    }

    @Test
    public void findTeamByIdTest() {
        teamDao.create(team1);
        teamDao.create(team2);
        teamDao.create(team3);

        assertThat(teamDao.findById(team1.getId())).isEqualTo(team1);
        assertThat(teamDao.findById(team2.getId())).isEqualTo(team2);
        assertThat(teamDao.findById(team3.getId())).isEqualTo(team3);

        teamDao.create(team4);
        assertThat(teamDao.findById(team4.getId())).isEqualTo(team4);
    }

    @Test
    public void removeTeamTest() {
        teamDao.create(team1);
        teamDao.create(team2);

        assertThat(teamDao.findById(team1.getId())).isEqualTo(team1);
        assertThat(teamDao.findById(team2.getId())).isEqualTo(team2);

        teamDao.remove(team1);

        assertThat(teamDao.findById(team1.getId())).isEqualTo(null);
        assertThat(teamDao.findById(team2.getId())).isEqualTo(team2);
    }

    @Test
    public void findTeamByNameTest() {
        teamDao.create(team1);
        teamDao.create(team2);

        assertThat(teamDao.findByName("team1")).isEqualTo(team1);
        assertThat(teamDao.findByName("team2")).isEqualTo(team2);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createTeamWithNullChampionship() {
        Team team = new Team("team", null);
        teamDao.create(team);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createTeamWithNullName() {
        Team team = new Team(null, Championship.SHL);
        teamDao.create(team);
    }

    @Test
    public void updateTeamTest(){
        teamDao.create(team1);

        assertThat(teamDao.findById(team1.getId()).getName()).isEqualTo(team1.getName());

        team1.setName("changed name");
        teamDao.update(team1);

        assertThat(teamDao.findById(team1.getId()).getName()).isEqualTo(team1.getName());
    }
}
