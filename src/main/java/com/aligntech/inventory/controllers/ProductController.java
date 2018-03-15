package com.aligntech.inventory.controllers;

import com.aligntech.inventory.dto.ProductDto;
import com.aligntech.inventory.entities.Product;
import com.aligntech.inventory.exception.ProductNotFoundException;
import com.aligntech.inventory.service.ProductService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Created by mozg on 13.03.2018.
 * inventory
 *
 * Я не стал сильно мудрить, так как боялся не успеть всё сделать в срок. Местами упростил себе задачу.
 */
@RestController
@RequestMapping("products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ProductService productService;
    private ModelMapper mapper;

    public ProductController(ProductService productService, ModelMapper mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<ProductDto> findAll() {
        logger.info("findAll");

        Collection<Product> products = productService.findAll();
        if (!products.isEmpty()) {
            logger.info("Found {} leftovers", products.size());
            return products.stream()
                    .map(p -> mapper.map(p, ProductDto.class))
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        return Collections.emptyList();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto findOne(@PathVariable("id") Long id) {
        logger.info("Find product by id {}", id);
        return mapper.map(productService.findProduct(id), ProductDto.class);
    }

    /* Возможно, я неправильно понял пункт Find product by name... */
    @GetMapping(value = "/search")
    @ResponseStatus(HttpStatus.OK)
    public Collection<ProductDto> searchProducts(@RequestParam(value = "name") String name) {
        logger.info("Search product '{}'", name);

        if (!StringUtils.isEmpty(name)) {
            Collection<Product> products = productService.findProductsByName(name);
            logger.info("Found {} products", products.size());

            return products.stream()
                    .map(p -> mapper.map(p, ProductDto.class))
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        return Collections.emptyList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> create(@Valid @RequestBody ProductDto productDto) {
        logger.info("Create new product: {}", productDto);

        Product product = productService.saveOrUpdate(mapper.map(productDto, Product.class));
        logger.info("Product has been created: {}", product);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @Valid @RequestBody ProductDto productDto) {
        logger.info("Update product: {}", productDto);

        //возможно, не самый красивый вариант :)
        Product product = productService.findProduct(id);   //бросим ошибку, если не нашли
        if (!product.getId().equals(productDto.getId())) {
            throw new ProductNotFoundException("Product not found");
        }

        product = productService.saveOrUpdate(mapper.map(productDto, Product.class));
        logger.info("Product has been updated: {}", product);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        logger.info("Delete product with id: {}", id);
        productService.deleteProduct(id);
    }

}
