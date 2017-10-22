package com.dart.service;

import com.dart.api.service.ParseService;
import com.dart.domain.ParseEntry;
import com.dart.domain.ParseStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created by dart on 20.10.17.
 */

@Service
public class ParseServiceImpl extends BaseServiceImpl<ParseEntry> implements ParseService {


    @Override
    public ParseEntry parse(String productCode) {
        ParseEntry parseEntry = new ParseEntry();
        parseEntry.setProductCode(Integer.parseInt(productCode));
        parseEntry.setParseDate(LocalDateTime.now());
        parseEntry.setStatus(ParseStatus.PARSED);
        return insert(parseEntry);
    }
}
