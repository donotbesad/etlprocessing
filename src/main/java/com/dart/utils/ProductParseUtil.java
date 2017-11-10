package com.dart.utils;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

    public static Map<Integer, Document> retrieveProductReviewPages(int productCode, int pageNumber) {
        Map<Integer, Document> page = new HashMap<>();

        StringBuilder url = new StringBuilder(CENEO_URL)
                .append(productCode)
                .append("/opinie-")
                .append(pageNumber);

        Document document;
        try {
            document = Jsoup.connect(url.toString()).get();
            if (document.location().contains("/opinie-")) {
                page.put(pageNumber, document);
            }
        } catch (IOException ignored) {
            log.warn("Can not parse page: " + url, ignored);
            System.err.println();
        }

        return Collections.unmodifiableMap(page);
    }
}
