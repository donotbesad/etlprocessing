package com.dart.api.service;

/**
 * Author: Dmitry Artemenko
 * Date: 20.10.17
 * Time: 14:07
 *
 * @author Dmitry Artemenko
 */
public interface ServiceFacade {

    ParseEntryService getParseEntryService();

    ReviewService getReviewService();

    ProductService getProductService();
}
