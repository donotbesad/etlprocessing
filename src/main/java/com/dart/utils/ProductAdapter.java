package com.dart.utils;

import com.dart.domain.product.ProductReview;
import com.dart.model.ProductReviewDTO;

/**
 * Created by dart on 26.10.17.
 */
public class ProductAdapter {

    public static ProductReviewDTO convert(ProductReview domain) {
        ProductReviewDTO dto = new ProductReviewDTO();
        if (domain != null) {
            dto.setId(domain.getUuid().toString());
            dto.setAuthor(domain.getAuthor());
            dto.setBenefits(domain.getBenefits());
            dto.setDefects(domain.getDefects());
            dto.setComment(domain.getComment());
            dto.setParseEntryId(domain.getParseEntry().getUuid().toString());
            dto.setPublishedDate(domain.getPublishedDate());
            dto.setRecommended(domain.isRecommended());
            dto.setStarsCount(domain.getStarsCount());
            dto.setLikesCount(domain.getLikesCount());
            dto.setDislikesCount(domain.getDislikesCount());
        }
        return dto;
    }
}
