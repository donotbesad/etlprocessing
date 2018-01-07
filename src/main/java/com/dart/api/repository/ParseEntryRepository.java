package com.dart.api.repository;

import com.dart.domain.ParseEntry;
import org.springframework.stereotype.Repository;

/**
 * Repository for database interaction with Parse Entry domain object
 *
 * Author: Dmitry Artemenko
 * Date: 20.10.17
 * Time: 14:07
 *
 * @author Dmitry Artemenko
 */

@Repository
public interface ParseEntryRepository extends BaseRepository<ParseEntry> {

    /**
     * Finding first Parse Entry domain object from database by
     * product code and created date which sorted in desc order
     *
     * @param productCode of specific product
     * @return Parse Entry domain object
     */
    ParseEntry findFirstByProductCodeOrderByCreatedDateDesc(int productCode);
}
