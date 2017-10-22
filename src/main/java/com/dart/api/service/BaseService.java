package com.dart.api.service;

import com.dart.domain.Identifiable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

/**
 * Created by dart on 20.10.17.
 */

public interface BaseService<T extends Identifiable> {

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
