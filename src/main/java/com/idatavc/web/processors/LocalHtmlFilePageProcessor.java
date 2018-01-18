package com.idatavc.web.processors;

import com.idatavc.web.dto.ProjectDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.XpathSelector;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class LocalHtmlFilePageProcessor implements PageProcessor {


    private static final Logger log = LoggerFactory.getLogger(LocalHtmlFilePageProcessor.class);

    private String filePath;



    public LocalHtmlFilePageProcessor(String filePath) {

        this.filePath = filePath;
    }

    @Override
    public void process(Page page) {




    }



    @Override
    public Site getSite() {
        return null;
    }
}
