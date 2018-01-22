package com.idatavc.web.service.impl;

import com.idatavc.web.dto.ProjectDto;
import com.idatavc.web.mapper.*;
import com.idatavc.web.model.*;
import com.idatavc.web.service.ResolveFileService;
import com.idatavc.web.utils.AliOssUtils;
import com.idatavc.web.utils.MD5Util;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.selector.XpathSelector;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.stream.Stream;

@Service
public class ResolveFileServiceImpl implements ResolveFileService {
    private static final Logger log = LoggerFactory.getLogger(ResolveFileService.class);

    @Value("${html.folder.path}")
    private String folderPath="/Users/zhhu/html";

    private ExecutorService executorService;

    @Autowired
    private ProjectsMapper projectsMapper;
    @Autowired
    private ProjectFinancingLogMapper projectFinancingLogMapper;
    @Autowired
    private InvestmentInstitutionsMapper investmentInstitutionsMapper;
    @Autowired
    private MetaRegionMapper metaRegionMapper;
    @Autowired
    private ProjectCrawlerMapper projectCrawlerMapper;
    @Autowired
    private InvestmentInstitutionsProjectMapper investmentInstitutionsProjectMapper;

    @Transactional
    @Override
    public boolean handlerHtmlResolve(String fileName) {
        log.info("beginning handler file {}",fileName);
        if (!fileName.endsWith(".DS_Store")){


        try {
            String html = readLineByLineJava8(fileName);

            XpathSelector xpathSelectorAllTr = new XpathSelector(
                    "//table/tbody/tr");
            XpathSelector xpathSelectorAllText = new XpathSelector(
                    "//table/tbody/tr/td/allText()");
            XpathSelector xpathSelector = new XpathSelector(
                    "//table/tbody/tr/td/div[@class='fix']/div[@class='icon hand fl']/@style");
            XpathSelector xpathSelectorHand = new XpathSelector(
                    "//table/tbody/tr/td/div[@class='dib']/div[@class='icon dib hand']/@style");

            XpathSelector xpathSelectorJGLogo = new XpathSelector(
                    "//div[@class='header fix']/div[@class='header-icon fl']/@style");

            XpathSelector xpathSelectorJGName = new XpathSelector(
                    "//div[@class='header fix']/div[@class='header-info fl']/div[@class='first']/span/text()");
            XpathSelector xpathSelectorJGInfo = new XpathSelector(
                    "//div[@class='header fix']/div[@class='header-info fl']/div[@class='mt20 f12']//span/allText()");

            InvestmentInstitutions entity = new InvestmentInstitutions();
            entity.setShortName(xpathSelectorJGName.select(html));
            String jgLogo = xpathSelectorJGLogo.select(html);
            if (null != jgLogo) {
                entity.setLogo(uploadFile(jgLogo.substring(jgLogo.indexOf("(\"") + 2, jgLogo.indexOf("\")"))));
            }
            try {
                List<String> infoList = xpathSelectorJGInfo.selectList(html);
                if (infoList.size()>2) {
                    entity.setInvestmentInstitutionsType(infoList.get(0));
                    entity.setEstablishedTime(infoList.get(1));
                    entity.setCity(infoList.get(2));
                    entity.setHomeUrl(infoList.get(3));
                }else{
                    entity.setHomeUrl(infoList.get(1));
                }
            } catch (Exception e) {
                log.error(e.getMessage(),e.fillInStackTrace());
            }
            List<ProjectDto> projectDtoList = new ArrayList<>();
            List<String> listTr = xpathSelectorAllTr.selectList(html);
            List<String> listTd = null;
            ProjectCrawler projectDto = null;
            String urlPath = null;
            String logoUrl = null;
            if (html.indexOf("投资事件")>0){
                for (String stringTr : listTr) {
                    listTd = xpathSelectorAllText.selectList("<table>" + stringTr + "</table>");
                    projectDto = new ProjectCrawler();
//                    projectDto.setId(Integer.valueOf(listTd.get(0)));
                    projectDto.setFinancingTime(listTd.get(1));
                    projectDto.setStage(listTd.get(2));
                    projectDto.setAmountDesc(listTd.get(3));
                    projectDto.setInvestors(listTd.get(4));
                    projectDto.setShortName(listTd.get(5));
                    projectDto.setSegmentation(listTd.get(7).replace("\\\\r\\\\n", "").trim());
                    projectDto.setKenelWords(listTd.get(8).replace("\\\\r\\\\n", "").trim());
//                    projectDto.setNowStage(listTd.get(6));
                    projectDto.setCity(listTd.get(6));
                    urlPath = xpathSelector.select("<table>" + stringTr + "</table>");
                    log.info("stringTr:{}","<table>" + stringTr + "</table>");
                    log.info("urlPath:{}",urlPath);
                    if (StringUtils.isEmpty(urlPath)){
                        urlPath = xpathSelectorHand.select("<table>" + stringTr + "</table>");
                        projectDto = new ProjectCrawler();
//                    projectDto.setId(Integer.valueOf(listTd.get(0)));
                        projectDto.setFinancingTime(listTd.get(1));
                        projectDto.setStage(listTd.get(2));
                        projectDto.setShortName(listTd.get(3));
                        projectDto.setSegmentation(listTd.get(5));
                        projectDto.setKenelWords(listTd.get(6).replace("\\\\r\\\\n", "").trim());
                        projectDto.setNowStage(listTd.get(7));
                        projectDto.setCity(listTd.get(4));
                        log.info("stringTr:{}","<table>" + stringTr + "</table>");
                        log.info("urlPath:{}",urlPath);
                        if (StringUtils.isNotEmpty(urlPath)) {
                            projectDto.setLogo(urlPath.substring(urlPath.indexOf("(\"") + 2, urlPath.indexOf("\")")));
                            logoUrl = uploadFile(projectDto.getLogo());
                            projectDto.setNewLogo(logoUrl);
                            projectCrawlerMapper.insert(projectDto);
                            Projects projects = new Projects();
                            projects.setShortName(projectDto.getShortName());

                            List<Projects> projectsList = projectsMapper.select(projects);
                            for (Projects p : projectsList) {
                                p.setProjectLogo(logoUrl);
                                projectsMapper.updateByPrimaryKey(p);
                                log.info("update project {}, {}", p.getShortName(), p.getProjectLogo());
                            }
                        }
                        saveProjects(projectDto,entity);
                    } else {
                        projectDto.setLogo(urlPath.substring(urlPath.indexOf("(\"") + 2, urlPath.indexOf("\")")));
                        logoUrl = uploadFile(projectDto.getLogo());
                        projectDto.setNewLogo(logoUrl);
//                    projectDtoList.add(projectDto);
                        projectCrawlerMapper.insert(projectDto);
                        Projects projects = new Projects();
                        projects.setShortName(projectDto.getShortName());

                        List<Projects> projectsList = projectsMapper.select(projects);
                        for (Projects p : projectsList) {
                            p.setProjectLogo(logoUrl);
                            projectsMapper.updateByPrimaryKey(p);
                            log.info("update project {}, {}", p.getShortName(), p.getProjectLogo());
                        }
                        saveProjects(projectDto,entity);
                    }

                }
            }else {
                for (String stringTr : listTr) {
                    listTd = xpathSelectorAllText.selectList("<table>" + stringTr + "</table>");
                    projectDto = new ProjectCrawler();
//                    projectDto.setId(Integer.valueOf(listTd.get(0)));
                    projectDto.setFinancingTime(listTd.get(1));
                    projectDto.setStage(listTd.get(2));
                    projectDto.setShortName(listTd.get(3));
                    projectDto.setSegmentation(listTd.get(4));
                    projectDto.setKenelWords(listTd.get(5).replace("\\\\r\\\\n", "").trim());
                    projectDto.setNowStage(listTd.get(6));
                    urlPath = xpathSelector.select("<table>" + stringTr + "</table>");
                    log.info("stringTr:{}","<table>" + stringTr + "</table>");
                    log.info("urlPath:{}",urlPath);
                    if (StringUtils.isNotEmpty(urlPath)) {
                        logoUrl = uploadFile(projectDto.getLogo());
                        projectDto.setLogo(urlPath.substring(urlPath.indexOf("(\"") + 2, urlPath.indexOf("\")")));
                        projectDto.setNewLogo(logoUrl);
                        projectCrawlerMapper.insert(projectDto);
                        saveProjects(projectDto, entity);
                        Projects projects = new Projects();
                        projects.setShortName(projectDto.getShortName());
                        List<Projects> projectsList = projectsMapper.select(projects);
                        for (Projects p : projectsList) {
                            p.setProjectLogo(logoUrl);
                            projectsMapper.updateByPrimaryKey(p);
                            log.info("update project {}, {}", p.getShortName(), p.getProjectLogo());
                        }
                    }
                }
            }


            log.info("ending handler file {}",fileName);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        }
        return false;

    }

    private void saveProjects(ProjectCrawler projectCrawler, InvestmentInstitutions entity) {
        Integer jgId = 0;
        InvestmentInstitutions queryII = new InvestmentInstitutions();
        queryII.setShortName(entity.getShortName());
        List<InvestmentInstitutions> investmentInstitutions  = investmentInstitutionsMapper.select(queryII);
        if (null != investmentInstitutions && investmentInstitutions.size() > 0){
            for (InvestmentInstitutions ii : investmentInstitutions){
                ii.setHomeUrl(entity.getHomeUrl());
                ii.setCity(entity.getCity());
                ii.setEstablishedTime(entity.getEstablishedTime());
                ii.setLogo(entity.getLogo());
                ii.setInvestmentInstitutionsType(entity.getInvestmentInstitutionsType());
                investmentInstitutionsMapper.updateByPrimaryKey(ii);
            }
            jgId = investmentInstitutions.get(0).getId();
        }else{
            investmentInstitutionsMapper.insert(entity);
            jgId = entity.getId();
        }

        Projects query = new Projects();
        query.setShortName(projectCrawler.getShortName());
        List<Projects> projectsList = projectsMapper.select(query);
        Integer projectId = 0;
        if (null != projectsList && projectsList.size()> 0){
            projectId = projectsList.get(0).getId();
            //更新项目
            for (Projects p : projectsList){
                p.setProjectLogo(projectCrawler.getNewLogo());
                projectsMapper.updateByPrimaryKey(p);
            }
        }else {
            //添加项目
            Projects projects = new Projects();
            projects.setProjectLogo(projectCrawler.getNewLogo());
            projects.setShortName(projectCrawler.getShortName());
            projects.setTerritory(projectCrawler.getCity());
            projects.setSegmentation(projectCrawler.getSegmentation());
            MetaRegion queryMR = new MetaRegion();
            queryMR.setQuid(0);
            queryMR.setShid(0);
            queryMR.setMing(projectCrawler.getCity() + "区");
            MetaRegion metaRegion = metaRegionMapper.selectOne(queryMR);
            if (null == metaRegion) {
                queryMR.setMing(projectCrawler.getCity() + "市");
                metaRegion = metaRegionMapper.selectOne(queryMR);
            }
            if (null == metaRegion) {
                queryMR.setMing(projectCrawler.getCity());
                queryMR.setShengid(0);
                metaRegion = metaRegionMapper.selectOne(queryMR);
            }
            if (null != metaRegion) {
                if (metaRegion.getGid() == 45055) {
                    projects.setContinent("亚洲");
                    projects.setCountry("中国");
                    metaRegion = metaRegionMapper.selectByPrimaryKey(metaRegion.getShengid());
                    if (null == metaRegion) {
                        projects.setProvince(projectCrawler.getCity());
                    } else {
                        projects.setProvince(metaRegion.getMing());
                    }
                    projects.setCity(projectCrawler.getCity());
                } else {
                    projects.setContinent(metaRegionMapper.selectByPrimaryKey(metaRegion.getGid()).getMing());
//                    projects.setCountry(metaRegionMapper.selectByPrimaryKey(metaRegion.getZid()).getMing());
//                    if (metaRegion.getShengid() == 0) {
//                        projects.setCity(metaRegion.getMing());
//                    } else {
//                        projects.setCity(metaRegionMapper.selectByPrimaryKey(metaRegion.getShengid()).getMing());
//                    }
                }
            }

            projects.setKernelDesc(projectCrawler.getKenelWords());
            projectsMapper.insert(projects);
        }

            ProjectFinancingLog projectFinancingLog = null;
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
            InvestmentInstitutionsProject investmentInstitutionsProject = new InvestmentInstitutionsProject();

            if (null != projectCrawler.getInvestors() && projectCrawler.getInvestors().indexOf("、")> 0) {
                for (String seg : projectCrawler.getInvestors().split("、")) {
                    projectFinancingLog = new ProjectFinancingLog();
                    queryII.setShortName(entity.getShortName());
                    investmentInstitutions = investmentInstitutionsMapper.select(queryII);
                    if (null == investmentInstitutions) {

                        investmentInstitutionsMapper.insert(queryII);
                        jgId = queryII.getId();
                    } else {
                        jgId = investmentInstitutions.get(0).getId();
                    }
                    projectFinancingLog.setProjectId(projectId);
                    projectFinancingLog.setStage(projectCrawler.getStage());
                    String date;
                    String[] dd = projectCrawler.getFinancingTime().replace(".", "/").split("/");
                    if (null != dd && dd.length > 1) {
                        if (dd.length == 2) {

                            date = dd[0] + "-" + (dd[1].length() < 2 ? "0" + dd[1] : dd[1]) + "-01";
                            if ("2012-11-31".equals(date)){
                                date = "2012-11-30";
                            }
                            projectFinancingLog.setFinancingTime(formatter.parseDateTime(date).toDate());
                        } else {

                            date = dd[0] + "-" + (dd[1].length() < 2 ? "0" + dd[1] : dd[1]) + "-" + (dd[2].length() < 2 ? "0" + dd[2] : dd[2]);
                            if ("2012-11-31".equals(date)){
                                date = "2012-11-30";
                            }
                            projectFinancingLog.setFinancingTime(formatter.parseDateTime(date).toDate());
                        }
                    }

//                    projectFinancingLog.setFinancingTime(formatter.parseDateTime(date).toDate());

                    projectFinancingLogMapper.insert(projectFinancingLog);

                    investmentInstitutionsProject.setProjectId(projectFinancingLog.getId());
                    investmentInstitutionsProject.setInvestmentInstitutionsId(jgId);
                    investmentInstitutionsProjectMapper.insert(investmentInstitutionsProject);
                }
            }else{
                projectFinancingLog = new ProjectFinancingLog();
                projectFinancingLog.setProjectId(projectId);
                projectFinancingLog.setStage(projectCrawler.getStage());
                String date;
                String[] dd = projectCrawler.getFinancingTime().replace(".", "/").split("/");
                try {
                    if (null != dd && dd.length > 1) {
                        if (dd.length == 2) {

                            date = dd[0] + "-" + (dd[1].length() < 2 ? "0" + dd[1] : dd[1]) + "-01";
                            if ("2012-11-31".equals(date)){
                                date = "2012-11-30";
                            }
                            projectFinancingLog.setFinancingTime(formatter.parseDateTime(date).toDate());
                        } else {

                            date = dd[0] + "-" + (dd[1].length() < 2 ? "0" + dd[1] : dd[1]) + "-" + (dd[2].length() < 2 ? "0" + dd[2] : dd[2]);
                            if ("2012-11-31".equals(date)){
                                date = "2012-11-30";
                            }
                            projectFinancingLog.setFinancingTime(formatter.parseDateTime(date).toDate());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


                projectFinancingLogMapper.insert(projectFinancingLog);

                investmentInstitutionsProject.setProjectId(projectFinancingLog.getId());
                investmentInstitutionsProject.setInvestmentInstitutionsId(jgId);
                investmentInstitutionsProjectMapper.insert(investmentInstitutionsProject);
            }
//            projects.






    }

    private String uploadFile(String oldPath){

       int randomnum = (int)(Math.random()*10000);
       int randomnum1 = (int)(Math.random()*9000);

       String randomnumString = String.valueOf(randomnum);
       String randomnumString1 = String.valueOf(randomnum1);

       String md5String =  MD5Util.md5Encode(randomnumString,"utf-8");
       ByteArrayOutputStream os = new ByteArrayOutputStream();
       String path = "upload/auto/"+md5String+".jpg";
       try {
           Thumbnails.of(new URL(oldPath).openStream()).size(750,750).toOutputStream(os);
               AliOssUtils.putObject(path, new ByteArrayInputStream(os.toByteArray()));
       }catch(Exception e){
           log.error(e.getMessage(),e.fillInStackTrace());
       }
       return "https://img.idatavc.com/"+path;
   }

    private String readLineByLineJava8(String filePath)
    {
        if (filePath.endsWith(".DS_Store")){
            return "";
        }

        if (filePath.indexOf(".txt")> 0) {
            StringBuffer sb = null;
            try {
                File f = new File(filePath);
                FileInputStream fis = new FileInputStream(f);
                InputStreamReader isr = new InputStreamReader(fis, initBookEncode(fis));
                BufferedReader reader = new BufferedReader(isr);
                sb = new StringBuffer();
                String s;
                while ((s = reader.readLine()) != null) {
                    sb.append(s + "\\r\\n");
                }
                reader.close();
                isr.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }else{
            StringBuilder contentBuilder = new StringBuilder();
            try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
                stream.forEach(s -> contentBuilder.append(s).append("\n"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return contentBuilder.toString();
        }
    }

    //读取文件编码
    public static String initBookEncode(FileInputStream fileInputStream) {
        String encode = "gb2312";
        try{
            byte[] head = new byte[3];
            fileInputStream.read(head);
            if(head[0]==-17 && head[1]==-69 && head[2] ==-65)
                encode = "UTF-8";
            else if (head[0] == -1 && head[1] == -2 )
                encode = "UTF-16";
            else if (head[0] == -2 && head[1] == -1 )
                encode = "Unicode";
//           else
//                System.out.println(head[0]+","+head[1]+","+head[2]);
        }catch (IOException e) {
            log.error(e.getMessage(),e.fillInStackTrace());

        }
        return encode;
    }

}
