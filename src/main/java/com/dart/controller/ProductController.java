package com.dart.controller;

import com.dart.api.service.ServiceFacade;
import com.dart.domain.DomainObject;
import com.dart.model.ApiResponse;
import com.dart.model.ProductDTO;
import com.dart.utils.ProductAdapter;
import com.dart.utils.RestControllerUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Author: Dmitry Artemenko
 * Date: 29.10.17
 * Time: 15:13
 *
 * @author Dmitry Artemenko
 */

@RestController
@RequestMapping(ProductController.Endpoints.SELF)
@Api(description = ApiDocumentation.PRODUCT_API_DESCRIPTION, value = ApiDocumentation.PRODUCT_API_VALUE)
public class ProductController implements BaseController {

    interface Endpoints {
        String SELF = SLASH + "products";
        String PRODUCT_CODE = "productCode";
    }

    @Autowired
    private ServiceFacade facade;

    @GetMapping(params = {PAGE, SIZE})
    @ApiOperation(value = ApiDocumentation.PRODUCT_API_LIST_OPERATION, response = ProductDTO.class)
    public ApiResponse getProducts(@RequestParam(required = false) String direction,
                                   @RequestParam(required = false) String property,
                                   @RequestParam int page,
                                   @RequestParam int size) {
        Optional<Sort> optionalSort = RestControllerUtil.checkSort(direction, property);
        Sort sort = optionalSort.orElse(new Sort(Sort.Direction.DESC, DomainObject.Fields.CREATED_DATE));
        PageRequest pageRequest = new PageRequest(page, size, sort);
        List<ProductDTO> result = facade.getProductService().findAll(pageRequest).getContent()
                .stream()
                .map(ProductAdapter::convert)
                .collect(Collectors.toList());
        return new ApiResponse(result);
    }

    @GetMapping(params = {ProductController.Endpoints.PRODUCT_CODE})
    @ApiOperation(value = ApiDocumentation.PRODUCT_API_BY_PRODUCT_CODE_OPERATION, response = ProductDTO.class)
    public ApiResponse getProductByProductCode(@RequestParam int productCode) {
        ProductDTO result = ProductAdapter.convert(facade.getProductService().findByProductCode(productCode));
        return new ApiResponse(result);
    }

    @GetMapping(GET_BY_ID)
    @ApiOperation(value = ApiDocumentation.PRODUCT_API_BY_PRODUCT_ID, response = ProductDTO.class)
    public ApiResponse getProduct(@PathVariable String uuid) {
        ProductDTO result = ProductAdapter.convert(facade.getProductService().findOne(UUID.fromString(uuid)));
        return new ApiResponse(result);
    }

}
