package guru.springframework.services.mapservices;

import guru.springframework.domain.DomainObject;
import guru.springframework.domain.OrderLine;
import guru.springframework.domain.OrderLine;
import guru.springframework.services.interfaces.OrderLineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Profile("map")
public class OrderLineServiceImpl extends AbstractMapService implements OrderLineService {
    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public OrderLine getById(Integer id) {
        return (OrderLine) super.getById(id);
    }

    @Override
    public OrderLine saveOrUpdate(OrderLine user) {
        if (user.getId() == null) {
            user.setId(getNextId());
        }
        domainMap.put(user.getId(), user);
        return (OrderLine) domainMap.get(user.getId());
    }

    @Override
    public void deleteById(Integer id) {
        log.info("OrderLines initial size: " + String.valueOf(domainMap.size()));
        domainMap.remove(id);
        log.info("Deleted OrderLine id: " + id);
        log.info("OrderLines post-delete size: " + String.valueOf(domainMap.size()));
    }

    private Integer getNextId() {
        Integer nextInt = domainMap.size() + 1;
        return nextInt;
    }

    @Override
    protected void loadDomainObjects() {

    }

    @Override
    public List<OrderLine> listAllByOrderId(String orderId) {
        return null;
    }
}
