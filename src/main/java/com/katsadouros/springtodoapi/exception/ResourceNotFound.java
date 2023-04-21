package com.katsadouros.springtodoapi.exception;

import java.util.HashMap;
import java.util.Map;

public final class ResourceNotFound extends RuntimeException {

    private final Map<String, String> details;

    public ResourceNotFound(String resource, String field, String value) {
        super("The requested resource was not found");
        this.details = new HashMap<>();
        this.details.put("resource", resource);
        this.details.put("field", field);
        this.details.put("value", value);
    }
}
