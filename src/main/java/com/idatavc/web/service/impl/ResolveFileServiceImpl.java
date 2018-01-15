package com.idatavc.web.service.impl;

import com.idatavc.web.service.ResolveFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ResolveFileServiceImpl implements ResolveFileService {
    private static final Logger log = LoggerFactory.getLogger(ResolveFileService.class);

    @Value("${html.folder.path}")
    private String folderPath;


    @Override
    public boolean handlerHtmlResolve() {




        return false;
    }
}
