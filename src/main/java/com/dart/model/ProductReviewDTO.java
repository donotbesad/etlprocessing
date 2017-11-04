package com.dart.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Author: Dmitry Artemenko
 * Date: 26.10.17
 * Time: 14:09
 *
 * @author Dmitry Artemenko
 */

@Data
@Accessors(chain = true)
public class ProductReviewDTO {

    public interface Fields {
        String ID = "id";
        String PARSE_ENTRY_ID = "parseEntryId";
        String PRODUCT_CODE = "productCode";
        String COMMENT = "comment";
        String AUTHOR = "author";
        String DEFECTS = "defects";
        String BENEFITS = "benefits";
        String PUBLISHED_DATE = "publishedDate";
        String STARS_COUNT = "starsCount";
        String LIKES_COUNT = "likesCount";
        String DISLIKES_COUNT = "dislikesCount";
        String RECOMMENDED = "recommended";
    }

    @ApiModelProperty(value = "Product review id")
    private String id;

    @ApiModelProperty(value = "Id of parse operation which parsed this review")
    private String parseEntryId;

    @ApiModelProperty(value = "Product code")
    private String productCode;

    @ApiModelProperty(value = "Comment from author")
    private String comment;

    @ApiModelProperty(value = "Author name")
    private String author;

    @ApiModelProperty(value = "Product defects")
    private List<String> defects;

    @ApiModelProperty(value = "Product benefits")
    private List<String> benefits;

    @ApiModelProperty(value = "Review publication date")
    private LocalDateTime publishedDate;

    @ApiModelProperty(value = "Product rating from 1 to 5")
    private int starsCount;

    @ApiModelProperty(value = "Count of users why likes this review")
    private int likesCount;

    @ApiModelProperty(value = "Count of users why don't like this review")
    private int dislikesCount;

    @ApiModelProperty(value = "Is this product recommended")
    private boolean recommended;
}
