package com.dart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Dmitry Artemenko
 * 1/2/2018
 */

@Controller
public class MainController {

    interface Fields {
        String EMPTY_PATH = "/";
        String PARSE_PATH = "/parse";
        String PRODUCTS_PATH = "/products";
        String INDEX_VIEW_NAME = "index";
        String PRODUCTS_VIEW_NAME = "product";
    }

    @GetMapping(Fields.EMPTY_PATH)
    public String emptyPathView() {
        return Fields.INDEX_VIEW_NAME;
    }

    @GetMapping(Fields.PRODUCTS_PATH)
    public String productsView() { return Fields.PRODUCTS_VIEW_NAME; }
}
