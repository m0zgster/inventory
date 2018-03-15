package com.aligntech.inventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mozg on 14.03.2018.
 * inventory
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InventoryApplicationException extends RuntimeException {

    private static final long serialVersionUID = -8504266813644974091L;

    public InventoryApplicationException(String message) {
        super(message);
    }

    public InventoryApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InventoryApplicationException(Throwable cause) {
        super(cause);
    }


}
