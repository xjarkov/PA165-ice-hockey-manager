package cz.fi.muni.pa165.hockeymanager.service;

import cz.fi.muni.pa165.hockeymanager.dao.HockeyPlayerDao;
import cz.fi.muni.pa165.hockeymanager.entity.HockeyPlayer;
import cz.fi.muni.pa165.hockeymanager.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertThrows;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class HockeyPlayerServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    @InjectMocks
    private HockeyPlayerService service;

    @Mock
    private HockeyPlayerDao dao;

    private HockeyPlayer player1;
    private HockeyPlayer player2;
    private HockeyPlayer player3;

    @BeforeMethod
    public void preparePlayers() {
        player1 = new HockeyPlayer();
        player2 = new HockeyPlayer();
        player3 = new HockeyPlayer();
    }

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void register_givenNullUser_throws() {
        assertThrows(IllegalArgumentException.class, () -> service.create(null));
    }

    @Test
    public void serviceCreateCallsDAOCreateTest() {
        service.create(player1);
        verify(dao).create(player1);
    }

    @Test
    public void serviceRemoveCallsDAORemoveTest() {
        when(dao.findById(player1.getId())).thenReturn(player1);
        doNothing().when(dao).remove(player1);
        service.remove(player1);
        verify(dao).remove(player1);
    }

    @Test
    public void serviceRemoveNullTest() {
        when(dao.findById(player1.getId())).thenReturn(null);
        doNothing().when(dao).remove(player1);
        service.remove(player1);
        verify(dao).remove(player1);
    }

    @Test
    public void serviceUpdateCallsDAOUpdateTest() {
        service.update(player1);
        verify(dao).update(player1);
    }

    @Test
    public void serviceFindByIdTest() {
        when(dao.findById(player1.getId())).thenReturn(player1);
        Assert.assertEquals(service.findById(player1.getId()), player1);
    }
}
