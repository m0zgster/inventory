package com.aligntech.inventory.controllers;

import com.aligntech.inventory.Application;
import com.aligntech.inventory.entities.Product;
import com.aligntech.inventory.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by mozg on 15.03.2018.
 * inventory
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Application.class)
@AutoConfigureMockMvc
public class LeftOversControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductService productService;

    @Before
    public void setUp() {
        createTestProduct();
    }

    @Test
    public void testFindAllLeftOvers() throws Exception {
        mvc.perform(get("/leftovers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].quantity", lessThan(5)));
    }

    private void createTestProduct() {
        Product product = new Product();
        product.setName("Leftover");
        product.setBrand("Brand");
        product.setPrice(10.00);
        product.setQuantity(1);
        productService.saveOrUpdate(product);
    }

}
