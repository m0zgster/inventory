package com.aligntech.inventory.service.impl;

import com.aligntech.inventory.dao.ProductRepository;
import com.aligntech.inventory.entities.Product;
import com.aligntech.inventory.exception.InventoryApplicationException;
import com.aligntech.inventory.exception.ProductNotFoundException;
import com.aligntech.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by mozg on 13.03.2018.
 * inventory
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Collection<Product> findAll() {
        return productRepository.findAll(Sort.by(Sort.Order.by("name")));
    }

    @Override
    @Transactional
    public Product saveOrUpdate(Product product) {
        if (product == null) {
            throw new InventoryApplicationException("Product is null!");
        }

        return productRepository.saveAndFlush(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product findProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new ProductNotFoundException("Product with id " + id + " not found!");
        }

        return product.get();
    }

    @Override
    public Collection<Product> findProductsByName(String name) {
        Collection<Product> products = productRepository.findByNameContaining(name);
        if (products == null || products.isEmpty()) {
            throw new ProductNotFoundException("Product '" + name + "' not found!");
        }

        return products;
    }

    @Override
    public Collection<Product> findLeftovers() {
        return productRepository.findLeftovers();
    }
}
