package com.aligntech.inventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mozg on 13.03.2018.
 * inventory
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1524814133669654946L;

    public ProductNotFoundException(String exception) {
        super(exception);
    }

}
