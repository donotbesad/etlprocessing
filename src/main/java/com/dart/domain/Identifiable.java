package com.dart.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * Created by dart on 20.10.17.
 */

@Data
@MappedSuperclass
public class Identifiable {

    interface Fields {
        String UUID = "uuid";
        String STRATEGY_CLASS = "org.hibernate.id.UUIDGenerator";
    }

    @Id
    @GeneratedValue
    @GenericGenerator(
            name = Fields.UUID,
            strategy = Fields.STRATEGY_CLASS
    )
    private UUID uuid;
}
