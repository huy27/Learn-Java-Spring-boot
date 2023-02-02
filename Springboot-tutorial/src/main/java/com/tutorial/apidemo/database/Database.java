package com.tutorial.apidemo.database;

import com.tutorial.apidemo.models.Product;
import com.tutorial.apidemo.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//                Product product1 = new Product("Macbook Pro 15", 1000.0, "");
//                Product product2 = new Product("Iphone 14 ProMax", 2220.0, "");
//                logger.info("insert data " + productRepository.save(product1));
//                logger.info("insert data " + productRepository.save(product2));
            }
        };
    }
}
