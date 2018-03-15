package com.aligntech.inventory;

import com.aligntech.inventory.entities.Product;
import com.aligntech.inventory.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by mozg on 15.03.2018.
 * inventory
 */
@Component
public class DataInitialization implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitialization.class);

    @Autowired
    private ProductService productService;

    /* Добавим пару элементов, чтобы после запуска у нас в базе уже что-то было */
    @Override
    public void run(String... args) throws Exception {
        logger.info("Data initialization...");
        Product product = new Product();
        product.setName("Test product 1");
        product.setBrand("Test brand");
        product.setPrice(20.00);
        product.setQuantity(2);

        logger.info("Saving a product: {}", product);
        productService.saveOrUpdate(product);

        Product product2 = new Product();
        product2.setName("Test product 2");
        product2.setBrand("Test brand2");
        product2.setPrice(10.00);
        product2.setQuantity(5);

        logger.info("Saving a product: {}", product2);
        productService.saveOrUpdate(product2);
    }

}
