package com.dart.api.repository;

import com.dart.domain.product.ProductReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by dart on 25.10.17.
 */
public interface ProductReviewRepository extends BaseRepository<ProductReview> {

    Page<ProductReview> findByParseEntryProductCode(int productCode, Pageable pageable);
}
