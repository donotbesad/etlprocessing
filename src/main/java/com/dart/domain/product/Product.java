package com.dart.domain.product;

import com.dart.domain.DomainObject;
import com.dart.domain.ParseEntry;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * Author: Dmitry Artemenko
 * Date: 29.10.17
 * Time: 10:09
 *
 * @author Dmitry Artemenko
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
@Table(name = "product")
public class Product extends DomainObject {

    public interface Columns {
        String PARSE_ENTRY_UUID = "parse_entry_uuid";
        String TYPE = "type";
        String BRAND = "brand";
        String MODEL = "model";
    }

    @OneToOne
    @JoinColumn(name = Columns.PARSE_ENTRY_UUID)
    private ParseEntry parseEntry;

    @Column(name = Columns.TYPE)
    private String type;

    @Column(name = Columns.BRAND)
    private String brand;

    @Column(name = Columns.MODEL)
    private String model;


    public String getTitle() {
        String title = brand + " " + model;
        return title.toUpperCase();
    }
}
