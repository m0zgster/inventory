package com.aligntech.inventory.dao;

import com.aligntech.inventory.entities.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by mozg on 15.03.2018.
 * inventory
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testFindByName() {
        Product product = createTestProduct();

        Collection<Product> collection = productRepository.findByNameContaining("Test product");

        assertNotNull(collection);
        assertTrue(collection.size() > 0);

        Product found = collection.iterator().next();

        assertEquals(found.getName(), product.getName());
    }

    @Test
    public void testFindLeftovers() {
        createTestProduct();

        Collection<Product> collection = productRepository.findLeftovers();

        assertNotNull(collection);
        assertTrue(collection.size() > 0);
    }

    private Product createTestProduct() {
        Product product = new Product();
        product.setName("Test product");
        product.setBrand("Test brand");
        product.setPrice(10.00);
        product.setQuantity(1);
        entityManager.persist(product);
        entityManager.flush();
        return product;
    }

}
