package guru.springframework.services.interfaces;

import guru.springframework.domain.Order;
import guru.springframework.domain.OrderLine;

import java.util.List;

public interface OrderService extends CRUDService<Order> {

  List<OrderLine> listAllOrderLines(Integer orderId);

  OrderLine addOrderLine(Integer orderId, OrderLine orderLine);
}
