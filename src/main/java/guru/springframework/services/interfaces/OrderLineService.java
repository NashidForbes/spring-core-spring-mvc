package guru.springframework.services.interfaces;

import guru.springframework.domain.OrderLine;

import java.util.List;

public interface OrderLineService extends CRUDService<OrderLine> {
    List<OrderLine> listAllByOrderId(String orderId);
}
