package com.aligntech.inventory.controllers;

import com.aligntech.inventory.dto.ProductDto;
import com.aligntech.inventory.entities.Product;
import com.aligntech.inventory.service.ProductService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;


/**
 * Created by mozg on 14.03.2018.
 * inventory
 *
 * Я, возможно, неправильно понял что значит сделать вывод таких продуктов на отдебной странице.
 * Сделал отдельный контроллер со своим url
 */
@RestController
@RequestMapping("leftovers")
public class LeftOverController {

    private static final Logger logger = LoggerFactory.getLogger(LeftOverController.class);

    private ProductService productService;
    private ModelMapper mapper;

    public LeftOverController(ProductService productService, ModelMapper mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Collection<ProductDto> findAllLeftOvers() {
        logger.info("Find all leftovers");

        Collection<Product> leftOvers = productService.findLeftovers();
        if (!leftOvers.isEmpty()) {
            logger.info("Found {} leftovers", leftOvers.size());
            return leftOvers.stream()
                    .map(p -> mapper.map(p, ProductDto.class))
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        return Collections.emptyList();
    }

}
