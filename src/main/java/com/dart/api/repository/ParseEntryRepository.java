package com.dart.api.repository;

import com.dart.domain.ParseEntry;
import org.springframework.stereotype.Repository;

/**
 * Created by dart on 20.10.17.
 */

@Repository
public interface ParseEntryRepository extends BaseRepository<ParseEntry> {

    ParseEntry findFirstByProductCodeOrderByCreatedDateDesc(int productCode);
}
