package cz.fi.muni.pa165.hockeymanager.daoTests;

import cz.fi.muni.pa165.hockeymanager.PersistenceApplicationContext;
import cz.fi.muni.pa165.hockeymanager.dao.TeamDao;
import cz.fi.muni.pa165.hockeymanager.entity.Match;
import cz.fi.muni.pa165.hockeymanager.entity.Team;
import cz.fi.muni.pa165.hockeymanager.enums.Championship;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = PersistenceApplicationContext.class)
@Transactional
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class TeamDaoTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private TeamDao teamDao;

    @Test
    public void createTeamTest() {
        Team team = new Team("team", Championship.SHL);

        teamDao.create(team);
        assertThat(teamDao.findById(team.getId())).isEqualTo(team);
    }

    @Test
    public void findAllTeamsTest() {
        Team team1 = new Team("team1", Championship.SHL);
        Team team2 = new Team("team2", Championship.SHL);
        Team team3 = new Team("team3", Championship.SHL);
        Team team4 = new Team("team4", Championship.SHL);

        assertThat(teamDao.findAll().size()).isEqualTo(0);

        teamDao.create(team1);
        teamDao.create(team2);
        teamDao.create(team3);

        assertThat(teamDao.findAll().size()).isEqualTo(3);

        teamDao.create(team4);

        assertThat(teamDao.findAll().size()).isEqualTo(4);
    }

    @Test
    public void findTeamByIdTest(){
        Team team1 = new Team("team1", Championship.SHL);
        Team team2 = new Team("team2", Championship.SHL);
        Team team3 = new Team("team3", Championship.SHL);
        Team team4 = new Team("team4", Championship.SHL);

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
    public void removeTeamTest(){
        Team team1 = new Team("team1", Championship.SHL);
        Team team2 = new Team("team2", Championship.SHL);

        teamDao.create(team1);
        teamDao.create(team2);

        assertThat(teamDao.findById(team1.getId())).isEqualTo(team1);
        assertThat(teamDao.findById(team2.getId())).isEqualTo(team2);

        teamDao.remove(team1);

        assertThat(teamDao.findById(team1.getId())).isEqualTo(null);
        assertThat(teamDao.findById(team2.getId())).isEqualTo(team2);
    }

}
