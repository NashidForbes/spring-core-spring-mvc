package guru.springframework.services.jpaservices;

import guru.springframework.domain.OrderLine;
import guru.springframework.domain.User;
import guru.springframework.services.interfaces.OrderLineService;
import guru.springframework.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Service
@Profile("jpadao")
public class OrderLineServiceJpaDAOImpl implements OrderLineService {
    private EntityManagerFactory emf;

    @Autowired  // injected third party encryption service from
    public void setEncryptionService(
            EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    private EncryptionService encryptionService;

    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<?> listAll() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("from OrderLine", OrderLine.class).getResultList();
    }

    @Override
    public OrderLine getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        return em.find(OrderLine.class, id);
    }

    @Override
    public OrderLine saveOrUpdate(OrderLine domainObject) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        OrderLine savedOrderLine = em.merge(domainObject);
        em.getTransaction().commit();
        em.close();
        return savedOrderLine;
    }

    @Override
    public void deleteById(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(OrderLine.class, id));
        em.getTransaction().commit();
        em.close();

    }

    @Override
    public List<OrderLine> listAllByOrderId(String orderId) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<OrderLine> orderLines = em.createQuery("from OrderLine where Order_Id = " + orderId,
                OrderLine.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return orderLines;
    }
}
