package tacos.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tacos.Ingredient;
import tacos.Order;
import tacos.data.OrderRepository;

import java.util.Optional;

@RestController
@RequestMapping(path = "/orders", produces = "application/json")
@CrossOrigin(origins = "*")
public class OrderController {
    private OrderRepository orderRepo;
    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @GetMapping
    public Iterable<Order> getAllOrders() {
        return orderRepo.findAll();
    }
    @GetMapping("/{id}")
    public Order orderById(@PathVariable("id") String id) {
        Optional<Order> optIngredient = orderRepo.findById(Long.parseLong(id));
        if (optIngredient.isPresent()) {
            return optIngredient.get();
        }
        return null;
    }
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Order postOrder(@RequestBody Order order) {
        return orderRepo.save(order);
    }
}
