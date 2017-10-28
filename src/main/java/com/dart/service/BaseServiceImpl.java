package com.dart.service;

import com.dart.api.repository.BaseRepository;
import com.dart.api.service.BaseService;
import com.dart.domain.DomainObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by dart on 20.10.17.
 */

@Transactional
abstract class BaseServiceImpl<T extends DomainObject, E extends BaseRepository<T>> implements BaseService<T> {

    @Autowired
    private E repository;

    @Override
    public Iterable<T> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public T findOne(UUID uuid) {
        return repository.findOne(uuid);
    }

    @Override
    public T insert(T entity) {
        entity.setCreatedDate(LocalDateTime.now());
        entity.setUpdatedDate(LocalDateTime.now());
        return repository.save(entity);
    }

    @Override
    public T update(T entity) {
        entity.setUpdatedDate(LocalDateTime.now());
        return repository.save(entity);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public void delete(UUID uuid) {
        repository.delete(uuid);
    }

    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    public void delete(List<T> entities) {
        repository.delete(entities);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    public E getRepository() {
        return repository;
    }
}
