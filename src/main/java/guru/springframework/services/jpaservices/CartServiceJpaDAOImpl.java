package guru.springframework.services.jpaservices;

import guru.springframework.domain.Cart;
import guru.springframework.domain.CartDetail;
import guru.springframework.services.interfaces.CartService;
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
public class CartServiceJpaDAOImpl implements CartService {
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
        List<Cart> carts = em.createQuery("from Cart", Cart.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return carts;
    }

    @Override
    public Cart getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Cart cart = em.find(Cart.class, id);
        em.getTransaction().commit();
        em.close();
        return cart;
    }

    @Override
    public Cart saveOrUpdate(Cart domainObject) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Cart savedCart = em.merge(domainObject);
        em.getTransaction().commit();
        em.close();
        return savedCart;
    }

    @Override
    public void deleteById(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Cart.class, id));
        em.getTransaction().commit();
        em.close();

    }

    @Override
    public List<CartDetail> listAllCartDetailsById(String id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT cd FROM CartDetail cd WHERE cd.cart.id = :cart_id");
        query.setParameter("cart_id", Integer.parseInt(id));
        List<CartDetail> cartDetails = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return cartDetails;
    }
}
