package cz.fi.muni.pa165.hockeymanager.dao;

import cz.fi.muni.pa165.hockeymanager.entity.HockeyPlayer;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 * @author Lukas Machalek (485196)
 */
@Repository
public class HockeyPlayerDaoImpl implements HockeyPlayerDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(HockeyPlayer hockeyPlayer) {
        em.persist(hockeyPlayer);
    }

    @Override
    public void remove(HockeyPlayer hockeyPlayer) {
        em.remove(hockeyPlayer);
    }

    @Override
    public void update(HockeyPlayer hockeyPlayer) { em.merge(hockeyPlayer); }

    @Override
    public HockeyPlayer findById(Long id) {
        return em.find(HockeyPlayer.class, id);
    }

    @Override
    public List<HockeyPlayer> findAll() {
        return em.createQuery("select p from HockeyPlayer p", HockeyPlayer.class).getResultList();
    }
}
