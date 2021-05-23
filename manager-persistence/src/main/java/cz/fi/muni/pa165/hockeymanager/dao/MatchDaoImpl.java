package cz.fi.muni.pa165.hockeymanager.dao;

import cz.fi.muni.pa165.hockeymanager.entity.Match;
import cz.fi.muni.pa165.hockeymanager.entity.Team;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Kristian Kosorin (456620)
 */
@Repository
public class MatchDaoImpl implements MatchDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Match match) {
        em.persist(match);
    }

    @Override
    public void remove(Match match) {
        em.remove(em.contains(match) ? match : em.merge(match));
    }

    @Override
    public void update(Match match) {
        em.merge(match);
    }

    @Override
    public Match findById(Long id) {
        return em.find(Match.class, id);
    }

    @Override
    public List<Match> findByTeam(Team team) {
        try {
            return em.createQuery("select m from Match m where m.homeTeam = :team or m.visitingTeam = :team ", Match.class)
                    .setParameter("team", team).getResultList();
        } catch (NoResultException nfr) {
            return null;
        }
    }

    @Override
    public List<Match> findAll() {
        return em.createQuery("select m from Match m", Match.class).getResultList();
    }
}
