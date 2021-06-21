package guru.springframework.services.jpaservices;

import guru.springframework.domain.CartDetail;
import guru.springframework.domain.CartDetail;
import guru.springframework.services.interfaces.CartDetailService;
import guru.springframework.services.interfaces.CartDetailService;
import guru.springframework.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import java.util.List;

@Service
@Profile("jpadao")
public class CartDetailServiceJpaDAOImpl implements CartDetailService {
    private EntityManagerFactory emf;
    private EncryptionService encryptionService;

    @Autowired  // injected third party encryption service from
    public void setEncryptionService(
            EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<?> listAll() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<CartDetail> carts = em.createQuery("from CartDetail", CartDetail.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return carts;
    }

    @Override
    public CartDetail getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        CartDetail cart = em.find(CartDetail.class, id);
        em.getTransaction().commit();
        em.close();
        return cart;
    }

    @Override
    public CartDetail saveOrUpdate(CartDetail domainObject) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        CartDetail savedCartDetail = em.merge(domainObject);
        em.getTransaction().commit();
        em.close();
        return savedCartDetail;
    }

    @Override
    public void deleteById(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(CartDetail.class, id));
        em.getTransaction().commit();
        em.close();

    }

}
