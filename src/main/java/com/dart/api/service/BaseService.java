package com.dart.api.service;

import com.dart.domain.DomainObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * Base service for all services in application
 *
 * Author: Dmitry Artemenko
 * Date: 20.10.17
 * Time: 14:07
 *
 * @author Dmitry Artemenko
 */

public interface BaseService<T extends DomainObject> {

    /**
     * Find all domain objects and put it to Pageable view
     *
     * @param pageable definition of Pageable view
     * @return Page of domain objects
     */
    Page<T> findAll(Pageable pageable);

    /**
     * Find one domain object by uuid
     *
     * @param uuid of domain object
     * @return domain object
     */
    T findOne(UUID uuid);

    /**
     * Save domain object to database
     *
     * @param entity of domain object
     * @return saved domain object
     */
    T save(T entity);

    /**
     * Delete all domain object of specific database table
     */
    void deleteAll();
}
