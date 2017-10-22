package com.dart.service;

import com.dart.api.service.ParseService;
import com.dart.api.service.ServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dart on 20.10.17.
 */

@Service
public class ServiceFacadeImpl implements ServiceFacade {

    @Autowired
    private ParseService parseService;

    @Override
    public ParseService getParseService() {
        return parseService;
    }
}