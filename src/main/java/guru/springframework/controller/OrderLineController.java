package guru.springframework.controller;

import guru.springframework.domain.OrderLine;
import guru.springframework.services.interfaces.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/v1")
public class OrderLineController {
    private OrderLineService orderLineService;

    @Autowired
    public void setOrderLineService(OrderLineService orderLineService) {
        this.orderLineService = orderLineService;
    }

    @RequestMapping({"/orderline", "/orderline/list"})
    public String listAllOrderLines(Model Model) {
        List<OrderLine> allOrderLines = (List<OrderLine>) orderLineService.listAll();
        Model.addAttribute("orderlines", allOrderLines);
        return "orderlines/orderlines";

    }

    @RequestMapping({"/orderline/{orderid}", "/orderline/list/{orderid}"})
    public String listAllOrderLinesByOrderId(@PathVariable String orderid, Model Model) {
        List<OrderLine> allOrderLines = (List<OrderLine>) orderLineService.listAllByOrderId(orderid);
        Model.addAttribute("orderlines", allOrderLines);
        return "orderlines/orderlines";

    }

    @RequestMapping("/orderline/update/{id}")
    public String editOrderLine(@PathVariable String id, Model model) {
        OrderLine orderLine = orderLineService.getById(Integer.parseInt(id));
        model.addAttribute("orderline", orderLine);
        return "orderlines/orderlineform";
    }


    @RequestMapping("/orderline/get/{id}")
    public String findOrderLineById(@PathVariable String id, Model Model) {
        OrderLine orderLine = orderLineService.getById(Integer.parseInt(id));
        Model.addAttribute("orderline", orderLine);
        return "orderlines/orderline";

    }

    @RequestMapping("/orderline/new")
    public String newOrderLine(Model model) {
        model.addAttribute("orderline", new OrderLine());
        return "orderlines/orderlineform";
    }

    @RequestMapping(value = "/orderLine", method = RequestMethod.POST)
    public String saveOrUpdateOrderLine(OrderLine orderLine) {
        OrderLine savedOrderLine = orderLineService.saveOrUpdate(orderLine);
        return "redirect:/v1/orderline/get/" + savedOrderLine.getId();
    }

    // not functional, cascade type bounded to OrderLine object, merge and persist operations only.
    @RequestMapping(value = "/orderline/delete", method = RequestMethod.POST)
    public String editOrderLine(OrderLine orderLine) {
        orderLineService.deleteById(orderLine.getId());
        return "redirect:/v1/orderline";
    }
}
