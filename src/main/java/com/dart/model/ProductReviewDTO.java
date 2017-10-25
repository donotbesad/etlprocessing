package com.dart.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by dart on 26.10.17.
 */

@Data
@Accessors(chain = true)
public class ProductReviewDTO {

    private String id;
    private String parseEntryId;
    private String comment;
    private String author;
    private List<String> defects;
    private List<String> benefits;
    private LocalDateTime publishedDate;
    private int starsCount;
    private int likesCount;
    private int dislikesCount;
    private boolean recommended;
}
