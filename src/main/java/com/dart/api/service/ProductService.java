package com.dart.api.service;

import com.dart.domain.product.Product;

/**
 * Author: Dmitry Artemenko
 * Date: 29.10.17
 * Time: 10:09
 *
 * @author Dmitry Artemenko
 */
public interface ProductService extends BaseService<Product>, ParseService {

    Product findByProductCode(int productCode);
}
