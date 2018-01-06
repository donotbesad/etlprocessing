package com.dart.api.service;

import com.dart.domain.DomainObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * Author: Dmitry Artemenko
 * Date: 20.10.17
 * Time: 14:07
 *
 * @author Dmitry Artemenko
 */

public interface BaseService<T extends DomainObject> {

    Page<T> findAll(Pageable pageable);

    T findOne(UUID uuid);

    T save(T entity);

    void deleteAll();
}
