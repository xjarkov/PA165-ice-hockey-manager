package cz.fi.muni.pa165.hockeymanager.service;

import cz.fi.muni.pa165.hockeymanager.dao.MatchDao;
import cz.fi.muni.pa165.hockeymanager.dao.TeamDao;
import cz.fi.muni.pa165.hockeymanager.entity.Match;
import cz.fi.muni.pa165.hockeymanager.entity.Team;
import cz.fi.muni.pa165.hockeymanager.enums.Championship;
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

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Petr Šopf (506511)
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class MatchServiceTest extends AbstractTestNGSpringContextTests {
    @Autowired
    @InjectMocks
    private MatchService service;

    @Mock
    private MatchDao matchDao;

    private Match match1 = new Match(
            new Team("Sochi", Championship.KHL),
            new Team("CSKA Moskva", Championship.KHL),
            LocalDateTime.of(2021, Month.AUGUST, 24, 18, 0)
    );
    private Match match2 = new Match(
            new Team("Sochi", Championship.KHL),
            new Team("CSKA Moskva", Championship.KHL),
            LocalDateTime.of(2021, Month.AUGUST, 26, 18, 0)
    );

    @BeforeMethod
    public void setup() throws ServiceException {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createTest() {
        service.create(match1);
        verify(matchDao).create(match1);
    }

    @Test
    public void removeTest() {
        service.remove(match1);
        verify(matchDao).remove(match1);
    }

    @Test
    public void removeNullTest() {
        when(matchDao.findById(match1.getId())).thenReturn(null);
        doNothing().when(matchDao).remove(match1);
        service.remove(match1);
        verify(matchDao).remove(match1);
    }

    @Test
    public void updateTest() {
        service.update(match1);
        verify(matchDao).update(match1);
    }

    @Test
    public void findByIdTest() {
        when(matchDao.findById(69L)).thenReturn(match1);

        Match found = service.findById(69L);
        verify(matchDao).findById(69L);
        assertThat(found).isEqualTo(match1);
    }

    @Test
    public void findAllTest() {
        List<Match> list = asList(match1, match2);
        when(matchDao.findAll()).thenReturn(list);

        List<Match> found = service.findAll();
        verify(matchDao).findAll();
        assertThat(found).contains(match1, match2);
        assertThat(found).hasSize(2);
    }
}
