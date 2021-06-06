package cz.fi.muni.pa165.hockeymanager.facade;

import cz.fi.muni.pa165.hockeymanager.dto.HockeyPlayerCreateDto;
import cz.fi.muni.pa165.hockeymanager.dto.HockeyPlayerDto;
import cz.fi.muni.pa165.hockeymanager.entity.HockeyPlayer;
import cz.fi.muni.pa165.hockeymanager.service.BeanMappingService;
import cz.fi.muni.pa165.hockeymanager.service.HockeyPlayerService;
import cz.fi.muni.pa165.hockeymanager.service.config.ServiceConfiguration;
import cz.fi.muni.pa165.hockeymanager.service.facade.HockeyPlayerFacadeImpl;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Kristian Kosorin (456620)
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class HockeyPlayerFacadeTest extends AbstractTransactionalTestNGSpringContextTests {
    @Mock
    private HockeyPlayerService hockeyPlayerService;

    @Spy
    @Autowired
    private BeanMappingService beanMappingService;

    @InjectMocks
    private HockeyPlayerFacadeImpl hockeyPlayerFacade = new HockeyPlayerFacadeImpl();

    private HockeyPlayer player1;
    private HockeyPlayerDto player1Dto;
    private HockeyPlayerCreateDto player1CreateDto;

    private List<HockeyPlayer> hockeyPlayerList;
    private List<HockeyPlayerDto> hockeyPlayerDtoList;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.openMocks(this);

        player1 = new HockeyPlayer("Jozef", "Novák", 85, 90);
        player1CreateDto = beanMappingService.mapTo(player1, HockeyPlayerCreateDto.class);
        player1Dto = beanMappingService.mapTo(player1, HockeyPlayerDto.class);

        hockeyPlayerList = List.of(player1);
        hockeyPlayerDtoList = List.of(player1Dto);
    }

    @Test
    public void createPlayerTest() {
        when(beanMappingService.mapTo(player1Dto, HockeyPlayer.class)).thenReturn(player1);
        when(hockeyPlayerService.create(player1)).thenReturn(player1);
        assertThat(hockeyPlayerFacade.create(player1CreateDto)).isEqualTo(player1.getId());
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
