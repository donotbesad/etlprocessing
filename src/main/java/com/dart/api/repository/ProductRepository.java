package com.dart.api.repository;

import com.dart.domain.product.Product;
import org.springframework.stereotype.Repository;

/**
 * Author: Dmitry Artemenko
 * Date: 29.10.17
 * Time: 10:50
 *
 * @author Dmitry Artemenko
 */

@Repository
public interface ProductRepository extends BaseRepository<Product> {

    Product findByParseEntryProductCode(int productCode);
}
