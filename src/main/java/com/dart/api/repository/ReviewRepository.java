package com.dart.api.repository;

import com.dart.domain.product.ProductReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * Author: Dmitry Artemenko
 * Date: 25.10.17
 * Time: 13:27
 *
 * @author Dmitry Artemenko
 */
public interface ReviewRepository extends BaseRepository<ProductReview> {

    Page<ProductReview> findByParseEntryProductCode(int productCode, Pageable pageable);

    Page<ProductReview> findByParseEntryUuid(UUID uuid, Pageable pageable);
}
