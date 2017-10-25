package com.dart.controller;

import com.dart.api.service.ServiceFacade;
import com.dart.model.ApiResponse;
import com.dart.model.ParseEntryDTO;
import com.dart.utils.ParseEntryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by dart on 20.10.17.
 */


@RestController
@RequestMapping(ParseController.Endpoints.SELF)
public class ParseController {

    interface Endpoints {
        String SELF = "/parse";
        String PARSE_ENTRY_BY_ID = "/{uuid}";
        String PARSE_PAGE = "/{from}/{to}";
        String PARSE_PRODUCT = "/product/{productCode}";
    }

    @Autowired
    private ServiceFacade facade;

    @GetMapping(Endpoints.PARSE_PAGE)
    public ApiResponse getParsedEntries(@PathVariable int from, @PathVariable int to) {
        List<ParseEntryDTO> result = facade.getParseEntryService().findAll(new PageRequest(from, to)).getContent().stream()
                .map(ParseEntryAdapter::convert)
                .collect(Collectors.toList());
        return new ApiResponse(result);
    }

    @GetMapping(Endpoints.PARSE_ENTRY_BY_ID)
    public ApiResponse getParsedEntryById(@PathVariable String uuid) {
        ParseEntryDTO result = ParseEntryAdapter.convert(facade.getParseEntryService().findOne(UUID.fromString(uuid)));
        return new ApiResponse(result);
    }

    @GetMapping(Endpoints.PARSE_PRODUCT)
    public ApiResponse parseProduct(@PathVariable int productCode) {
        ParseEntryDTO result = ParseEntryAdapter.convert(facade.getParseEntryService().parse(productCode));
        return new ApiResponse(result);
    }

}

