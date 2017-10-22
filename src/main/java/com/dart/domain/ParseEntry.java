package com.dart.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by dart on 20.10.17.
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
@Table(name = "parse_entry")
public class ParseEntry extends Identifiable {

    interface Columns {
        String PRODUCT_CODE = "product_code";
        String PARSE_DATE = "parse_date";
    }

    interface Fields {
        String PRODUCT_CODE = "productCode";
        String PARSE_DATE = "parseDate";
        String STATUS = "status";
    }

    @Column(name = Columns.PRODUCT_CODE, nullable = false)
    private int productCode;

    @Column(name = Columns.PARSE_DATE, nullable = false)
    private LocalDateTime parseDate;

    @Enumerated(EnumType.STRING)
    private ParseStatus status;

}
