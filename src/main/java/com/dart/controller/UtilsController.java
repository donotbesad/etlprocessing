package com.dart.controller;

import com.dart.api.service.ServiceFacade;
import com.dart.model.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Dmitry Artemenko
 * Date: 07.01.18
 * Time: 0:11
 *
 * @author Dmitry Artemenko
 */

@RestController
public class UtilsController {

    private static final String TRUNCATE_ALL_DATA_PATH = "/truncate";

    @Autowired
    private ServiceFacade facade;

    @DeleteMapping(TRUNCATE_ALL_DATA_PATH)
    public ApiResponse truncate() {
        facade.getProductService().deleteAll();
        facade.getReviewService().deleteAll();
        facade.getParseEntryService().deleteAll();
        return new ApiResponse(null).setStatus(ApiResponse.Status.SUCCESS);
    }

}
