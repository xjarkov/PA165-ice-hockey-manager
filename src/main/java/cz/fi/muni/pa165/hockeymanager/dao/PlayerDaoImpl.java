package cz.fi.muni.pa165.hockeymanager.dao;

import cz.fi.muni.pa165.hockeymanager.entity.Player;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Lukas Machalek (485196)
 */
public class PlayerDaoImpl implements PlayerDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Player player) {
        em.persist(player);
    }

    @Override
    public void remove(Player player) {
        em.remove(player);
    }

    @Override
    public List<Player> findAll() {
        return em.createQuery("select p from Player p").getResultList();
    }

    @Override
    public Player findById(Long id) {
        return em.find(Player.class, id);
    }
}
