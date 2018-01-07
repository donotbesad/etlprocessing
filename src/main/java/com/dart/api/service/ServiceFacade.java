package com.dart.api.service;

/**
 * Implementation of Facade pattern
 *
 * Author: Dmitry Artemenko
 * Date: 20.10.17
 * Time: 14:07
 *
 * @author Dmitry Artemenko
 */
public interface ServiceFacade {

    /**
     * Get Parse Entry Service
     *
     * @return Parse Entry Service
     */
    ParseEntryService getParseEntryService();

    /**
     * Get Review Service
     *
     * @return Review Service
     */
    ReviewService getReviewService();

    /**
     * Get Product Service
     *
     * @return Product Service
     */
    ProductService getProductService();

    /**
     * Get Export Service
     * @return Export Service
     */
    ExportService getExportService();
}
