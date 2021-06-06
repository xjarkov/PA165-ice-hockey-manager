package cz.fi.muni.pa165.hockeymanager.service.facade;

import cz.fi.muni.pa165.hockeymanager.dao.HockeyPlayerDao;
import cz.fi.muni.pa165.hockeymanager.dto.HockeyPlayerCreateDto;
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
    public Long create(HockeyPlayerCreateDto hockeyPlayerCreateDto) {
        HockeyPlayer hockeyPlayer = beanMappingService.mapTo(hockeyPlayerCreateDto, HockeyPlayer.class);
        hockeyPlayer = hockeyPlayerService.create(hockeyPlayer);
        return hockeyPlayer.getId();
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
    public HockeyPlayerDto findById(Long id) {
        return beanMappingService.mapTo(hockeyPlayerService.findById(id), HockeyPlayerDto.class);
    }

    @Override
    public List<HockeyPlayerDto> findAll() {
        return beanMappingService.mapTo(hockeyPlayerService.findAll(), HockeyPlayerDto.class);
    }

    @Override
    public List<HockeyPlayerDto> findPlayersWithoutTeam() {
        return beanMappingService.mapTo(hockeyPlayerService.findPlayersWithoutTeam(), HockeyPlayerDto.class);
    }
}
