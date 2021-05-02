package cz.fi.muni.pa165.hockeymanager.service;

import cz.fi.muni.pa165.hockeymanager.dao.TeamDao;
import cz.fi.muni.pa165.hockeymanager.entity.HockeyPlayer;
import cz.fi.muni.pa165.hockeymanager.entity.Match;
import cz.fi.muni.pa165.hockeymanager.entity.Team;
import cz.fi.muni.pa165.hockeymanager.enums.Championship;
import cz.fi.muni.pa165.hockeymanager.exceptions.ManagerServiceException;
import cz.fi.muni.pa165.hockeymanager.service.config.ServiceConfiguration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import net.bytebuddy.asm.Advice;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ContextConfiguration(classes = ServiceConfiguration.class)
public class TeamServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private TeamDao teamDao;

    @Autowired
    @InjectMocks
    private TeamService teamService;

    private Team team1;
    private Team team2;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeMethod
    public void resetMock() {
        reset(teamDao);
    }

    @BeforeMethod
    public void prepareTeams() {
        team1 = new Team("team1", Championship.SHL);
        team2 = new Team("team2", Championship.DEL);
    }

    @Test
    public void createTeams() {
        teamService.create(team1);
        verify(teamDao).create(team1);
    }

    @Test
    public void removeTeamTest() {
        Team createdTeam = teamService.create(team1);
        verify(teamDao).create(team1);
        teamService.remove(createdTeam);
        verify(teamDao).remove(createdTeam);
    }

    @Test
    public void findTeamByNameTest() {
        Team createdTeam = teamService.create(team1);
        verify(teamDao).create(team1);
        when(teamDao.findByName("team1")).thenReturn(team1);

        assertThat(teamService.findByName(createdTeam.getName())).isEqualTo(createdTeam);
    }

    @Test
    public void findTeamByIdTest() {
        Team createdTeam = teamService.create(team1);
        verify(teamDao).create(team1);
        when(teamDao.findById(team1.getId())).thenReturn(team1);

        assertThat(teamService.findById(createdTeam.getId())).isEqualTo(createdTeam);
    }

    @Test
    public void findAllTeamsTest() {
        Team createdTeam1 = teamService.create(team1);
        Team createdTeam2 = teamService.create(team2);
        verify(teamDao).create(team1);
        verify(teamDao).create(team2);

        when(teamDao.findAll()).thenReturn(asList(createdTeam1, createdTeam2));

        assertThat(teamService.findAll()).isEqualTo(asList(createdTeam1, createdTeam2));
    }

    @Test
    public void addPlayerToTeamCorrectTest() {
        HockeyPlayer player = new HockeyPlayer("fName", "lName", 1, 1);

        teamService.addPlayer(team1, player);

        assertThat(player.getTeam()).isEqualTo(team1);
        assertThat(team1.getHockeyPlayers()).contains(player);
    }

    @Test(expectedExceptions = ManagerServiceException.class)
    public void addPlayerWithTeamToTeamTest() {
        HockeyPlayer player = new HockeyPlayer("fName", "lName", 1, 1, new Team("tean3", Championship.KHL));

        teamService.addPlayer(team1, player);
    }

    @Test(expectedExceptions = ManagerServiceException.class)
    public void addAlreadyPresentPlayerToTeamTest() {
        HockeyPlayer player = new HockeyPlayer("fName", "lName", 1, 1, team1);

        teamService.addPlayer(team1, player);
    }

    @Test
    public void removePlayerCorrectTest() {
        HockeyPlayer player = new HockeyPlayer("fName", "lName", 1, 1);
        teamService.addPlayer(team1, player);

        assertThat(player.getTeam()).isEqualTo(team1);
        assertThat(team1.getHockeyPlayers()).contains(player);

        teamService.removePlayer(team1, player);

        assertThat(player.getTeam()).isEqualTo(null);
        assertThat(team1.getHockeyPlayers().size()).isEqualTo(0);
    }

    @Test(expectedExceptions = ManagerServiceException.class)
    public void removeMissingPlayerTest() {
        HockeyPlayer player = new HockeyPlayer("fName", "lName", 1, 1);
        teamService.removePlayer(team1, player);
    }

    @Test
    public void addMatchCorrectTest() {
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 1, 1);
        Match match = new Match(team1, team2, dateTime);

        teamService.addMatch(team1, match);
        assertThat(team1.getMatches()).contains(match);
    }

    @Test(expectedExceptions = ManagerServiceException.class)
    public void addMatchIncorrectTest() {
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 1, 1);
        Match match = new Match(new Team("team3", Championship.KHL), team2, dateTime);

        teamService.addMatch(team1, match);
    }

    @Test
    public void removeNearestMatchTest() {
        LocalDateTime dateTimeFar = LocalDateTime.of(2000, 1, 1, 1, 1);
        Match matchFar = new Match(team1, new Team("team3", Championship.KHL), dateTimeFar);
        LocalDateTime dateTimeNear = LocalDateTime.of(2020, 1, 1, 1, 1);
        Match matchNear = new Match(team1, team2, dateTimeNear);

        teamService.addMatch(team1, matchFar);
        teamService.addMatch(team1, matchNear);

        assertThat(team1.getMatches().size()).isEqualTo(2);

        teamService.removeTheNearestMatch(team1);

        assertThat(team1.getMatches().size()).isEqualTo(1);
        assertThat(team1.getMatches()).contains(matchNear);
    }

    @Test(expectedExceptions = ManagerServiceException.class)
    public void removeNearestMatchIncorrectTest() {
        teamService.removeTheNearestMatch(team1);
    }
}
