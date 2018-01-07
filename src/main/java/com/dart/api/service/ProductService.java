package com.dart.api.service;

import com.dart.domain.product.Product;

/**
 * Service for business logic on Product domain object
 *
 * Author: Dmitry Artemenko
 * Date: 29.10.17
 * Time: 10:09
 *
 * @author Dmitry Artemenko
 */
public interface ProductService extends BaseService<Product>, ParseService {

    /**
     * Find Product by Parse Entry product code
     *
     * @param productCode of specific product
     * @return product
     */
    Product findByProductCode(int productCode);
}
