package guru.springframework.services.jpaservices;

import guru.springframework.domain.Order;
import guru.springframework.domain.Order;
import guru.springframework.domain.OrderLine;
import guru.springframework.services.interfaces.OrderService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Service
@Profile("jpadao")
public class OrderServiceJpaDAOImpl implements OrderService {
    private EntityManagerFactory emf;

    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<?> listAll() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Order> orders = em.createQuery("from Order", Order.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return orders;
    }

    @Override
    public Order getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Order order = em.find(Order.class, id);
        em.getTransaction().commit();
        em.close();
        return order;
    }

    @Override
    public Order saveOrUpdate(Order domainObject) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Order savedOrder = em.merge(domainObject);
        em.getTransaction().commit();
        em.close();
        return savedOrder;
    }

    @Override
    public void deleteById(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("delete from OrderLine where OrderLine.order_id = " + id, OrderLine.class);
        em.remove(em.find(Order.class, id));
        em.getTransaction().commit();
        em.close();

    }
}
