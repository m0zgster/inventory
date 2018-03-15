package com.aligntech.inventory.controllers;

import com.aligntech.inventory.Application;
import com.aligntech.inventory.dto.ProductDto;
import com.aligntech.inventory.entities.Product;
import com.aligntech.inventory.exception.ProductNotFoundException;
import com.aligntech.inventory.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collection;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductService productService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testFindById() throws Exception {
        Product product = createTestProduct();
        assertNotNull(product);

        mvc.perform(get("/products/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(product.getId().intValue())));
    }

    @Test
    public void testFindAll() throws Exception {
        mvc.perform(get("/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testSearch() throws Exception {
        mvc.perform(get("/products/search?name=Test")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test(expected = ProductNotFoundException.class)
    public void testDeleteById() throws Exception {
        Product product = createTestProduct();
        assertNotNull(product);

        mvc.perform(delete("/products/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        productService.findProduct(product.getId());
    }

    @Test
    public void testCreateProduct() throws Exception {
        ProductDto dto = createDto(null, "Create product");

        mvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Collection<Product> products = productService.findAll();
        assertNotNull(products);
        assertTrue(products.size() > 0);

        Long id = products.iterator().next().getId();
        ProductDto dto = createDto(id, "Update product");

        mvc.perform(put("/products/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    private ProductDto createDto(Long id, String name) {
        return new ProductDto(id, name, "Brand Brand", 5.00, 10);
    }

    private Product createTestProduct() {
        Product product = new Product();
        product.setName("Find one product");
        product.setBrand("Brand");
        product.setPrice(10.00);
        product.setQuantity(10);
        return productService.saveOrUpdate(product);
    }

}
