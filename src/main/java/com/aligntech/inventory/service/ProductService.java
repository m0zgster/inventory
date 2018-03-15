package com.aligntech.inventory.service;

import com.aligntech.inventory.entities.Product;

import java.util.Collection;

/**
 * Created by mozg on 13.03.2018.
 * inventory
 */
public interface ProductService {

    Collection<Product> findAll();

    Product saveOrUpdate(Product product);

    void deleteProduct(Long id);

    Product findProduct(Long id);

    Collection<Product> findProductsByName(String name);

    Collection<Product> findLeftovers();

}
