package com.dart.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Created by dart on 26.10.17.
 */

@Data
@Accessors(chain = true)
public class ParseEntryDTO {

    private String id;
    private String status;
    private LocalDateTime createdDate;
    private int productCode;
    private int parsedCount;
}
