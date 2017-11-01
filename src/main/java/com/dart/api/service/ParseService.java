package com.dart.api.service;

import com.dart.domain.ParseEntry;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Author: Dmitry Artemenko
 * Date: 29.10.17
 * Time: 10:04
 *
 * @author Dmitry Artemenko
 */
public interface ParseService {

    void extract(ParseEntry toParse, ParseEntry existing, Document document) throws Exception;

    void transform(ParseEntry toParse, ParseEntry existing, Elements elements);
}
