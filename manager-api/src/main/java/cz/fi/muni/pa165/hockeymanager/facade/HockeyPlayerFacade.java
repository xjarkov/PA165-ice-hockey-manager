package cz.fi.muni.pa165.hockeymanager.facade;

import cz.fi.muni.pa165.hockeymanager.dto.HockeyPlayerDto;
import java.util.List;

public interface HockeyPlayerFacade {
    long createHockeyPlayer(HockeyPlayerDto hockeyPlayerDto);
    void removeHockeyPlayer(long hockeyPlayerId);
    List<HockeyPlayerDto> getAllHockeyPlayers();

}
