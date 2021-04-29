package cz.fi.muni.pa165.hockeymanager.facade;

import cz.fi.muni.pa165.hockeymanager.dto.HockeyPlayerDto;
import java.util.List;

/**
 * @author Lukas Machalek (485196)
 */
public interface HockeyPlayerFacade {

    /**
     * Create HockeyPlayer in database.
     * @param hockeyPlayerDto HockeyPlayer to be saved in database.
     * @return Id of the newly saved player.
     */
    long createHockeyPlayer(HockeyPlayerDto hockeyPlayerDto);

    /**
     * Delete HockeyPlayer from database by it's id.
     * @param hockeyPlayerId Id of HockeyPlayer you want to delete.
     */
    void removeHockeyPlayer(long hockeyPlayerId);

    /**
     * Get List of all HockeyPlayers saved in database.
     * @return List of HockeyPlayer.
     */
    List<HockeyPlayerDto> getAllHockeyPlayers();

}
