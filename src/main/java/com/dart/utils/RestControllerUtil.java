package com.dart.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;

import java.util.Optional;

/**
 * Author: Dmitry Artemenko
 * Date: 05.11.17
 * Time: 21:59
 *
 * @author Dmitry Artemenko
 */
public class RestControllerUtil {

    public static Optional<Sort> checkSort(String direction, String property) {
        return StringUtils.isNotEmpty(direction) && StringUtils.isNotEmpty(property)
                ? Optional.of(new Sort(Sort.Direction.fromString(direction), property)) : Optional.empty();
    }
}
