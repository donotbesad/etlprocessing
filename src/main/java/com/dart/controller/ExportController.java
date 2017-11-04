package com.dart.controller;

import com.dart.api.service.ServiceFacade;
import com.dart.model.ProductReviewDTO;
import com.dart.utils.ProductAdapter;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void exportReviewsByProductCode(@RequestParam int productCode, HttpServletResponse response) {
        try {
            prepareCsvResponse(response, Names.REVIEWS_CSV);
            List<ProductReviewDTO> reviews = facade.getReviewService().findReviewsByProductCode(productCode)
                    .stream()
                    .map(ProductAdapter::convert)
                    .collect(Collectors.toList());
            facade.getExportService().writeReviewsToCsv(response.getWriter(), reviews);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = Names.REVIEWS, params = {Names.PARSE_ENTRY_ID_PARAM})
    public void exportReviewsParseEntry(@RequestParam String parseEntryId, HttpServletResponse response) {
        try {
            prepareCsvResponse(response, Names.REVIEWS_CSV);
            List<ProductReviewDTO> reviews = facade.getReviewService().findReviewsByParseEntry(UUID.fromString(parseEntryId))
                    .stream()
                    .map(ProductAdapter::convert)
                    .collect(Collectors.toList());
            facade.getExportService().writeReviewsToCsv(response.getWriter(), reviews);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(Names.REVIEW_BY_ID)
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
        response.setContentType("text/csv; charset=utf-8");
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
    }


}
