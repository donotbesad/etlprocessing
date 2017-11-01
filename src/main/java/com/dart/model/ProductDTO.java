package com.dart.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Author: Dmitry Artemenko
 * Date: 29.10.17
 * Time: 15:15
 *
 * @author Dmitry Artemenko
 */

@Data
@Accessors(chain = true)
public class ProductDTO {

    @ApiModelProperty(notes = "Product id")
    private String id;

    @ApiModelProperty(notes = "Product type")
    private String type;

    @ApiModelProperty(notes = "Product brand")
    private String brand;

    @ApiModelProperty(notes = "Product model")
    private String model;
}
