package cz.fi.muni.pa165.hockeymanager.dao;

import cz.fi.muni.pa165.hockeymanager.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Matus Jarkovic (456441)
 */
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(User user) {
        em.persist(user);
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public void remove(User user) {
        em.remove(user);
    }
}
