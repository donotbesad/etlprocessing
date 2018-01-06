package com.dart.service;

import com.dart.api.repository.BaseRepository;
import com.dart.api.service.BaseService;
import com.dart.domain.DomainObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Author: Dmitry Artemenko
 * Date: 20.10.17
 * Time: 14:10
 *
 * @author Dmitry Artemenko
 */

@Transactional
abstract class BaseServiceImpl<T extends DomainObject, E extends BaseRepository<T>> implements BaseService<T> {

    @Autowired
    private E repository;

    @Override
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public T findOne(UUID uuid) {
        return repository.findOne(uuid);
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    E getRepository() {
        return repository;
    }
}
