package com.dart.api.repository;

import com.dart.domain.product.Product;
import org.springframework.stereotype.Repository;

/**
 * Repository for database interaction with Product domain object
 *
 * Author: Dmitry Artemenko
 * Date: 29.10.17
 * Time: 10:50
 *
 * @author Dmitry Artemenko
 */

@Repository
public interface ProductRepository extends BaseRepository<Product> {

    /**
     * Find Product domain object by Parse Entry product code
     *
     * @param productCode of specific code
     * @return Product domain object
     */
    Product findByParseEntryProductCode(int productCode);
}
