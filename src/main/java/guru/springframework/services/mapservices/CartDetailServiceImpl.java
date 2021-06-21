package guru.springframework.services.mapservices;

import guru.springframework.domain.Cart;
import guru.springframework.domain.CartDetail;
import guru.springframework.domain.DomainObject;
import guru.springframework.services.interfaces.CartDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Profile("map")
public class CartDetailServiceImpl extends AbstractMapService implements CartDetailService {
    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public CartDetail getById(Integer id) {
        return (CartDetail) super.getById(id);
    }

    @Override
    public CartDetail saveOrUpdate(CartDetail user) {
        if (user.getId() == null) {
            user.setId(getNextId());
        }
        domainMap.put(user.getId(), user);
        return (CartDetail) domainMap.get(user.getId());
    }

    @Override
    public void deleteById(Integer id) {
        log.info("CartDetails initial size: " + String.valueOf(domainMap.size()));
        domainMap.remove(id);
        log.info("Deleted CartDetail id: " + id);
        log.info("CartDetails post-delete size: " + String.valueOf(domainMap.size()));
    }

    private Integer getNextId() {
        Integer nextInt = domainMap.size() + 1;
        return nextInt;
    }

    @Override
    protected void loadDomainObjects() {
    }
}
