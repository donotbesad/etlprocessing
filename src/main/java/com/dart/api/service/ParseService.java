package com.dart.api.service;

import com.dart.domain.ParseEntry;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Base service for Product elements parsing
 *
 * Author: Dmitry Artemenko
 * Date: 29.10.17
 * Time: 10:04
 *
 * @author Dmitry Artemenko
 */
public interface ParseService {

    /**
     * Extract data from resource
     *
     * @param toParse is entry which will be parsed
     * @param existing entry
     * @param document is representation of html page
     * @throws Exception (ignored)
     */
    void extract(ParseEntry toParse, ParseEntry existing, Document document) throws Exception;

    /**
     * Transform data from resource  elements
     *
     * @param toParse is entry which will be parsed
     * @param existing entry
     * @param elements from resource
     */
    void transform(ParseEntry toParse, ParseEntry existing, Elements elements);
}
