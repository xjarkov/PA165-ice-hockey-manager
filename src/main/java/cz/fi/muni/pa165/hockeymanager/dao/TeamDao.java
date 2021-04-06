package cz.fi.muni.pa165.hockeymanager.dao;

import cz.fi.muni.pa165.hockeymanager.entity.Team;

import java.util.List;
/*
* Team table class
*
* @author Kristian Kosorin (456620)
* */
public interface TeamDao {
    /*
    * Adds new entity to Team table
    *
    * @param entity
    * */
    void create(Team entity);

    /*
    * Removes entity from Team table
    *
    * @param team
    * */
    void remove(Team team);

    /*
    * Find and return Team by name.
    *
    * @param name
    * */
    Team findByName(String name);

    /*
    * Find and return Team by id.
    *
    * @param id
    * */
    Team findById(Long id);

    /*
    * Find and return all Teams.
    * */
    List<Team> findAll();


}
