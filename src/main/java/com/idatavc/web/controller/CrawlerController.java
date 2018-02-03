package com.idatavc.web.controller;


import com.idatavc.web.handler.DataHandlerPipeline;
import com.idatavc.web.processors.LocalHtmlFilePageProcessor;
import com.idatavc.web.service.ResolveFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.selector.Html;

import java.io.File;

@RestController
public class CrawlerController {

    @Autowired
    private DataHandlerPipeline dataHandlerPipeline;

    @Value("${html.folder.path}")
    private String filePath;

    @Autowired
    private ResolveFileService resolveFileService;

    @GetMapping("resolve/file")
    public String handlerFileResolve(){

//        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        //设置动态转发代理，使用定制的ProxyProvider
//        httpClientDownloader.setProxyProvider(CrowProxyProvider.from(new Proxy("forward.xdaili.cn", 80)));
        try {
            File folderName = new File(this.filePath);

            if (folderName.isDirectory()){
                String encoding = System.getProperty("file.encoding");
                for (String fileName : folderName.list()){
                    resolveFileService.handlerHtmlResolve(this.filePath+"/"+fileName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @GetMapping("resolve/cninf")
    public String handlerCninfResolve(){

        resolveFileService.handlerCninf();
        return "ok";
    }

}
