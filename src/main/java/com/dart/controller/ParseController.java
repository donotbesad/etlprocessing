package com.dart.controller;

import com.dart.api.service.ServiceFacade;
import com.dart.domain.ParseEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dart on 20.10.17.
 */

@RestController
public class ParseController {

    interface Endpoints {
        String PARSE = "/parse/{productCode}";
    }

    @Autowired
    private ServiceFacade facade;

    @GetMapping(Endpoints.PARSE)
    public ParseEntry parseProduct(@PathVariable String productCode) {
        return facade.getParseService().parse(productCode);
    }
}
