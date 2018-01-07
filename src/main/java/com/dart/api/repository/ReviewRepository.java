package com.dart.api.repository;

import com.dart.domain.product.ProductReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository for database interaction with Product domain object
 *
 * Author: Dmitry Artemenko
 * Date: 25.10.17
 * Time: 13:27
 *
 * @author Dmitry Artemenko
 */

@Repository
public interface ReviewRepository extends BaseRepository<ProductReview> {

    /**
     * Find Product Reviews domain object by Parse Entry product code
     *
     * @param productCode of specific Product
     * @param sort in which result will be sorted
     * @return List of Product Reviews
     */
    List<ProductReview> findByParseEntryProductCode(int productCode, Sort sort);

    /**
     * Find Product Reviews domain object by Parse Entry uuid
     *
     * @param uuid of Parse Entry
     * @param sort in which result will be sorted
     * @return List of Product Reviews
     */
    List<ProductReview> findByParseEntryUuid(UUID uuid, Sort sort);

    /**
     * Find Product Reviews domain object by Parse Entry product code
     * and put it to Pageable view
     *
     * @param productCode of specific Product
     * @param pageable definition of Pageable view
     * @return Page of Product Reviews
     */
    Page<ProductReview> findByParseEntryProductCode(int productCode, Pageable pageable);

    /**
     * Find Product Reviews domain object by Parse Entry uuid
     * and put it to Pageable view
     *
     * @param uuid of Parse Entry
     * @param pageable definition of Pageable view
     * @return Page of Product Reviews
     */
    Page<ProductReview> findByParseEntryUuid(UUID uuid, Pageable pageable);
}
