package com.dart.api.service;

import com.dart.domain.ParseEntry;

/**
 * Created by dart on 20.10.17.
 */
public interface ParseService extends BaseService<ParseEntry> {

    ParseEntry parse(String productCode);
}
