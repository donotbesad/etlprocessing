package com.dart.service;

import com.dart.api.service.ParseEntryService;
import com.dart.api.service.ProductReviewService;
import com.dart.api.service.ServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dart on 20.10.17.
 */

@Service
public class ServiceFacadeImpl implements ServiceFacade {

    @Autowired
    private ParseEntryService parseEntryService;

    @Autowired
    private ProductReviewService productReviewService;

    public ParseEntryService getParseEntryService() {
        return parseEntryService;
    }

    public ProductReviewService getProductReviewService() {
        return productReviewService;
    }
}
