package com.dart.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Created by dart on 26.10.17.
 */

@Data
@Accessors(chain = true)
public class ParseEntryDTO {

    @ApiModelProperty(notes = "Parse operation id")
    private String id;

    @ApiModelProperty(notes = "Parsing status")
    private String status;

    @ApiModelProperty(notes = "Parsing date")
    private LocalDateTime createdDate;

    @ApiModelProperty(notes = "Code of product for parse")
    private int productCode;

    @ApiModelProperty(notes = "Count of parsed elements")
    private int parsedCount;
}
