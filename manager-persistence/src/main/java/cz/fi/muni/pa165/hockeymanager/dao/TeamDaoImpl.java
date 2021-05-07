package cz.fi.muni.pa165.hockeymanager.dao;

import cz.fi.muni.pa165.hockeymanager.entity.Team;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Petr Šopf (506511)
 */
@Repository
public class TeamDaoImpl implements TeamDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Team team) {
        em.persist(team);
    }

    @Override
    public void remove(Team team) {
        em.remove(team);
    }

    @Override
    public void update(Team team) {
        em.merge(team);
    }

    @Override
    public Team findById(Long id) {
        return em.find(Team.class, id);
    }

    @Override
    public Team findByName(String name) {
        return em.createQuery("select t from Team t where t.name = :name", Team.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<Team> findAll() {
        return em.createQuery("select t from Team t", Team.class).getResultList();
    }
}
