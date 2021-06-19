package guru.springframework.controller;

import guru.springframework.domain.Order;
import guru.springframework.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/v1")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping({"/orders", "/order/list"})
    public String listAllOrders(Model Model) {
        List<Order> allOrders = (List<Order>) orderService.listAll();
        Model.addAttribute("allOrders", allOrders);
        return "orders";

    }

    @RequestMapping({"/orders/{id}/orderlines", "/order/{id}/orderlines/list"})
    public String listAllOrderLines(Model Model) {
        List<Order> allOrders = (List<Order>) orderService.listAll();
        Model.addAttribute("allOrders", allOrders);
        return "orders";

    }

    @RequestMapping("/order/update/{id}")
    public String editOrder(@PathVariable String id, Model model){
        Order order = orderService.getById(Integer.parseInt(id));

        model.addAttribute("order", order);

        return "orderform";
    }


    @RequestMapping("/order/get/{id}")
    public String findOrderById(@PathVariable String id, Model Model) { Order order = orderService.getById(Integer.parseInt(id));
        Model.addAttribute("order", order);
        return "order";

    }

    @RequestMapping("/order/new")
    public String newOrder(Model model){
        model.addAttribute("order", new Order());

        return "orderform";
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String saveOrUpdateOrder(Order order){
        Order savedOrder = orderService.saveOrUpdate(order);

        return "redirect:/v1/order/" + savedOrder.getId();
    }


    @RequestMapping(value = "/order/delete", method = RequestMethod.POST)
    public String editOrder(Order order){
        orderService.deleteById(order.getId());

        return "redirect:/v1/orders";
    }
}
