package cz.fi.muni.pa165.hockeymanager.facade;

import cz.fi.muni.pa165.hockeymanager.dto.HockeyPlayerDto;
import cz.fi.muni.pa165.hockeymanager.entity.HockeyPlayer;
import cz.fi.muni.pa165.hockeymanager.service.BeanMappingService;
import cz.fi.muni.pa165.hockeymanager.service.HockeyPlayerService;
import cz.fi.muni.pa165.hockeymanager.service.config.ServiceConfiguration;
import cz.fi.muni.pa165.hockeymanager.service.facade.HockeyPlayerFacadeImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.*;


@ContextConfiguration(classes = ServiceConfiguration.class)
public class HockeyPlayerFacadeTest extends AbstractTransactionalTestNGSpringContextTests {
    @Mock
    private HockeyPlayerService service;

    @Mock
    private BeanMappingService beanMappingService;

    @InjectMocks
    private HockeyPlayerFacadeImpl hockeyPlayerFacade = new HockeyPlayerFacadeImpl();

    private HockeyPlayer player1;
    private HockeyPlayerDto dto;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.openMocks(this);

        player1 = new HockeyPlayer("Jozef", "Novák", 85, 90);
        dto = new HockeyPlayerDto("Jozef", "Novák", 85, 90);
    }

    @Test
    public void createPlayerTest() {
        when(beanMappingService.mapTo(dto, HockeyPlayer.class)).thenReturn(player1);
        when(service.create(player1)).thenReturn(player1);
        assertThat(hockeyPlayerFacade.create(dto)).isEqualTo(player1.getId());
    }
}
