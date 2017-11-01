package com.dart.api.service;

import com.dart.domain.ParseEntry;

/**
 * Author: Dmitry Artemenko
 * Date: 20.10.17
 * Time: 14:07
 *
 * @author Dmitry Artemenko
 */
public interface ParseEntryService extends BaseService<ParseEntry> {

    ParseEntry parse(int productCode);

}
