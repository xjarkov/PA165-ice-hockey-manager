package cz.fi.muni.pa165.hockeymanager.service.facade;

import cz.fi.muni.pa165.hockeymanager.dto.HockeyPlayerDto;
import cz.fi.muni.pa165.hockeymanager.entity.HockeyPlayer;
import cz.fi.muni.pa165.hockeymanager.entity.Team;
import cz.fi.muni.pa165.hockeymanager.facade.HockeyPlayerFacade;
import cz.fi.muni.pa165.hockeymanager.service.BeanMappingService;
import cz.fi.muni.pa165.hockeymanager.service.HockeyPlayerService;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Lukas Machalek (485196)
 */
@Service
@Transactional
public class HockeyPlayerFacadeImpl implements HockeyPlayerFacade {
    @Autowired
    private BeanMappingService beanMappingService;

    @Autowired
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
    public List<HockeyPlayerDto> findAll() {
        return beanMappingService.mapTo(hockeyPlayerService.findAll(), HockeyPlayerDto.class);
    }
}
