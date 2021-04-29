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
    public long createHockeyPlayer(HockeyPlayerDto hockeyPlayerDto) {
        HockeyPlayer mappedHockeyPlayer = beanMappingService.mapTo(hockeyPlayerDto, HockeyPlayer.class);
        Team playerTeam = beanMappingService.mapTo(hockeyPlayerDto.getTeam(), Team.class);
        if(teamService.findById(playerTeam.getId()) != null){
            teamService.create(playerTeam);
        }
        mappedHockeyPlayer.setTeam(playerTeam);
        hockeyPlayerService.create(mappedHockeyPlayer);
        //hockeyPlayerService has void as return type for create. Im not sure if this returns the correct id.
        //It can be reworked to work as the model project, but hockeyPlayerService has to be changed for that.
        //Tell me in PR and i will rework it if needed.
        return mappedHockeyPlayer.getId();
    }

    @Override
    public void removeHockeyPlayer(long hockeyPlayerId) {
        //This also needs a touch inside hockeyPLayerService i guess.. this doesn't look good.
        hockeyPlayerService.remove(hockeyPlayerService.findById(hockeyPlayerId));
    }

    @Override
    public List<HockeyPlayerDto> getAllHockeyPlayers() {
        List<HockeyPlayer> hockeyPlayers = hockeyPlayerService.getAllPlayers();
        List<HockeyPlayerDto> hockeyPlayerDtos = new ArrayList<>();
        for(HockeyPlayer p : hockeyPlayers){
            HockeyPlayerDto pDto = beanMappingService.mapTo(p, HockeyPlayerDto.class);
            hockeyPlayerDtos.add(pDto);
        }
        return hockeyPlayerDtos;
    }
}
