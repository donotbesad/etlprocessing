package com.dart.api.service;

import com.dart.domain.product.ProductReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * Author: Dmitry Artemenko
 * Date: 23.10.17
 * Time: 14:07
 *
 * @author Dmitry Artemenko
 */
public interface ReviewService extends BaseService<ProductReview>, ParseService {

    Page<ProductReview> findReviewsByProductCode(int productCode, Pageable pageable);

    Page<ProductReview> findReviewsByParseEntry(UUID uuid, Pageable pageable);
}
