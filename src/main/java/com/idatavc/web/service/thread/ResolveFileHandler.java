package com.idatavc.web.service.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.selector.XpathSelector;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class ResolveFileHandler implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(ResolveFileHandler.class);



    private String filePath;
    private String fileName;

    public ResolveFileHandler(String filePath, String fileName){
        this.fileName = fileName;
        this.filePath = filePath;
    }

    @Override
    public void run() {

        try {
            String html = readLineByLineJava8(this.filePath+"/"+this.fileName);

            XpathSelector xpathSelectorAllTr = new XpathSelector(
                    "//div");

            List<String> listTr = xpathSelectorAllTr.selectList(html);

            for (String stringTr : listTr){
                log.info(stringTr);
            }

//            XpathSelector xpathSelectorAllText = new XpathSelector(
//                    "//table/tbody/tr/td/allText()");
//
//            XpathSelector xpathSelector = new XpathSelector(
//                    "//table/tbody/tr/td/div[@class='fix']/div[@class='icon hand fl']/@style");
//

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private String readLineByLineJava8(String filePath)
    {
        if (filePath.endsWith(".txt")) {
            StringBuilder contentBuilder = new StringBuilder();
            try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
                stream.forEach(s -> contentBuilder.append(s).append("\n"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return contentBuilder.toString();
        }
        return "";
    }
}
