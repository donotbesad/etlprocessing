package com.dart.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * Created by dart on 20.10.17.
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
@Table(name = "parse_entry")
public class ParseEntry extends DomainObject {

    interface Columns {
        String PRODUCT_CODE = "product_code";
        String PARSED_COUNT = "parsed_count";
        String STATUS = "status";
    }

    @Column(name = Columns.PRODUCT_CODE, nullable = false)
    private int productCode;

    @Column(name = Columns.PARSED_COUNT)
    private int parsedCount;

    @Enumerated(EnumType.STRING)
    @Column(name = Columns.STATUS, nullable = false)
    private ParseStatus status;

}
