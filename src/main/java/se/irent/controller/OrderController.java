package se.irent.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.irent.entity.Order;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Order> getAllOrders(){
        List<Order> allOrders = new ArrayList<>();
        return allOrders;
    }
}
