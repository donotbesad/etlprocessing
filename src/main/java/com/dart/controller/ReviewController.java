package com.dart.controller;

import com.dart.api.service.ServiceFacade;
import com.dart.domain.product.ProductReview;
import com.dart.model.ApiResponse;
import com.dart.model.ProductReviewDTO;
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
 * Date: 25.10.17
 * Time: 14:07
 *
 * @author Dmitry Artemenko
 */

@RestController
@RequestMapping(ReviewController.Endpoints.SELF)
@Api(description = ApiDocumentation.PRODUCT_REVIEW_API_DESCRIPTION, value = ApiDocumentation.PRODUCT_REVIEW_API_VALUE)
public class ReviewController implements BaseController {

    interface Endpoints {
        String SELF = "/reviews";
        String PRODUCT_CODE = "productCode";
        String PARSE_ENTRY_ID = "parseEntryId";
    }

    @Autowired
    private ServiceFacade facade;

    @GetMapping(GET_BY_ID)
    @ApiOperation(value = ApiDocumentation.PRODUCT_REVIEW_API_ID_OPERATION, response = ProductReviewDTO.class)
    public ApiResponse getProductReview(@PathVariable String uuid) {
        ProductReviewDTO result = ProductAdapter.convert(facade.getReviewService().findOne(UUID.fromString(uuid)));
        return new ApiResponse(result);
    }

    @GetMapping(params = {PAGE, SIZE, Endpoints.PRODUCT_CODE})
    @ApiOperation(value = ApiDocumentation.PRODUCT_REVIEW_API_LIST_BY_PRODUCT_CODE_OPERATION, response = ProductReviewDTO.class)
    public ApiResponse getProductReviewsByProductCode(@RequestParam(required = false) String direction,
                                                      @RequestParam(required = false) String property,
                                                      @RequestParam int page,
                                                      @RequestParam int size,
                                                      @RequestParam int productCode) {
        Optional<Sort> optionalSort = RestControllerUtil.checkSort(direction, property);
        Sort sort = optionalSort.orElse(new Sort(Sort.Direction.DESC, ProductReview.Fields.PUBLISHED_DATE));
        PageRequest pageRequest = new PageRequest(page, size, sort);
        List<ProductReviewDTO> result = facade.getReviewService().findReviewsByProductCode(productCode, pageRequest).getContent()
                .stream()
                .map(ProductAdapter::convert)
                .collect(Collectors.toList());
        return new ApiResponse(result);
    }

    @GetMapping(params = {PAGE, SIZE, Endpoints.PARSE_ENTRY_ID})
    @ApiOperation(value = ApiDocumentation.PRODUCT_REVIEW_API_LIST_BY_PARSE_ENTRY_ID, response = ProductReviewDTO.class)
    public ApiResponse getProductReviewsByParseEntry(@RequestParam(required = false) String direction,
                                                     @RequestParam(required = false) String property,
                                                     @RequestParam int page,
                                                     @RequestParam int size,
                                                     @RequestParam String parseEntryId) {
        Optional<Sort> optionalSort = RestControllerUtil.checkSort(direction, property);
        Sort sort = optionalSort.orElse(new Sort(Sort.Direction.DESC, ProductReview.Fields.PUBLISHED_DATE));
        PageRequest pageRequest = new PageRequest(page, size, sort);
        List<ProductReviewDTO> result = facade.getReviewService().findReviewsByParseEntry(UUID.fromString(parseEntryId), pageRequest).getContent()
                .stream()
                .map(ProductAdapter::convert)
                .collect(Collectors.toList());
        return new ApiResponse(result);
    }
}
