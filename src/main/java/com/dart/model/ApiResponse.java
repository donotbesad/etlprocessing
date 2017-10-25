package com.dart.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by dart on 26.10.17.
 */

@Data
@Accessors(chain = true)
public class ApiResponse {

    private Object payload;

    public ApiResponse(Object payload) {
        this.payload = payload;
    }
}
