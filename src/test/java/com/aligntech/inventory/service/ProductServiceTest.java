package com.aligntech.inventory.service;

import com.aligntech.inventory.entities.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by mozg on 15.03.2018.
 * inventory
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {


    @Autowired
    private ProductService productService;

    @Test
    public void testFindAll() {
        Product product = createTestProduct("qwerty", "bla-bla");
        System.out.println(product);
        Collection<Product> products = productService.findAll();
        assertNotNull(products);
        assertTrue(products.size() > 0);
    }

    @Test
    public void testFindProductById() {
        Product product = createTestProduct("test", "aaa");

        Product found = productService.findProduct(product.getId());
        assertNotNull(found);
        assertEquals(product.getId(), found.getId());
    }

    @Test
    public void testFindProductsByName() {
        createTestProduct("qwerty", "bla-bla");
        Collection<Product> found = productService.findProductsByName("qwerty");
        assertNotNull(found);
        assertTrue(found.size() > 0);
        assertEquals(found.iterator().next().getName(), "qwerty");
    }

    @Test
    public void findLeftovers() {
        createTestProduct("test", "bla-bla");
        Collection<Product> leftOvers = productService.findLeftovers();
        assertNotNull(leftOvers);
        assertTrue(leftOvers.size() > 0);
        assertTrue(leftOvers.iterator().next().getQuantity() < 5);
    }

    @Test
    public void testDeleteProduct() {
        Product product = createTestProduct("test", "bla-bla");
        assertNotNull(product);
        assertNotNull(product.getId());
        productService.deleteProduct(product.getId());
    }

    private Product createTestProduct(String name, String brand) {
        Product product = new Product();
        product.setName(name);
        product.setBrand(brand);
        product.setPrice(10.00);
        product.setQuantity(1);
        return productService.saveOrUpdate(product);
    }

}
