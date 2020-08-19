package tacos.web.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tacos.entity.Order;
import tacos.repository.OrderRepository;

@RestController
@RequestMapping(path = "/order",produces = "application/json")
@CrossOrigin(origins = "*")
public class OrderController {
    private OrderRepository orderRepository;
    @Autowired
    public OrderController(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Order> getAllOrder(){
        return orderRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, consumes =  "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Order saveOrder(@RequestBody Order order){
        return orderRepository.save(order);
    }
}
