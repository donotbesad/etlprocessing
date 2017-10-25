package com.dart.service;

import com.dart.api.repository.ParseEntryRepository;
import com.dart.api.service.ParseEntryService;
import com.dart.api.service.ProductReviewService;
import com.dart.domain.ParseEntry;
import com.dart.domain.ParseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dart on 20.10.17.
 */

@Service
public class ParseEntryServiceImpl extends BaseServiceImpl<ParseEntry, ParseEntryRepository> implements ParseEntryService {

    @Autowired
    private ParseEntryRepository parseEntryRepository;

    @Autowired
    private ProductReviewService productReviewService;


    @Override
    public ParseEntry parse(int productCode) {
        ParseEntry existing = parseEntryRepository.findFirstByProductCodeOrderByCreatedDateDesc(productCode);

        ParseEntry toParse = new ParseEntry();
        toParse.setProductCode(productCode);
        toParse.setStatus(ParseStatus.PARSING);
        insert(toParse);

        try {
            productReviewService.extract(toParse, existing);
            toParse.setStatus(ParseStatus.PARSED);
        } catch (Exception e) {
            toParse.setStatus(ParseStatus.FAILED);
        }

        return insert(toParse);
    }
}
