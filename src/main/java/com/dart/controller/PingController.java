package com.dart.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dart on 20.10.17.
 */

@RestController
public class PingController {

    @GetMapping("/ping")
    public String ping() {
        return "Server is running..";
    }

}
