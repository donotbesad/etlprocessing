package com.dart.controller;

import com.dart.api.service.ServiceFacade;
import com.dart.domain.DomainObject;
import com.dart.model.ApiResponse;
import com.dart.model.ParseEntryDTO;
import com.dart.utils.ParseEntryAdapter;
import com.dart.utils.RestControllerUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Author: Dmitry Artemenko
 * Date: 20.10.17
 * Time: 14:08
 *
 * @author Dmitry Artemenko
 */


@RestController
@RequestMapping(ParseController.Endpoints.SELF)
@Api(description = ApiDocumentation.PARSE_API_DESCRIPTION, value = ApiDocumentation.PARSE_API_VALUE)
public class ParseController implements BaseController {

    interface Endpoints {
        String SELF = "/parse";
        String PARSE_PRODUCT = "/code/{productCode}";
        String PRODUCT_CODE = "productCode";
    }

    @Autowired
    private ServiceFacade facade;

    @GetMapping
    @ApiOperation(value = ApiDocumentation.PARSE_API_LIST_OPERATION, response = ParseEntryDTO.class)
    public ApiResponse getParsedEntries(@RequestParam(required = false) String direction,
                                        @RequestParam(required = false) String property,
                                        @PathParam(value = PAGE) int page,
                                        @PathParam(value = SIZE) int size) {
        Optional<Sort> optionalSort = RestControllerUtil.checkSort(direction, property);
        Sort sort = optionalSort.orElse(new Sort(Sort.Direction.DESC, DomainObject.Fields.CREATED_DATE));
        PageRequest pageRequest = new PageRequest(page, size, sort);
        List<ParseEntryDTO> result = facade.getParseEntryService().findAll(pageRequest).getContent()
                .stream()
                .map(ParseEntryAdapter::convert)
                .collect(Collectors.toList());
        return new ApiResponse(result);
    }

    @GetMapping(GET_BY_ID)
    @ApiOperation(value = ApiDocumentation.PARSE_API_ID_OPERATION, response = ParseEntryDTO.class)
    public ApiResponse getParsedEntryById(@PathVariable String uuid) {
        ParseEntryDTO result = ParseEntryAdapter.convert(facade.getParseEntryService().findOne(UUID.fromString(uuid)));
        return new ApiResponse(result);
    }

    @GetMapping(params = {Endpoints.PRODUCT_CODE})
    public ApiResponse getParsedEntryByProductCode(@RequestParam int productCode) {
        ParseEntryDTO result = ParseEntryAdapter.convert(facade.getParseEntryService().getByProductCode(productCode));
        return new ApiResponse(result);
    }

    @GetMapping(Endpoints.PARSE_PRODUCT)
    @ApiOperation(value = ApiDocumentation.PARSE_API_PARSE_OPERATION, response = ParseEntryDTO.class)
    public ApiResponse parseProduct(@PathVariable int productCode) {
        ParseEntryDTO result = ParseEntryAdapter.convert(facade.getParseEntryService()
                .parse(productCode));
        return new ApiResponse(result);
    }


}

