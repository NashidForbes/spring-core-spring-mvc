package guru.springframework.services.mapservices;

import guru.springframework.domain.Cart;
import guru.springframework.domain.CartDetail;
import guru.springframework.domain.DomainObject;
import guru.springframework.services.interfaces.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Profile("map")
public class CartServiceImpl extends AbstractMapService implements CartService {
    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Cart getById(Integer id) {
        return (Cart) super.getById(id);
    }

    @Override
    public Cart saveOrUpdate(Cart user) {
        if (user.getId() == null) {
            user.setId(getNextId());
        }
        domainMap.put(user.getId(), user);
        return (Cart) domainMap.get(user.getId());
    }

    @Override
    public void deleteById(Integer id) {
        log.info("Carts initial size: " + String.valueOf(domainMap.size()));
        domainMap.remove(id);
        log.info("Deleted Cart id: " + id);
        log.info("Carts post-delete size: " + String.valueOf(domainMap.size()));
    }

    private Integer getNextId() {
        Integer nextInt = domainMap.size() + 1;
        return nextInt;
    }

    @Override
    protected void loadDomainObjects() {
    }

    @Override
    public List<CartDetail> listAllCartDetailsById(String id) {
        return null;
    }
}
