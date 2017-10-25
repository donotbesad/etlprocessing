package com.dart.utils;

import com.dart.domain.ParseEntry;
import com.dart.model.ParseEntryDTO;

/**
 * Created by dart on 26.10.17.
 */
public class ParseEntryAdapter {

    public static ParseEntryDTO convert(ParseEntry domain) {
        ParseEntryDTO dto = new ParseEntryDTO();
        if (domain != null) {
            dto.setId(domain.getUuid().toString());
            dto.setParsedCount(domain.getParsedCount());
            dto.setProductCode(domain.getProductCode());
            dto.setStatus(domain.getStatus().getLabel());
            dto.setCreatedDate(domain.getCreatedDate());
        }
        return dto;
    }
}
