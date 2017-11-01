package com.dart.service;

import com.dart.api.service.ParseEntryService;
import com.dart.api.service.ProductService;
import com.dart.api.service.ReviewService;
import com.dart.api.service.ServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: Dmitry Artemenko
 * Date: 20.10.17
 * Time: 14:10
 *
 * @author Dmitry Artemenko
 */

@Service
public class ServiceFacadeImpl implements ServiceFacade {

    @Autowired
    private ParseEntryService parseEntryService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ProductService productService;

    public ParseEntryService getParseEntryService() {
        return parseEntryService;
    }

    public ReviewService getReviewService() {
        return reviewService;
    }

    @Override
    public ProductService getProductService() {
        return productService;
    }
}
