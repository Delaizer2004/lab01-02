package ua.edu.dmitro.lab01.controller;

import org.springframework.web.bind.annotation.*;
import ua.edu.dmitro.lab01.model.Product;
import java.util.*;

@RestController
@RequestMapping("/products")
public class ObjectController {

    private Map<Long, Product> products = new HashMap<>();
    private long counter = 1;

    @PostMapping
    public Product create(@RequestBody Product product) {
        product.setId(counter++);
        products.put(product.getId(), product);
        return product;
    }

    @GetMapping
    public Collection<Product> getAll() {
        return products.values();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return products.get(id);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Product existing = products.get(id);
        if (existing != null) {
            existing.setName(updatedProduct.getName());
            existing.setPrice(updatedProduct.getPrice());
        }
        return existing;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        products.remove(id);
        return "Deleted product with ID " + id;
    }
}
