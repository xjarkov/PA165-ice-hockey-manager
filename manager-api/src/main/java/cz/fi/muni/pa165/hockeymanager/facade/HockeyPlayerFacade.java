package cz.fi.muni.pa165.hockeymanager.facade;

import cz.fi.muni.pa165.hockeymanager.dto.HockeyPlayerDto;

import java.util.List;

/**
 * @author Lukas Machalek (485196)
 */
public interface HockeyPlayerFacade {
    /**
     * Create HockeyPlayer in database.
     *
     * @param hockeyPlayerDto HockeyPlayer to be saved in database.
     * @return Id of the newly saved player.
     */
    Long create(HockeyPlayerDto hockeyPlayerDto);

    /**
     * Delete HockeyPlayer from database by it's id.
     *
     * @param hockeyPlayerDto hockeyPlayerDto of HockeyPlayer you want to delete.
     */
    void remove(HockeyPlayerDto hockeyPlayerDto);

    /**
     * Update HockeyPlayer in database.
     *
     * @param hockeyPlayerDto Dto of HockeyPlayer that we want to update.
     */
    void update(HockeyPlayerDto hockeyPlayerDto);

    /**
     * Finds player by id
     *
     * @param id of the player
     * @return player
     */
    HockeyPlayerDto findById(Long id);

    /**
     * Get List of all HockeyPlayers saved in database.
     *
     * @return List of HockeyPlayer.
     */
    List<HockeyPlayerDto> findAll();

}
