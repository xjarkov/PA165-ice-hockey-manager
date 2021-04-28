package cz.fi.muni.pa165.hockeymanager.dao;

import cz.fi.muni.pa165.hockeymanager.entity.Team;

import java.util.List;
/**
 * @author Kristian Kosorin (456620)
 */
public interface TeamDao {
    /**
    * Adds new entity to Team table.
    *
    * @param entity Team entity to be added to DB.
    */
    void create(Team entity);

    /**
    * Removes entity from Team table.
    *
    * @param team Team entity to remove from DB.
    */
    void remove(Team team);

    /**
    * Find and return Team by name.
    *
    * @param name Name of team to find in DB.
    */
    Team findByName(String name);

    /**
    * Find and return Team by id.
    *
    * @param id Id of team to find in DB.
    */
    Team findById(Long id);

    /**
    * Find and return all Teams.
    */
    List<Team> findAll();

    /**
     * Update team in database.
     *
     * @param team Team to be updated
     */
    void update(Team team);
}
