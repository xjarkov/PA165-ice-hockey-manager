package cz.fi.muni.pa165.hockeymanager.dao;

import cz.fi.muni.pa165.hockeymanager.entity.HumanPlayer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Matus Jarkovic (456441)
 */
@Repository
public class HumanPlayerDaoImpl implements HumanPlayerDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(HumanPlayer player) {
        em.persist(player);
    }

    @Override
    public List<HumanPlayer> findAll() {
        return em.createQuery("select player from HumanPlayer player", HumanPlayer.class).getResultList();
    }

    @Override
    public HumanPlayer findById(Long id) {
        return em.find(HumanPlayer.class, id);
    }

    @Override
    public void remove(HumanPlayer player) {
        em.remove(player);
    }
}
