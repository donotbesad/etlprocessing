package com.dart.utils;

import com.dart.domain.product.Product;
import com.dart.domain.product.ProductReview;
import com.dart.model.ProductDTO;
import com.dart.model.ProductReviewDTO;

/**
 * Author: Dmitry Artemenko
 * Date: 26.10.17
 * Time: 14:10
 *
 * @author Dmitry Artemenko
 */
public class ProductAdapter {

    public static ProductReviewDTO convert(ProductReview domain) {
        ProductReviewDTO dto = null;
        if (domain != null) {
            dto = new ProductReviewDTO();
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
            dto.setProductCode(Integer.toString(domain.getParseEntry().getProductCode()));
        }
        return dto;
    }

    public static ProductDTO convert(Product domain) {
        ProductDTO dto = null;
        if (domain != null) {
            dto = new ProductDTO();
            dto.setId(domain.getUuid().toString());
            dto.setModel(domain.getModel());
            dto.setBrand(domain.getBrand());
            dto.setType(domain.getType());
            dto.setCode(domain.getParseEntry().getProductCode());
        }
        return dto;
    }

}
