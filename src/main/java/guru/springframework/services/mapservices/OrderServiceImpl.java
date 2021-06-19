package guru.springframework.services.mapservices;

import guru.springframework.domain.Order;
import guru.springframework.domain.DomainObject;
import guru.springframework.domain.Order;
import guru.springframework.domain.OrderLine;
import guru.springframework.services.interfaces.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Profile("map")
public class OrderServiceImpl extends AbstractMapService  implements OrderService {

    protected Map<Integer, DomainObject> OrderLinesMap;


    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Order getById(Integer id) {
        return (Order) super.getById(id);
    }

    @Override
    public Order saveOrUpdate(Order order) {
        if (order.getId() == null) {
            order.setId(getNextId());
        }
        domainMap.put(order.getId(), order);
        return (Order) domainMap.get(order.getId());
    }

    @Override
    public void deleteById(Integer id) {
        log.info("Orders initial size: " + String.valueOf(domainMap.size()));
        domainMap.remove(id);
        log.info("Deleted Order id: " + id);
        log.info("Orders post-delete size: " + String.valueOf(domainMap.size()));
    }

    private Integer getNextId() {
        Integer nextInt = domainMap.size() + 1;
        return nextInt;
    }

    @Override
    protected void loadDomainObjects() {

    }

    @Override
    public List<OrderLine> listAllOrderLines(Integer orderId) {
        return null;
    }

    @Override
    public OrderLine addOrderLine(Integer orderId, OrderLine orderLine) {
        Order orderItem = getById(orderId);
        orderItem.getOrderLines().add(orderLine);
        orderLine.setOrder(orderItem);
        Integer itemIndex = orderItem.getOrderLines().indexOf(orderLine);
        log.info("Order line item with id: " + orderLine.getId() + " added.");
        return  orderItem.getOrderLines().get(itemIndex);
    }
}
