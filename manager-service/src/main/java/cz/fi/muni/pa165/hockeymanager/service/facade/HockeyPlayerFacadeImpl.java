package cz.fi.muni.pa165.hockeymanager.service.facade;

import cz.fi.muni.pa165.hockeymanager.dto.HockeyPlayerDto;
import cz.fi.muni.pa165.hockeymanager.entity.HockeyPlayer;
import cz.fi.muni.pa165.hockeymanager.entity.Team;
import cz.fi.muni.pa165.hockeymanager.facade.HockeyPlayerFacade;
import cz.fi.muni.pa165.hockeymanager.service.BeanMappingService;
import cz.fi.muni.pa165.hockeymanager.service.HockeyPlayerService;
import cz.fi.muni.pa165.hockeymanager.service.TeamService;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Lukas Machalek
 */
public class HockeyPlayerFacadeImpl implements HockeyPlayerFacade {

    @Autowired
    private BeanMappingService beanMappingService;

    @Inject
    private HockeyPlayerService hockeyPlayerService;

    @Inject
    private TeamService teamService;

    @Override
    public long create(HockeyPlayerDto hockeyPlayerDto) {
        HockeyPlayer mappedHockeyPlayer = beanMappingService.mapTo(hockeyPlayerDto, HockeyPlayer.class);
        Team playerTeam = beanMappingService.mapTo(hockeyPlayerDto.getTeam(), Team.class);
        if(teamService.findById(playerTeam.getId()) != null){
            teamService.create(playerTeam);
        }
        mappedHockeyPlayer.setTeam(playerTeam);
        HockeyPlayer createdPlayer = hockeyPlayerService.create(mappedHockeyPlayer);
        return createdPlayer.getId();
    }

    @Override
    public void remove(long hockeyPlayerId) {
        hockeyPlayerService.remove(hockeyPlayerService.findById(hockeyPlayerId));
    }

    @Override
    public List<HockeyPlayerDto> FindAllHockeyPlayers() {
        List<HockeyPlayer> hockeyPlayers = hockeyPlayerService.getAllPlayers();
        List<HockeyPlayerDto> hockeyPlayerDtos = new ArrayList<>();
        for(HockeyPlayer p : hockeyPlayers){
            HockeyPlayerDto pDto = beanMappingService.mapTo(p, HockeyPlayerDto.class);
            hockeyPlayerDtos.add(pDto);
        }
        return hockeyPlayerDtos;
    }

    @Override
    public void update(HockeyPlayerDto hockeyPlayerDto) {
        HockeyPlayer mappedHockeyPlayer = beanMappingService.mapTo(hockeyPlayerDto, HockeyPlayer.class);
        hockeyPlayerService.update(mappedHockeyPlayer);
    }
}
