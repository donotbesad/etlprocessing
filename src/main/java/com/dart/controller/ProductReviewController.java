package com.dart.controller;

import com.dart.api.service.ServiceFacade;
import com.dart.model.ApiResponse;
import com.dart.model.ProductReviewDTO;
import com.dart.utils.ProductAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by dart on 25.10.17.
 */

@RestController
@RequestMapping(ProductReviewController.Endpoints.SELF)
@Api(description = ApiDocumentation.PRODUCT_REVIEW_API_DESCRIPTION, value = ApiDocumentation.PRODUCT_REVIEW_API_VALUE)
public class ProductReviewController implements BaseController {

    interface Endpoints {
        String SELF = "/reviews";
        String PRODUCT_REVIEWS_BY_PRODUCT_CODE = "/product/{productCode}";
    }

    @Autowired
    private ServiceFacade facade;


    @GetMapping
    @ApiOperation(value = ApiDocumentation.PRODUCT_REVIEW_API_LIST_OPERATION, response = ProductReviewDTO.class)
    public ApiResponse getProductReviews(@PathParam(value = PAGE) int page,
                                         @PathParam(value = SIZE) int size) {
        List<ProductReviewDTO> result = facade.getProductReviewService().findAll(new PageRequest(page, size)).getContent().stream()
                .map(ProductAdapter::convert)
                .collect(Collectors.toList());
        return new ApiResponse(result);
    }

    @GetMapping(GET_BY_ID)
    @ApiOperation(value = ApiDocumentation.PRODUCT_REVIEW_API_ID_OPERATION, response = ProductReviewDTO.class)
    public ApiResponse getProductReview(@PathVariable String uuid) {
        ProductReviewDTO result = ProductAdapter.convert(facade.getProductReviewService().findOne(UUID.fromString(uuid)));
        return new ApiResponse(result);
    }

    @GetMapping(Endpoints.PRODUCT_REVIEWS_BY_PRODUCT_CODE)
    @ApiOperation(value = ApiDocumentation.PRODUCT_REVIEW_API_LIST_BY_PRODUCT_CODE_OPERATION, response = ProductReviewDTO.class)
    public ApiResponse getProductReviewsByProductCode(@PathParam(value = PAGE) int page,
                                                      @PathParam(value = SIZE) int size,
                                                      @PathVariable String productCode) {
        List<ProductReviewDTO> result = facade.getProductReviewService().findReviewsByProductCode(Integer.parseInt(productCode), new PageRequest(page, size)).getContent()
                .stream()
                .map(ProductAdapter::convert)
                .collect(Collectors.toList());
        return new ApiResponse(result);
    }
}
