package com.dart.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

/**
 * Superclass for all domain objects
 *
 * Author: Dmitry Artemenko
 * Date: 23.10.17
 * Time: 14:08
 *
 * @author Dmitry Artemenko
 */

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
@Accessors(chain = true)
public class DomainObject extends Identifiable {

    public interface Columns {
        String CREATED_DATE = "created_date";
        String UPDATED_DATE = "updated_date";
    }

    public interface Fields {
        String CREATED_DATE = "createdDate";
        String UPDATED_DATE = "updatedDate";
    }

    @Column(name = Columns.CREATED_DATE, nullable = false)
    private LocalDateTime createdDate;

    @Column(name = Columns.UPDATED_DATE, nullable = false)
    private LocalDateTime updatedDate;

    @PrePersist
    public void prePersist() {
        if (createdDate == null) {
            createdDate = LocalDateTime.now();
        }
        updatedDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedDate = LocalDateTime.now();
    }


}
