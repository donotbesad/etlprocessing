package com.dart.utils;

import com.dart.domain.ParseEntry;
import com.dart.model.ParseEntryDTO;

/**
 * Author: Dmitry Artemenko
 * Date: 26.10.17
 * Time: 14:10
 *
 * @author Dmitry Artemenko
 */
public class ParseEntryAdapter {

    public static ParseEntryDTO convert(ParseEntry domain) {
        ParseEntryDTO dto = null;
        if (domain != null) {
            dto = new ParseEntryDTO();
            dto.setId(domain.getUuid().toString());
            dto.setParsedCount(domain.getParsedCount());
            dto.setProductCode(domain.getProductCode());
            dto.setStatus(domain.getStatus().getLabel());
            dto.setCreatedDate(domain.getCreatedDate());
        }
        return dto;
    }
}
