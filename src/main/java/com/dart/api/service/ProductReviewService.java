package com.dart.api.service;

import com.dart.domain.ParseEntry;
import com.dart.domain.product.ProductReview;
import org.jsoup.select.Elements;

/**
 * Created by dart on 23.10.17.
 */
public interface ProductReviewService extends BaseService<ProductReview> {

    void extract(ParseEntry toParse, ParseEntry existing) throws Exception;

    int transform(ParseEntry toParse, ParseEntry existing, Elements elements);
}
