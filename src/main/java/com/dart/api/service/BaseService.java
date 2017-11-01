package com.dart.api.service;

import com.dart.domain.DomainObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

/**
 * Author: Dmitry Artemenko
 * Date: 20.10.17
 * Time: 14:07
 *
 * @author Dmitry Artemenko
 */

public interface BaseService<T extends DomainObject> {

    Iterable<T> findAll(Sort sort);

    Page<T> findAll(Pageable pageable);

    T findOne(UUID uuid);

    T insert(T entity);

    T update(T entity);

    long count();

    void delete(UUID uuid);

    void delete(T entity);

    void delete(List<T> entities);

    void deleteAll();
}
