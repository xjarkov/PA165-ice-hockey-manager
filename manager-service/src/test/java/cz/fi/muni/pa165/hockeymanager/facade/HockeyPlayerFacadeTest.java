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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.*;


@ContextConfiguration(classes = ServiceConfiguration.class)
public class HockeyPlayerFacadeTest extends AbstractTransactionalTestNGSpringContextTests {
    @Mock
    private HockeyPlayerService hockeyPlayerService;

    @Mock
    private BeanMappingService beanMappingService;

    @InjectMocks
    private HockeyPlayerFacadeImpl hockeyPlayerFacade = new HockeyPlayerFacadeImpl();

    private HockeyPlayer player1 = new HockeyPlayer("Jozef", "Novák", 85, 90);
    private HockeyPlayerDto player1Dto = new HockeyPlayerDto("Jozef", "Novák", 85, 90);

    private List<HockeyPlayer> hockeyPlayerList = List.of(player1);
    private List<HockeyPlayerDto> hockeyPlayerDtoList = List.of(player1Dto);

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createPlayerTest() {
        when(beanMappingService.mapTo(player1Dto, HockeyPlayer.class)).thenReturn(player1);
        when(hockeyPlayerService.create(player1)).thenReturn(player1);
        assertThat(hockeyPlayerFacade.create(player1Dto)).isEqualTo(player1.getId());
    }

    @Test
    public void removePlayerTest() {
        when(beanMappingService.mapTo(player1Dto, HockeyPlayer.class)).thenReturn(player1);
        hockeyPlayerFacade.remove(player1Dto);
        verify(hockeyPlayerService).remove(player1);
    }

    @Test
    public void findAllTest() {
        when(hockeyPlayerService.findAll()).thenReturn(hockeyPlayerList);
        when(beanMappingService.mapTo(hockeyPlayerList, HockeyPlayerDto.class)).thenReturn(hockeyPlayerDtoList);
        assertThat(hockeyPlayerFacade.findAll()).isEqualTo(hockeyPlayerDtoList);
    }
}
