package com.aligntech.inventory.dto;

import com.fasterxml.jackson.annotation.JsonRootName;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

/**
 * Created by mozg on 13.03.2018.
 * inventory
 */
@JsonRootName(value = "product")
public class ProductDto {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String brand;

    @NotNull
    @Positive
    private Double price;

    @NotNull
    @PositiveOrZero
    private Integer quantity;

    public ProductDto() {}

    public ProductDto(Long id, String name, String brand, Double price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
    }

    /* Можно, конечно, использовать lombok, но можно и не использовать :) */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductDto {" +
                " id = " + id +
                ", name = '" + name + '\'' +
                ", brand = '" + brand + '\'' +
                ", price = " + price +
                ", quantity = " + quantity +
                " }";
    }
}
