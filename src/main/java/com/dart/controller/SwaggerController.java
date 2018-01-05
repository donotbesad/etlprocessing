package com.dart.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: Dmitry Artemenko
 * Date: 04.11.17
 * Time: 3:09
 *
 * @author Dmitry Artemenko
 */

@Controller
@Api(description = ApiDocumentation.MAIN_API_DESCRIPTION, value = ApiDocumentation.MAIN_API_VALUE)
public class SwaggerController {

    interface Names {
        String PATH = "/swagger";
        String SWAGGER_PATH = "/swagger-ui.html";
    }

    @GetMapping(Names.PATH)
    @ApiOperation(value = ApiDocumentation.MAIN_API_EMPTY_PATH_OPERATION)
    public void redirectToApiDocumentation(HttpServletResponse response) throws IOException {
        response.sendRedirect(Names.SWAGGER_PATH);
    }
}
