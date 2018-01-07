package com.dart.api.service;

import com.dart.domain.ParseEntry;

/**
 * Service for implementing business logic on parse entry domain object
 *
 * Author: Dmitry Artemenko
 * Date: 20.10.17
 * Time: 14:07
 *
 * @author Dmitry Artemenko
 */
public interface ParseEntryService extends BaseService<ParseEntry> {

    /**
     * Get Parse Entry domain object by Product code
     *
     * @param productCode of specific Product
     * @return parsing operation
     */
    ParseEntry getByProductCode(int productCode);

    /**
     * Parsing operation on Product by specific Product code
     *
     * @param productCode of specific Product
     * @return parsing operation
     */
    ParseEntry parse(int productCode);

}
