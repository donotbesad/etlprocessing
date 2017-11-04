package com.dart.controller;

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
public class MainController {

    interface Names {
        String EMPTY_PATH = "/";
        String SWAGGER_PATH = "/swagger-ui.html";
    }

    @GetMapping(Names.EMPTY_PATH)
    public void redirectToApiDocumentation(HttpServletResponse response) throws IOException {
        response.sendRedirect(Names.SWAGGER_PATH);
    }
}
