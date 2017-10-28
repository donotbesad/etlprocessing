package com.dart.api.repository;

import com.dart.domain.ParseEntry;

import java.util.List;

/**
 * Created by dart on 20.10.17.
 */

public interface ParseEntryRepository extends BaseRepository<ParseEntry> {

    ParseEntry findFirstByProductCodeOrderByCreatedDateDesc(int productCode);

    List<ParseEntry> findByProductCode(int productCode);
}
