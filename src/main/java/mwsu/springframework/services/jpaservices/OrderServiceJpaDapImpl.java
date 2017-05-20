package mwsu.springframework.services.jpaservices;

import mwsu.springframework.domain.Order;
import mwsu.springframework.services.OrderService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

/**
 * Created by bingyang.wei on 5/9/2017.
 */
@Service
@Profile("jpadao")
public class OrderServiceJpaDapImpl implements OrderService{

    private EntityManagerFactory emf;

    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Order> listAll() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("from Order", Order.class).getResultList();
    }

    @Override
    public Order getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Order.class, id);
    }

    @Override
    public Order saveOrUpdate(Order order) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Order savedOrder = em.merge(order);
        em.getTransaction().commit();
        return savedOrder;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Order.class,id));
        em.getTransaction().commit();
    }
}
