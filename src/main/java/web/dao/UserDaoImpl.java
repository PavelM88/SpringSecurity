package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    private final EntityManagerFactory managerFactory;

    public UserDaoImpl(EntityManagerFactory managerFactory) {
        this.managerFactory = managerFactory;
    }

    @Override
    public void addUser(User user) {
        EntityManager em = managerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void updateUser(User user) {
        EntityManager em = managerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deleteUser(User user) {
        EntityManager em = managerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("delete from User u where u.id = :id")
                .setParameter("id", user.getId())
                .executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public User getUser(int id) {
        EntityManager em = managerFactory.createEntityManager();
        return em.find(User.class, id);
    }

    @Override
    public List<User> allUsers() {
        EntityManager em = managerFactory.createEntityManager();
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserByName(String userName) {
        EntityManager em = managerFactory.createEntityManager();
        return em.createQuery("select u from User u where u.userName = :userName", User.class)
                .setParameter("userName", userName)
                .getSingleResult();
    }
}
