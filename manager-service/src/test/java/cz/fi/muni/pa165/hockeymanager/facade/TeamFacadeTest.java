package cz.fi.muni.pa165.hockeymanager.facade;

import cz.fi.muni.pa165.hockeymanager.dto.TeamDto;
import cz.fi.muni.pa165.hockeymanager.dto.UserDto;
import cz.fi.muni.pa165.hockeymanager.entity.Team;
import cz.fi.muni.pa165.hockeymanager.entity.User;
import cz.fi.muni.pa165.hockeymanager.enums.Championship;
import cz.fi.muni.pa165.hockeymanager.service.BeanMappingService;
import cz.fi.muni.pa165.hockeymanager.service.TeamService;
import cz.fi.muni.pa165.hockeymanager.service.config.ServiceConfiguration;
import cz.fi.muni.pa165.hockeymanager.service.facade.TeamFacadeImpl;
import java.util.ArrayList;
import java.util.HashSet;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Lukas Machalek (485196)
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class TeamFacadeTest {

    @Mock
    private TeamService teamService;

    @Mock
    private BeanMappingService beanMappingService;

    @InjectMocks
    private final TeamFacade teamFacade = new TeamFacadeImpl();

    private Team team;
    private TeamDto teamDto;

    @BeforeClass
    public void setupClass() throws ServiceException {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeMethod
    public void setup() {
        //teamDto setup
        teamDto = new TeamDto();
        teamDto.setName("team1");
        teamDto.setChampionship(Championship.DEL);
        teamDto.setHockeyPlayers(new HashSet<>());
        teamDto.setMatches(new HashSet<>());

        teamDto.setId(1L);
        teamDto.setPoints(5L);
        teamDto.setManager(new UserDto());

        //team setup
        team = new Team("team1", Championship.DEL);
        team.setId(1L);
        team.setManager(new User());
        team.setPoints(5L);

        reset(teamService);
        reset(beanMappingService);
    }

    @Test
    public void createTeamTest() {
        when(beanMappingService.mapTo(teamDto, Team.class)).thenReturn(team);
        when(teamService.create(team)).thenReturn(team);

        long id = teamFacade.create(teamDto);

        verify(teamService).create(team);
        verify(beanMappingService).mapTo(teamDto, Team.class);
        assertThat(id).isEqualTo(team.getId());
    }

    @Test
    public void removeTeamTest() {
        when(teamService.findById(team.getId())).thenReturn(team);
        teamFacade.remove(team.getId());

        verify(teamService).findById(team.getId());
        verify(teamService).remove(team);
    }

    @Test
    public void findByIdTest() {
        when(beanMappingService.mapTo(team, TeamDto.class)).thenReturn(teamDto);
        when(teamService.findById(1L)).thenReturn(team);
        TeamDto found = teamFacade.findTeamById(team.getId());

        verify(teamService).findById(team.getId());
        assertThat(found).isEqualTo(teamDto);
    }

    @Test
    public void findByNameTest() {
        when(beanMappingService.mapTo(team, TeamDto.class)).thenReturn(teamDto);
        when(teamService.findByName(team.getName())).thenReturn(team);
        TeamDto found = teamFacade.findTeamByName(team.getName());

        verify(teamService).findByName(team.getName());
        assertThat(found).isEqualTo(teamDto);
    }

    @Test
    public void findAllTest() {
        when(teamService.findAll()).thenReturn(new ArrayList<>());
        when(beanMappingService.mapTo(new ArrayList<Team>(), TeamDto.class)).thenReturn(new ArrayList<TeamDto>());
        teamFacade.findAllTeams();

        verify(teamService).findAll();
        verify(beanMappingService).mapTo(new ArrayList<Team>(), TeamDto.class);
    }
}
