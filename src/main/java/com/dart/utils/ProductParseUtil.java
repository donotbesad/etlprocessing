package com.dart.utils;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Dmitry Artemenko
 * Date: 29.10.17
 * Time: 11:40
 *
 * @author Dmitry Artemenko
 */
public class ProductParseUtil {

    private static final Logger log = Logger.getLogger(ProductParseUtil.class.getName());

    public static final String CENEO_URL = "https://www.ceneo.pl/";

    public static Document retrieveProductPage(int productCode) {
        StringBuilder url = new StringBuilder(CENEO_URL)
                .append(productCode);
        Document document = null;
        try {
            document = Jsoup.connect(url.toString()).get();
        } catch (IOException ignored) {
            log.warn("Can not parse page: " + url, ignored);
        }
        return document;
    }

    public static List<Document> retrieveProductReviewPages(int productCode) {
        List<Document> result = new ArrayList<>();
        int pageNumber = 2;
        boolean pagesAvailable = true;

        while (pagesAvailable) {
            StringBuilder url = new StringBuilder(CENEO_URL)
                    .append(productCode)
                    .append("/opinie-")
                    .append(pageNumber);

            Document document;
            try {
                document = Jsoup.connect(url.toString()).get();
                if (document.location().contains("/opinie-")) {
                    result.add(document);
                    pageNumber++;
                } else {
                    pagesAvailable = false;
                }
            } catch (IOException ignored) {
                log.warn("Can not parse page: " + url, ignored);
            }
        }

        return result;
    }
}
