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
        String INDEX_VIEW_NAME = "index";
    }

    @GetMapping(Fields.EMPTY_PATH)
    public String redirectToApiDocumentation() {
        return Fields.INDEX_VIEW_NAME;
    }

}
