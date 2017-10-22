package com.dart.api.repository;

import com.dart.domain.ParseEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by dart on 20.10.17.
 */

@Repository
public interface SearchEntryRepository extends CrudRepository<ParseEntry, UUID> {
}
