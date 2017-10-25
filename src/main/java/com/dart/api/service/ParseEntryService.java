package com.dart.api.service;

import com.dart.domain.ParseEntry;

/**
 * Created by dart on 20.10.17.
 */
public interface ParseEntryService extends BaseService<ParseEntry> {

    ParseEntry parse(int productCode);

}
