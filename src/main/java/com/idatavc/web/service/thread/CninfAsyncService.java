package com.idatavc.web.service.thread;

import com.idatavc.web.dto.Announcement;
import com.idatavc.web.utils.AliOssUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

@Component
public class CninfAsyncService {


    private static final Logger logger = LoggerFactory.getLogger(CninfAsyncService.class);

    @Autowired
    private RestTemplate restTemplate;



    @Async
    public CompletableFuture<String> downloadFile(String name, String url) throws InterruptedException {


        try {

           String fileURL = "http://www.cninfo.com.cn/cninfo-new/disclosure/szse/bulletin_detail/true/1204379898?announceTime=2018-02-01";
            String saveDir = "/Users/zhhu/Downloads";
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(
                    url);

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            int responseCode = response.getStatusLine().getStatusCode();


            InputStream is = entity.getContent();

            String filePath =saveDir + File.pathSeparator+ name +".pdf";
            FileOutputStream fos = new FileOutputStream(new File(filePath));

            int inByte;
            while ((inByte = is.read()) != -1) {
                fos.write(inByte);
            }

            is.close();
            fos.close();

            client.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            logger.error("{}","s","s");
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return CompletableFuture.completedFuture("ok");
    }

    public CompletableFuture<String> downloadFile(Announcement announcement) {
        try {

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dd = DateTime.now().toDate();
            dd.setTime(announcement.getAnnouncementTime());


            String fileURL = String.format("http://www.cninfo.com.cn/cninfo-new/disclosure/szse/bulletin_detail/true/s%?announceTime=s%",announcement.getAnnouncementId(),dateFormat.format(dd));

//            String saveDir = "/Users/zhhu/Downloads";

//            ResponseEntity<Resource> responseEntity = restTemplate.exchange(fileURL, HttpMethod.GET,);

            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(fileURL);

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            int responseCode = response.getStatusLine().getStatusCode();

//            restTemplate
            InputStream is = entity.getContent();

            StringBuilder stringBuilder = new StringBuilder("http://cninf.idatavc.com/upload/");
            stringBuilder.append(announcement.getAnnouncementTitle()).append(".pdf");
            AliOssUtils.putObject(stringBuilder.toString(), is);
//            String filePath =saveDir + File.pathSeparator+ name +".pdf";
//            FileOutputStream fos = new FileOutputStream(new File(filePath));
//
//            int inByte;
//            while ((inByte = is.read()) != -1) {
//                fos.write(inByte);
//            }

            is.close();
//            fos.close();

            client.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            logger.error("{}","s","s");
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture("ok");
    }
}
