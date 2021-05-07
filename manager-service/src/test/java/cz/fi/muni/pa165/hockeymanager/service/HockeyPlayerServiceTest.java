package cz.fi.muni.pa165.hockeymanager.service;

import cz.fi.muni.pa165.hockeymanager.dao.HockeyPlayerDao;
import cz.fi.muni.pa165.hockeymanager.dao.MatchDao;
import cz.fi.muni.pa165.hockeymanager.entity.HockeyPlayer;
import cz.fi.muni.pa165.hockeymanager.service.config.ServiceConfiguration;

import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Kristian Kosorin (456620)
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class HockeyPlayerServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    @InjectMocks
    private HockeyPlayerService service;

    @Mock
    private HockeyPlayerDao playerDao;

    @Mock
    private MatchDao matchDao;

    private HockeyPlayer player1 = new HockeyPlayer("Jozef", "Novák", 85, 90);
    private HockeyPlayer player2 = new HockeyPlayer("Fero", "Horniak", 93, 95);

    @BeforeMethod
    public void setup() throws ServiceException {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createTest() {
        service.create(player1);
        verify(playerDao).create(player1);
    }

    @Test
    public void removeTest() {
        service.remove(player1);
        verify(playerDao).remove(player1);
    }

    @Test
    public void removeNullTest() {
        when(playerDao.findById(player1.getId())).thenReturn(null);
        doNothing().when(playerDao).remove(player1);
        service.remove(player1);
        verify(playerDao).remove(player1);
    }

    @Test
    public void updateTest() {
        service.update(player1);
        verify(playerDao).update(player1);
    }

    @Test
    public void findByIdTest() {
        when(playerDao.findById(42L)).thenReturn(player1);

        HockeyPlayer found = service.findById(42L);
        verify(playerDao).findById(42L);
        assertThat(found).isEqualTo(player1);
    }

    @Test
    public void findAllTest() {
        List<HockeyPlayer> list = asList(player1, player2);
        when(playerDao.findAll()).thenReturn(list);

        List<HockeyPlayer> found = service.findAll();
        verify(playerDao).findAll();
        assertThat(found).contains(player1, player2);
        assertThat(found).hasSize(2);
    }

    @Test
    public void findPlayerTeamMatchesTest() {
        service.findPlayerTeamMatches(player1);
        verify(matchDao).findByTeam(player1.getTeam());
    }
}
