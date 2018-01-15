package com.idatavc.web.controller;


import com.idatavc.web.service.ResolveFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrawlerController {

    @Autowired
    private ResolveFileService resolveFileService;

    @GetMapping("resolve/file")
    public String handlerFileResolve(){

        if (!resolveFileService.handlerHtmlResolve()){
            return "error";
        }

        return "ok";
    }

}
