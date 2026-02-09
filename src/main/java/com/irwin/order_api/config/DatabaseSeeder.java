package com.irwin.order_api.config;
import com.irwin.order_api.entity.Product;
import com.irwin.order_api.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;

    public DatabaseSeeder(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (productRepository.count() == 0) {
            List<Product> products = List.of(
                    new Product("Laptop", "Gaming Laptop", 1500.0, true, null),
                    new Product("Mouse", "Wireless Mouse", 25.0, true, null),
                    new Product("Keyboard", "Mechanical Keyboard", 75.0, true, null)
            );
            productRepository.saveAll(products);
            System.out.println("Seeded products into the database : " + products);
        }
    }
}
