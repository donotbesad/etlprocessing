package com.dart.api.repository;

import com.dart.domain.DomainObject;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

/**
 * Created by dart on 20.10.17.
 */

@NoRepositoryBean
public interface BaseRepository<T extends DomainObject> extends PagingAndSortingRepository<T, UUID> {

}
