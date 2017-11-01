package com.dart.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;

/**
 * Author: Dmitry Artemenko
 * Date: 26.10.17
 * Time: 14:09
 *
 * @author Dmitry Artemenko
 */

@Data
@Accessors(chain = true)
public class ApiResponse {

    private Object payload;
    private Status status;

    public ApiResponse(Object payload) {
        validate(payload);
        this.payload = payload;
    }

    private void validate(Object payload) {
        if (payload == null || payload instanceof Collection
                && CollectionUtils.isEmpty((Collection) payload)) {
            this.status = Status.NOT_FOUND;
        } else {
            this.status = Status.SUCCESS;
        }
    }

    private enum Status {
        @JsonProperty("success")
        SUCCESS("success"),
        @JsonProperty("fail")
        FAIL("fail"),
        @JsonProperty("not found")
        NOT_FOUND("not found");

        private String label;

        Status(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }


        @Override
        public String toString() {
            return getLabel();
        }
    }
}
