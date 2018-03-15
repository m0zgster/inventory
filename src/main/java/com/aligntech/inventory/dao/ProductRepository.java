package com.aligntech.inventory.dao;

import com.aligntech.inventory.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

/**
 * Created by mozg on 13.03.2018.
 * inventory
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    //предполагаем, что у нас могут быть продукты с одинаковыми именами (например, у разных брендов)
    Collection<Product> findByNameContaining(String name);

    @Query("select p from Product p where p.quantity < 5")
    Collection<Product> findLeftovers();

}
