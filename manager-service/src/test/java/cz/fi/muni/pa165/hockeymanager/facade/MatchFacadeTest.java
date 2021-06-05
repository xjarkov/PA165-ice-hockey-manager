package cz.fi.muni.pa165.hockeymanager.facade;

import cz.fi.muni.pa165.hockeymanager.dto.MatchCreateDto;
import cz.fi.muni.pa165.hockeymanager.dto.MatchDto;
import cz.fi.muni.pa165.hockeymanager.dto.TeamDto;
import cz.fi.muni.pa165.hockeymanager.entity.Match;
import cz.fi.muni.pa165.hockeymanager.entity.Team;
import cz.fi.muni.pa165.hockeymanager.enums.Championship;
import cz.fi.muni.pa165.hockeymanager.service.BeanMappingService;
import cz.fi.muni.pa165.hockeymanager.service.MatchService;
import cz.fi.muni.pa165.hockeymanager.service.config.ServiceConfiguration;
import cz.fi.muni.pa165.hockeymanager.service.facade.MatchFacadeImpl;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Petr Šopf (506511)
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class MatchFacadeTest extends AbstractTransactionalTestNGSpringContextTests {
    @Mock
    private MatchService matchService;

    @Spy
    @Autowired
    private BeanMappingService beanMappingService;

    @InjectMocks
    private MatchFacadeImpl matchFacade = new MatchFacadeImpl();

    private Match match1;
    private Match match2;

    private MatchCreateDto matchCreateDto1;

    private MatchDto matchDto1;
    private MatchDto matchDto2;

    private List<Match> matchList;
    private List<MatchDto> matchDtoList;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.openMocks(this);

        match1 = new Match(
                new Team("Sochi", Championship.KHL),
                new Team("CSKA Moskva", Championship.KHL),
                LocalDateTime.of(2021, Month.AUGUST, 24, 18, 0)
        );

        match2 = new Match(
                new Team("Sochi", Championship.KHL),
                new Team("CSKA Moskva", Championship.KHL),
                LocalDateTime.of(2021, Month.AUGUST, 26, 18, 0)
        );

        matchCreateDto1 = beanMappingService.mapTo(match1, MatchCreateDto.class);

        matchDto1 = beanMappingService.mapTo(match1, MatchDto.class);
        matchDto2 = beanMappingService.mapTo(match2, MatchDto.class);

        matchList = List.of(match1, match2);
        matchDtoList = List.of(matchDto1, matchDto2);
    }

    @Test
    public void createMatchTest() {
        when(beanMappingService.mapTo(matchDto1, Match.class)).thenReturn(match1);
        when(matchService.create(match1)).thenReturn(match1);
        assertThat(matchFacade.create(matchCreateDto1)).isEqualTo(match1.getId());
    }

    @Test
    public void removeMatchTest() {
        when(beanMappingService.mapTo(matchDto1, Match.class)).thenReturn(match1);
        matchFacade.remove(matchDto1);
        verify(matchService).remove(match1);
    }

    @Test
    public void findByIdTest() {
        when(matchService.findById(match1.getId())).thenReturn(match1);
        when(beanMappingService.mapMatchToMatchDto(match1)).thenReturn(matchDto1);
        assertThat(matchFacade.findMatchById(match1.getId())).isEqualTo(matchDto1);
    }

    @Test
    public void findAllTest() {
        when(matchService.findAll()).thenReturn(matchList);
        when(beanMappingService.mapMatchesToMatchDtos(matchList)).thenReturn(matchDtoList);
        assertThat(matchFacade.findAllMatches()).isEqualTo(matchDtoList);
    }

    @Test
    public void findNearestTest() {
        when(matchService.findNearest()).thenReturn(match1);
        when(beanMappingService.mapMatchToMatchDto(match1)).thenReturn(matchDto1);
        assertThat(matchFacade.findNearestMatch()).isEqualTo(matchDto1);
    }
}
