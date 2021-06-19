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
        return em.createQuery("from Order", Order.class).getResultList();
    }

    @Override
    public List<OrderLine> listAllOrderLines(Integer orderId) {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("from OrderLine", OrderLine.class).getResultList();
    }

    @Override
    public OrderLine addOrderLine(Integer orderId, OrderLine orderLine) {


        return null;
    }

    @Override
    public Order getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Order.class, id);
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
