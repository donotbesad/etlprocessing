package com.dart.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * Superclass for all domain objects
 *
 * Author: Dmitry Artemenko
 * Date: 20.10.17
 * Time: 14:08
 *
 * @author Dmitry Artemenko
 */

@Data
@MappedSuperclass
public class Identifiable {

    public interface Columns {
        String UUID = "uuid";
        String STRATEGY_CLASS = "org.hibernate.id.UUIDGenerator";
    }

    @Id
    @GeneratedValue
    @GenericGenerator(
            name = Columns.UUID,
            strategy = Columns.STRATEGY_CLASS
    )
    private UUID uuid;
}
