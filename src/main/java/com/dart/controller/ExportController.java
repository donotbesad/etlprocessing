package com.dart.controller;

import com.dart.api.service.ServiceFacade;
import com.dart.domain.product.ProductReview;
import com.dart.model.ProductReviewDTO;
import com.dart.utils.ProductAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Author: Dmitry Artemenko
 * Date: 03.11.17
 * Time: 18:03
 *
 * @author Dmitry Artemenko
 */

@Controller
@RequestMapping(ExportController.Names.SELF)
@Api(description = ApiDocumentation.EXPORT_API_DESCRIPTION, value = ApiDocumentation.EXPORT_API_VALUE)
public class ExportController {

    interface Names {
        String SELF = "/export";
        String REVIEWS = "/reviews";
        String REVIEW_BY_ID = REVIEWS + BaseController.SLASH + BaseController.GET_BY_ID;
        String PRODUCT_CODE_PARAM = "productCode";
        String PARSE_ENTRY_ID_PARAM = "parseEntryId";

        // file names
        String REVIEWS_CSV = "reviews.csv";
        String REVIEW_CSV = "review.csv";

        //response
        String CSV_RESPONSE_CONTENT_TYPE = "text/csv; charset=utf-8";
        String CSV_RESPONSE_CONTENT_DISPOSITION = "Content-disposition";
        String CSV_RESPONSE_FILE_PREFIX = "attachment; filename=";
    }

    @Autowired
    private ServiceFacade facade;

    @GetMapping(value = Names.REVIEWS, params = {Names.PRODUCT_CODE_PARAM})
    @ApiOperation(value = ApiDocumentation.EXPORT_API_REVIEWS_BY_PRODUCT_CODE_OPERATION)
    public void exportReviewsByProductCode(@RequestParam int productCode, HttpServletResponse response) {
        try {
            prepareCsvResponse(response, Names.REVIEWS_CSV);
            Sort sort = new Sort(Sort.Direction.DESC, ProductReview.Fields.PUBLISHED_DATE);
            List<ProductReviewDTO> reviews = facade.getReviewService().findReviewsByProductCode(productCode, sort)
                    .stream()
                    .map(ProductAdapter::convert)
                    .collect(Collectors.toList());
            facade.getExportService().writeReviewsToCsv(response.getWriter(), reviews);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = Names.REVIEWS, params = {Names.PARSE_ENTRY_ID_PARAM})
    @ApiOperation(value = ApiDocumentation.EXPORT_API_REVIEWS_BY_PARSE_ENTRY_OPERATION)
    public void exportReviewsParseEntry(@RequestParam String parseEntryId, HttpServletResponse response) {
        try {
            prepareCsvResponse(response, Names.REVIEWS_CSV);
            Sort sort = new Sort(Sort.Direction.DESC, ProductReview.Fields.PUBLISHED_DATE);
            List<ProductReviewDTO> reviews = facade.getReviewService().findReviewsByParseEntry(UUID.fromString(parseEntryId), sort)
                    .stream()
                    .map(ProductAdapter::convert)
                    .collect(Collectors.toList());
            facade.getExportService().writeReviewsToCsv(response.getWriter(), reviews);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(Names.REVIEW_BY_ID)
    @ApiOperation(value = ApiDocumentation.EXPORT_API_REVIEW_BY_ID_OPERATION)
    public void exportReview(@PathVariable String uuid, HttpServletResponse response) {
        try {
            prepareCsvResponse(response, Names.REVIEW_CSV);
            ProductReviewDTO review = ProductAdapter.convert(facade.getReviewService().findOne(UUID.fromString(uuid)));
            facade.getExportService().writeReviewToCsv(response.getWriter(), review);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void prepareCsvResponse(HttpServletResponse response, String fileName) {
        response.setContentType(ExportController.Names.CSV_RESPONSE_CONTENT_TYPE);
        response.setHeader(Names.CSV_RESPONSE_CONTENT_DISPOSITION, Names.CSV_RESPONSE_FILE_PREFIX + fileName);
    }


}
