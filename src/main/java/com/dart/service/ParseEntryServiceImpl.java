package com.dart.service;

import com.dart.api.repository.ParseEntryRepository;
import com.dart.api.service.ParseEntryService;
import com.dart.api.service.ServiceFacade;
import com.dart.domain.ParseEntry;
import com.dart.domain.ParseStatus;
import com.dart.utils.ProductParseUtil;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Dmitry Artemenko
 * Date: 20.10.17
 * Time: 14:10
 *
 * @author Dmitry Artemenko
 */

@Service
public class ParseEntryServiceImpl extends BaseServiceImpl<ParseEntry, ParseEntryRepository> implements ParseEntryService {

    @Autowired
    private ParseEntryRepository parseEntryRepository;

    @Autowired
    private ServiceFacade facade;


    @Override
    public ParseEntry parse(int productCode) {
        ParseEntry existing = parseEntryRepository.findFirstByProductCodeOrderByCreatedDateDesc(productCode);
        ParseEntry toParse = new ParseEntry();
        toParse.setProductCode(productCode);
        toParse.setStatus(ParseStatus.PARSING);
        insert(toParse);

        Document mainPage = ProductParseUtil.retrieveProductPage(productCode);
        if (mainPage == null) {
            toParse.setStatus(ParseStatus.NOT_FOUND);
            return insert(toParse);
        }

        List<Document> reviewPages = ProductParseUtil.retrieveProductReviewPages(productCode);
        reviewPages.add(mainPage);

        try {
            for (Document reviewPage : reviewPages) {
                facade.getReviewService().extract(toParse, existing, reviewPage);
            }
            facade.getProductService().extract(toParse, existing, mainPage);
            toParse.setStatus(ParseStatus.PARSED);
        } catch (Exception e) {
            toParse.setStatus(ParseStatus.FAILED);
        }

        return insert(toParse);
    }
}
