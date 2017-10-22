package com.dart.api.repository;

import com.dart.domain.Identifiable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * Created by dart on 20.10.17.
 */
public interface BaseRepository<T extends Identifiable> extends CrudRepository<T, UUID> {

    Iterable<T> findAll(Sort sort);

    Page<T> findAll(Pageable pageable);


}
