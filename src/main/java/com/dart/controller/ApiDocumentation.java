package com.dart.controller;

/**
 * Author: Dmitry Artemenko
 * Date: 29.10.17
 * Time: 1:09
 *
 * @author Dmitry Artemenko
 */
public interface ApiDocumentation {

    String PARSE_API_DESCRIPTION = "parsing endpoint";
    String PARSE_API_VALUE = "Endpoint for product parsing and retrieving parse operations";
    String PARSE_API_LIST_OPERATION = "Get list of parsed operations";
    String PARSE_API_ID_OPERATION = "Get parse operation by id";
    String PARSE_API_PARSE_OPERATION = "Parse product data by specific product code and returns this parse operation";

    String PRODUCT_REVIEW_API_DESCRIPTION = "product reviews endpoint";
    String PRODUCT_REVIEW_API_VALUE = "Endpoint for interaction with product reviews";
    String PRODUCT_REVIEW_API_LIST_OPERATION = "Get list of all products reviews";
    String PRODUCT_REVIEW_API_LIST_BY_PRODUCT_CODE_OPERATION = "Get list of reviews by specific product id";
    String PRODUCT_REVIEW_API_ID_OPERATION = "Get product review by id";
    String PRODUCT_REVIEW_API_LIST_BY_PARSE_ENTRY_ID = "Get product review by parse operation id";

    String PRODUCT_API_DESCRIPTION = "products endpoint";
    String PRODUCT_API_VALUE = "Endpoint for interaction with products";
    String PRODUCT_API_LIST_OPERATION = "Get list of all products";
    String PRODUCT_API_BY_PRODUCT_CODE_OPERATION = "Get product by product code";
    String PRODUCT_API_BY_PRODUCT_ID = "Get product by product id";
}
