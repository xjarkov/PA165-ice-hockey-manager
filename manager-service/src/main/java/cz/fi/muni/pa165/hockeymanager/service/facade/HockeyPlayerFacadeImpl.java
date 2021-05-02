package cz.fi.muni.pa165.hockeymanager.service.facade;

import cz.fi.muni.pa165.hockeymanager.dto.HockeyPlayerDto;
import cz.fi.muni.pa165.hockeymanager.entity.HockeyPlayer;
import cz.fi.muni.pa165.hockeymanager.entity.Team;
import cz.fi.muni.pa165.hockeymanager.facade.HockeyPlayerFacade;
import cz.fi.muni.pa165.hockeymanager.service.BeanMappingService;
import cz.fi.muni.pa165.hockeymanager.service.HockeyPlayerService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Lukas Machalek
 */
public class HockeyPlayerFacadeImpl implements HockeyPlayerFacade {
    @Autowired
    private BeanMappingService beanMappingService;
    private HockeyPlayerService hockeyPlayerService;

    @Override
    public Long create(HockeyPlayerDto hockeyPlayerDto) {
        HockeyPlayer mappedHockeyPlayer = beanMappingService.mapTo(hockeyPlayerDto, HockeyPlayer.class);
        mappedHockeyPlayer.setTeam(beanMappingService.mapTo(hockeyPlayerDto.getTeam(), Team.class));
        HockeyPlayer createdPlayer = hockeyPlayerService.create(mappedHockeyPlayer);
        return createdPlayer.getId();
    }

    @Override
    public void remove(HockeyPlayerDto hockeyPlayerDto) {
        hockeyPlayerService.remove(beanMappingService.mapTo(hockeyPlayerDto, HockeyPlayer.class));
    }

    @Override
    public void update(HockeyPlayerDto hockeyPlayerDto) {
        hockeyPlayerService.update(beanMappingService.mapTo(hockeyPlayerDto, HockeyPlayer.class));
    }

    @Override
    public List<HockeyPlayerDto> findAllHockeyPlayers() {
        return beanMappingService.mapTo(hockeyPlayerService.getAllPlayers(), HockeyPlayerDto.class);
    }
}
