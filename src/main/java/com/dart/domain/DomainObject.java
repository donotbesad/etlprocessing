package com.dart.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Author: Dmitry Artemenko
 * Date: 23.10.17
 * Time: 14:08
 *
 * @author Dmitry Artemenko
 */

@Data
@MappedSuperclass
public class DomainObject extends Identifiable {

    interface Columns {
        String CREATED_DATE = "createdDate";
        String UPDATED_DATE = "updatedDate";
    }

    @Column(name = Columns.CREATED_DATE, nullable = false)
    private LocalDateTime createdDate;

    @Column(name = Columns.UPDATED_DATE, nullable = false)
    private LocalDateTime updatedDate;

}
