package com.dart.api.repository;

import com.dart.domain.DomainObject;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

/**
 * Author: Dmitry Artemenko
 * Date: 20.10.17
 * Time: 14:07
 *
 * @author Dmitry Artemenko
 */

@NoRepositoryBean
public interface BaseRepository<T extends DomainObject> extends PagingAndSortingRepository<T, UUID> {

}
