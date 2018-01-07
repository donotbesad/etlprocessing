package com.dart.api.service;

import com.dart.domain.product.ProductReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

/**
 * Service for business logic on Review domain object
 *
 * Author: Dmitry Artemenko
 * Date: 23.10.17
 * Time: 14:07
 *
 * @author Dmitry Artemenko
 */
public interface ReviewService extends BaseService<ProductReview>, ParseService {

    /**
     * Find Product Reviews domain object by Parse Entry product code
     *
     * @param productCode of specific Product
     * @param sort in which result will be sorted
     * @return List of Product Reviews
     */
    List<ProductReview> findReviewsByProductCode(int productCode, Sort sort);

    /**
     * Find Product Reviews domain object by Parse Entry uuid
     *
     * @param uuid of Parse Entry
     * @param sort in which result will be sorted
     * @return List of Product Reviews
     */
    List<ProductReview> findReviewsByParseEntry(UUID uuid, Sort sort);

    /**
     * Find Product Reviews domain object by Parse Entry product code
     * and put it to Pageable view
     *
     * @param productCode of specific Product
     * @param pageable definition of Pageable view
     * @return Page of Product Reviews
     */
    Page<ProductReview> findReviewsByProductCode(int productCode, Pageable pageable);

    /**
     * Find Product Reviews domain object by Parse Entry uuid
     * and put it to Pageable view
     *
     * @param uuid of Parse Entry
     * @param pageable definition of Pageable view
     * @return Page of Product Reviews
     */
    Page<ProductReview> findReviewsByParseEntry(UUID uuid, Pageable pageable);
}
