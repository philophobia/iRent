package se.irent.controller;

import org.springframework.web.bind.annotation.*;
import se.irent.entity.Administrator;
import se.irent.entity.Order;
import se.irent.service.AdministratorServiceImpl;
import se.irent.service.OrderServiceImpl;
import se.irent.service.UserServiceImpl;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/v1/management")
public class ManagementController {
    @Resource
    private OrderServiceImpl orderService;
    @Resource
    private AdministratorServiceImpl adminService;
    @Resource
    private UserServiceImpl userService;

    @RequestMapping(value = "/login")
    public Administrator login(@RequestParam("id") String admin_id, @RequestParam("password") String pwd){
        Administrator cur_admin = adminService.findById(admin_id);
        if (pwd.equals(cur_admin.getPassword())) {
            cur_admin.setPassword("***");
            return cur_admin;
        }
        else
            return null;
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders(){
        return orderService.findAll();
    }

    @GetMapping("/orders")
    public Order getOneOrder(@RequestParam("id") String order_id){
        return orderService.findById(order_id);
    }

    @DeleteMapping("/orders")
    public void deleteOneOrder(@RequestParam("id") String order_id){
        orderService.deleteById(order_id);
    }

    @DeleteMapping("/users")
    public void deleteOneUser(@RequestParam("id") String user_id){
        userService.deleteById(user_id);
    }
}
