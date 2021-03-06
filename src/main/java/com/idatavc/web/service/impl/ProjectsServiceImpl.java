package com.idatavc.web.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.util.StringUtil;
import com.idatavc.web.MyCell;
import com.idatavc.web.mapper.*;
import com.idatavc.web.model.*;
import com.idatavc.web.service.ProjectsService;
import com.idatavc.web.utils.CheckChinese;
import org.apache.ibatis.javassist.bytecode.ExceptionsAttribute;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Service("projectsService")
public class ProjectsServiceImpl implements ProjectsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectsService.class);

    @Autowired
    private ProjectsMapper projectsMapper;

    @Autowired
    private ProjectFinancingLogMapper projectFinancingLogMapper;
    @Autowired
    private InvestmentInstitutionsMapper investmentInstitutionsMapper;
    @Autowired
    private FoundersMapper foundersMapper;
    @Autowired
    private FoundersEducationMapper foundersEducationMapper;
    @Autowired
    private FoundersWorkMapper foundersWorkMapper;
    @Autowired
    private InvestorsMapper investorsMapper;
    @Autowired
    private MetaRegionMapper metaRegionMapper;
    @Autowired
    private ProjectSegmentationMapper projectSegmentationMapper;
    @Autowired
    private InvestmentInstitutionsProjectMapper investmentInstitutionsProjectMapper;

    @Transactional
    @Override
    public void saveProject(Integer taskID, Map<Integer, List<MyCell>> data) {
        LOGGER.info("Task id is {},Begin time {}",taskID, DateTime.now().toDate());
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTimeFormatter formatterShort = DateTimeFormat.forPattern("yyyy-MM");



        data.forEach((k, v) -> {
            LOGGER.info("{}",k);

            final Projects projects = new Projects();
            final ProjectFinancingLog projectFinancingLog = new ProjectFinancingLog();
//            final Investors investors = new Investors();
            final InvestmentInstitutions investmentInstitutions = new InvestmentInstitutions();
            final Founders founders = new Founders();
            final FoundersEducation foundersEducation = new FoundersEducation();
            final FoundersWork foundersWork = new FoundersWork();

            String index0 = v.get(0).getContent();
            if (!StringUtils.isEmpty(index0)) {
                if (index0.indexOf(".")!=-1) {
                    projects.setSerialNumber(Integer.valueOf(index0.substring(0, index0.indexOf("."))));
                }else{
                    projects.setSerialNumber(Integer.valueOf(index0));

                }
            }
            investmentInstitutions.setShortName(v.get(1).getContent());

//            investmentInstitutions.setCommet(v.get(2).getContent());
//            if (v.size()>32&&!"".equals(v.get(32).getContent())){
//                projects.setTotalAmount(new BigDecimal(v.get(32).getContent()));
//            }

            projects.setShortName(v.get(3).getContent());
            projects.setFullName(v.get(4).getContent());
            projects.setKernelDesc(v.get(5).getContent());
            projects.setCommet(v.get(6).getContent());
            projects.setUrl(v.get(7).getContent());

            String establishedTime = v.get(8).getContent();
            if (!"".equals(establishedTime)&&!"--".equals(establishedTime)&&!"-".equals(establishedTime)) {
                if (establishedTime.length() < 10){
                    projects.setEstablishedTime(formatterShort.parseDateTime(establishedTime).toDate());
                }else {
                    projects.setEstablishedTime(formatter.parseDateTime(establishedTime).toDate());
                }
            }
            projects.setSegmentation(v.get(9).getContent()); //细分领域


            projects.setItemLabel(v.get(10).getContent()); //项目标签
//            v.get(11)地域
            projects.setTerritory(v.get(11).getContent());
            projects.setAddress(v.get(12).getContent());
//            projects.setCreateTime(DateTime.now().toDate());
            String city = v.get(11).getContent();
            if (city.indexOf("/")>-1){
                city = city.split("/")[1];
            }

            try {
                if (!"".equals(city)) {
                    MetaRegion queryMR = new MetaRegion();
                    queryMR.setQuid(0);
                    queryMR.setShid(0);
                    queryMR.setMing(city + "区");
                    MetaRegion metaRegion = metaRegionMapper.selectOne(queryMR);
                    if (null == metaRegion) {
                        queryMR.setMing(city + "市");
                        metaRegion = metaRegionMapper.selectOne(queryMR);
                    }
                    if (null == metaRegion) {
                        queryMR.setMing(city);
                        queryMR.setShengid(0);
                        metaRegion = metaRegionMapper.selectOne(queryMR);
                    }
                    if (null != metaRegion) {
                        if (metaRegion.getGid() == 45055) {
                            projects.setContinent("亚洲");
                            projects.setCountry("中国");
                            metaRegion = metaRegionMapper.selectByPrimaryKey(metaRegion.getShengid());
                            if (null == metaRegion) {
                                projects.setProvince(city);
                            } else {
                                projects.setProvince(metaRegion.getMing());
                            }
                            projects.setCity(city);
                        } else {
                            projects.setContinent(metaRegionMapper.selectByPrimaryKey(metaRegion.getGid()).getMing());
                            projects.setCountry(metaRegionMapper.selectByPrimaryKey(metaRegion.getZid()).getMing());
                            if (metaRegion.getShengid() == 0) {
                                projects.setCity(metaRegion.getMing());
                            } else {
                                projects.setCity(metaRegionMapper.selectByPrimaryKey(metaRegion.getShengid()).getMing());
                            }
                        }


                    } else {
                        queryMR = new MetaRegion();
                        queryMR.setQuid(0);
                        queryMR.setMing(city + "区");
                        metaRegion = metaRegionMapper.selectOne(queryMR);
                        if (null == metaRegion) {
                            queryMR.setMing(city + "市");
                            metaRegion = metaRegionMapper.selectOne(queryMR);
                        }
                        if (null == metaRegion) {
                            queryMR.setMing(city);
                            try {
                                metaRegion = metaRegionMapper.selectOne(queryMR);
                            } catch (Exception e) {
                                metaRegion = metaRegionMapper.select(queryMR).get(0);
                            }
                        }
                        if (null != metaRegion) {
                            if (metaRegion.getGid() == 45055) {
                                projects.setContinent("亚洲");
                                projects.setCountry("中国");
                                metaRegion = metaRegionMapper.selectByPrimaryKey(metaRegion.getShengid());
                                if (null == metaRegion) {
                                    projects.setProvince(city);
                                } else {
                                    projects.setProvince(metaRegion.getMing());
                                }
                                projects.setCity(city);
                            } else {
                                projects.setContinent(metaRegionMapper.selectByPrimaryKey(metaRegion.getGid()).getMing());
                                projects.setCountry(metaRegionMapper.selectByPrimaryKey(metaRegion.getZid()).getMing());
                                if (metaRegion.getShengid() == 0) {
                                    projects.setCity(metaRegion.getMing());
                                } else {
                                    projects.setCity(metaRegionMapper.selectByPrimaryKey(metaRegion.getShengid()).getMing());
                                }


                            }


                        }
                    }
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
//            projects.setCity(v.get(11).getContent());
            if (null != investmentInstitutions.getShortName() && !"".equals(investmentInstitutions.getShortName())) {
                InvestmentInstitutions queryOne = null;
                try {
                    queryOne = investmentInstitutionsMapper.selectOne(investmentInstitutions);
                } catch (Exception e) {
                    queryOne = investmentInstitutionsMapper.select(investmentInstitutions).get(0);
                }
                if (null == queryOne) {

                    investmentInstitutionsMapper.insert(investmentInstitutions);
                    projects.setInvestmentInstitutionsId(investmentInstitutions.getId());
                } else {
                    projects.setInvestmentInstitutionsId(queryOne.getId());
                }
            }


            Projects queryProjects = new Projects();
            queryProjects.setShortName(v.get(3).getContent().trim());
            LOGGER.info("Full name is "+ v.get(4).getContent().trim());
            queryProjects.setFullName(v.get(4).getContent().trim());

            List<Projects> queryP = projectsMapper.select(queryProjects);
            Integer projectId = null;
            if (null == queryP||queryP.size()==0) {
                projectsMapper.insert(projects);
                projectFinancingLog.setProjectId(projects.getId());
                projectId = projects.getId();
            } else {
                projectFinancingLog.setProjectId(queryP.get(0).getId());
                projectId = queryP.get(0).getId();
            }


//            DateTimeFormatter formatDate = DateTimeFormat.forPattern("yyyy-MM-dd");
            String financingTimeCell = v.get(13).getContent();
            if (financingTimeCell.equals("--")||financingTimeCell.equals("-")){
                financingTimeCell = "";
            }
            projectFinancingLog.setFinancingTime("".equals(financingTimeCell) ? null : formatter.parseDateTime(financingTimeCell.length() < 9 ? financingTimeCell + "-01" : financingTimeCell).toDate());
            projectFinancingLog.setStage(v.get(14).getContent());
            String amount = v.get(15).getContent();
            if ("".equals(amount)||null == amount) {
                amount = "0";
            }
            String currency = v.get(16).getContent();
            BigDecimal rate = null;
            if (currency.equals("人民币")) {
                projectFinancingLog.setCurrency(0);
                rate = new BigDecimal(1);
                projectFinancingLog.setRate(rate);
            }else if(currency.equals("美元")) {
                projectFinancingLog.setCurrency(1);
                rate = new BigDecimal(6.59);
                projectFinancingLog.setRate(rate);
            }else if (currency.equals("卢比")) {
                projectFinancingLog.setCurrency(2);
                rate = new BigDecimal(0.1013);
                projectFinancingLog.setRate(rate);
            }else if(currency.equals("欧元")) {
                projectFinancingLog.setCurrency(3);
                rate = new BigDecimal(8.6857);
                projectFinancingLog.setRate(rate);
            }else if(currency.equals("英镑")) {
                projectFinancingLog.setCurrency(4);
                rate = new BigDecimal(7.8097);
                projectFinancingLog.setRate(rate);
            }else {
                rate = new BigDecimal(1);
            }
            amount = amount.replaceAll("\n","");
            if (!StringUtils.isEmpty(amount.trim())) {
                projectFinancingLog.setAmount(new BigDecimal(amount).multiply(rate));
            }

            String stockRight = v.get(17).getContent();
            if (StringUtils.isEmpty(stockRight.trim())) {
                stockRight = "0";
            }

            if (stockRight.indexOf("可转债") > -1){
                stockRight = "0";
            }
            projectFinancingLog.setStockRight(new BigDecimal(stockRight));
            String pr = v.get(18).getContent();
            if (pr.indexOf("未透露") > 0) {
                projectFinancingLog.setPrAmount(new BigDecimal("100"));
            }
            if (pr.indexOf("十") > 0) {
                projectFinancingLog.setPrAmount(new BigDecimal("20"));
            }
            if (pr.indexOf("百") > 0) {
                projectFinancingLog.setPrAmount(new BigDecimal("200"));
            }
            if (pr.indexOf("千") > 0) {
                projectFinancingLog.setPrAmount(new BigDecimal("1000"));
            }
            if (pr.indexOf("亿") > 0) {
                projectFinancingLog.setPrAmount(new BigDecimal("5000"));
            }

            String totalAmount = v.get(19).getContent();
            if (totalAmount.indexOf("可转债") > -1){
                projectFinancingLog.setTotalAmount(new BigDecimal("0"));
            }else
            if (totalAmount.indexOf("未透露") > -1) {
                projectFinancingLog.setTotalAmount(new BigDecimal("100"));
            } else if (totalAmount.indexOf("十") >= 0) {
                projectFinancingLog.setTotalAmount(new BigDecimal("20"));
            } else if (totalAmount.indexOf("百") >= 0) {
                projectFinancingLog.setTotalAmount(new BigDecimal("200"));
            } else if (totalAmount.indexOf("千") >= 0) {
                projectFinancingLog.setTotalAmount(new BigDecimal("1000"));
            } else if (totalAmount.indexOf("亿") >= 0) {
                projectFinancingLog.setTotalAmount(new BigDecimal("5000"));
            } else if (null != totalAmount && !"".equals(totalAmount)) {
                try {
                    projectFinancingLog.setTotalAmount(new BigDecimal(totalAmount).multiply(rate));
                } catch (Exception e) {
                    projectFinancingLog.setTotalAmount(new BigDecimal(0).multiply(rate));
                }
            }
            projectFinancingLog.setShareDivest(v.get(20).getContent());
            String valuation = v.get(21).getContent();
            if ("".equals(valuation) || null == valuation) {
                valuation = "0";
            }
            if (valuation.indexOf("万") > 0) {
                valuation = valuation.substring(0, valuation.indexOf("万"));
            }
            Integer serialNum = null;
                if (!StringUtils.isEmpty(index0)) {
                    if (index0.indexOf(".")!=-1) {
                        serialNum =Integer.valueOf(index0.substring(0, index0.indexOf(".")));


                    }else{
                        serialNum =Integer.valueOf(index0);

                    }
                }else{
                    return;
                }


                if (v.get(32).getContent().trim().equals("指数数据收集")){
                    projectFinancingLog.setDataSoruceTypeId(7);
                }else if (v.get(32).getContent().trim().equals("IT桔子")){
                    projectFinancingLog.setDataSoruceTypeId(4);
                }else if (v.get(32).getContent().trim().equals("企名片")){
                    projectFinancingLog.setDataSoruceTypeId(3);
                }else if (v.get(32).getContent().trim().equals("万德")){
                    projectFinancingLog.setDataSoruceTypeId(6);
                }else if (v.get(32).getContent().trim().equals("kr")){
                    projectFinancingLog.setDataSoruceTypeId(12);
                }
                projectFinancingLog.setSerialNumber(serialNum);

                if (org.apache.commons.lang3.StringUtils.isEmpty(valuation.trim())){
                    valuation = "0";
                }
                if (valuation.indexOf("可转债")> -1){
                    valuation = "0";
                }
            projectFinancingLog.setValuation(new BigDecimal(valuation).multiply(rate));
            projectFinancingLog.setInvestmentInstitutionsList(v.get(22).getContent());
            projectFinancingLog.setProportionList(v.get(23).getContent());
            projectFinancingLog.setCreateTime(DateTime.now().toDate());
            projectFinancingLog.setStatus(2);
            projectFinancingLog.setTotalAmountStatus(0);
            projectFinancingLog.setAmountStatus(0);


            ProjectFinancingLog queryPFL = new ProjectFinancingLog();
            queryPFL.setSerialNumber(serialNum);
            ProjectFinancingLog projectFinancingLogOld = projectFinancingLogMapper.selectOne(queryPFL);
            Integer projectFinancingLogId = null;
            if (null == projectFinancingLogOld) {

                projectFinancingLogMapper.insert(projectFinancingLog);
                projectFinancingLogId = projectFinancingLog.getId();
            }else{
                projectFinancingLogId = projectFinancingLogOld.getId();
            }



                LOGGER.info("Founders's name {}",v.get(24).getContent().trim());
                if (!StringUtils.isEmpty(v.get(24).getContent().trim())) {
                    founders.setName(v.get(24).getContent());
                    founders.setPosition(v.get(25).getContent());
                    founders.setIntroduction(v.get(26).getContent());
                    founders.setEducationalBackgroundDesc(v.get(28).getContent());
                    founders.setWorkingBackgroundDesc(v.get(30).getContent());
                    founders.setEntrepreneurialExperience(v.get(31).getContent());
                    founders.setCreateTime(DateTime.now().toDate());
                    founders.setYn(1);
                    founders.setProjectId(projectId);
                    Example queryExample = new Example(Founders.class);
                    queryExample.createCriteria().andLike("name", "%" + v.get(24).getContent().trim() + "%");
                    List<Founders> queryF = foundersMapper.selectByExample(queryExample);

                    Integer founderId = null;
                    if (null == queryF || queryF.size() == 0) {
                        foundersMapper.insert(founders);
                        foundersEducation.setFounderId(founders.getId());
                        foundersWork.setFounderId(founders.getId());
//                founderId = founders.getId();
                    } else {
                        founderId = queryF.get(0).getId();
                        foundersEducation.setFounderId(founderId);
                        foundersWork.setFounderId(founderId);
                    }

                    String[] foundersEducations = v.get(27).getContent().split("、");
//            foundersEducation.setFounderId(founderId);
                    for (String fe : foundersEducations) {
                        foundersEducation.setEducationExperience(fe);
                        try {
                            foundersEducationMapper.insert(foundersEducation);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    String[] foundersWorks = v.get(29).getContent().split("、");
//            foundersWork.setFounderId(founderId);
                    for (String fw : foundersWorks) {
                        foundersWork.setWorkExperience(fw);
                        try {
                            foundersWorkMapper.insert(foundersWork);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            String[] seStr = v.get(9).getContent().split("、");
            ProjectSegmentation projectSegmentation = new ProjectSegmentation();
            projectSegmentation.setProjectId(projectId);
            for (String s : seStr) {
                projectSegmentation.setSegmentationName(s);
                projectSegmentationMapper.insert(projectSegmentation);
            }

            String[] jgs = v.get(23).getContent().split("、");

            InvestmentInstitutions query = new InvestmentInstitutions();

            List<InvestmentInstitutions> ii = null;
            InvestmentInstitutions investmentInstitutions2=null;

            if (StringUtil.isNotEmpty(v.get(1).getContent())) {
                saveInvestmentInstitutionsProject(projectFinancingLogId, query, v.get(1).getContent());
            }else {
                for(String s : jgs) {
                    if (StringUtil.isNotEmpty(s)) {
                        saveInvestmentInstitutionsProject(projectFinancingLog.getId(), query, s);
                    }
                }
            }
        });

        LOGGER.info("Task id is {},End time {}",taskID, DateTime.now().toDate());
    }

    @Override
    public void resolveProjects(Map<Integer, List<MyCell>> data) {

//        List<String> edus = foundersEducationMapper.

        List<String> edus = foundersEducationMapper.selectEdus();

        data.forEach((k,v) -> {
            v.get(11).setContent(checkCity(v.get(4).getContent()));
            v.get(27).setContent(checkEdus(edus, v.get(30).getContent()));
        });

        try {
            //创建excel工作簿
            Workbook wb = new HSSFWorkbook();
            //创建第一个sheet（页），命名为 new sheet
            Sheet sheet = wb.createSheet("new sheet");
            AtomicReference<Row> row = new AtomicReference<>();
            Cell cell = null;
            AtomicInteger index = new AtomicInteger();
            AtomicInteger j = new AtomicInteger();
            data.forEach((k,v) -> {
                row.set(sheet.createRow( index.get()));

                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                row.get().createCell(j.get()).setCellValue(v.get(j.getAndIncrement()).getContent());
                index.getAndIncrement();
                j.set(0);
                LOGGER.info("{},{}",v.get(0).getContent(),v.get(1).getContent());
            });

            LOGGER.info("ending.........");
            //创建一个文件 命名为workbook.xls
            FileOutputStream fileOut = new FileOutputStream("workbook3.xls");
            // 把上面创建的工作簿输出到文件中
            wb.write(fileOut);
            //关闭输出流
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



@Transactional
    @Override
    public void resolveCity() {

        List<Projects> projectsList = null;
        Example query = new Example(Projects.class);
        query.and().andIsNull("city");
        projectsList = projectsMapper.selectByExample(query);



        for (Projects projects : projectsList){
            LOGGER.info(projects.getShortName());
            String city =projects.getTerritory();
            if (city.indexOf("/")>-1){
                city = city.split("/")[1];
            }

            if (!"".equals(city)) {
                MetaRegion queryMR = new MetaRegion();
                queryMR.setQuid(0);
                queryMR.setShid(0);
                queryMR.setMing(city + "区");
                MetaRegion metaRegion = metaRegionMapper.selectOne(queryMR);
                if (null == metaRegion) {
                    queryMR.setMing(city + "市");
                    metaRegion = metaRegionMapper.selectOne(queryMR);
                }
                if (null == metaRegion) {
                    queryMR.setMing(city);
                    queryMR.setShengid(0);
                    metaRegion = metaRegionMapper.selectOne(queryMR);
                }
                if (null != metaRegion) {
                    if (metaRegion.getGid() == 45055) {
                        projects.setContinent("亚洲");
                        projects.setCountry("中国");
                        metaRegion = metaRegionMapper.selectByPrimaryKey(metaRegion.getShengid());
                        if (null == metaRegion) {
                            projects.setProvince(city);
                        } else {
                            projects.setProvince(metaRegion.getMing());
                        }
                        projects.setCity(city);
                    } else {
                        try {
                            projects.setContinent(metaRegionMapper.selectByPrimaryKey(metaRegion.getGid()).getMing());
                            projects.setCountry(metaRegionMapper.selectByPrimaryKey(metaRegion.getZid()).getMing());
                            if (metaRegion.getShengid() == 0) {
                                projects.setCity(metaRegion.getMing());
                            } else {
                                projects.setCity(metaRegionMapper.selectByPrimaryKey(metaRegion.getShengid()).getMing());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }


                } else {
                    queryMR = new MetaRegion();
                    queryMR.setQuid(0);
                    queryMR.setMing(city + "区");
                    metaRegion = metaRegionMapper.selectOne(queryMR);
                    if (null == metaRegion) {
                        queryMR.setMing(city + "市");
                        metaRegion = metaRegionMapper.selectOne(queryMR);
                    }
                    if (null == metaRegion) {
                        queryMR.setMing(city);
                        try {
                            metaRegion = metaRegionMapper.selectOne(queryMR);
                        } catch (Exception e) {
                            metaRegion = metaRegionMapper.select(queryMR).get(0);
                        }
                    }
                    if (null != metaRegion) {
                        if (metaRegion.getGid() == 45055) {
                            projects.setContinent("亚洲");
                            projects.setCountry("中国");
                            metaRegion = metaRegionMapper.selectByPrimaryKey(metaRegion.getShengid());
                            if (null == metaRegion) {
                                projects.setProvince(city);
                            } else {
                                projects.setProvince(metaRegion.getMing());
                            }
                            projects.setCity(city);
                        } else {
                            projects.setContinent(metaRegionMapper.selectByPrimaryKey(metaRegion.getGid()).getMing());
                            projects.setCountry(metaRegionMapper.selectByPrimaryKey(metaRegion.getZid()).getMing());
                            if (metaRegion.getShengid() == 0) {
                                projects.setCity(metaRegion.getMing());
                            } else {
                                projects.setCity(metaRegionMapper.selectByPrimaryKey(metaRegion.getShengid()).getMing());
                            }


                        }


                    }
                }
            }
            projectsMapper.updateByPrimaryKey(projects);


        }


    }

    @Override
    public void handlerProjectShortName(Map<Integer, List<MyCell>> data) {


        data.forEach((k,v) -> {
            String updateName = v.get(1).getContent().trim();
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(updateName)) {
                Projects query = new Projects();
                query.setShortName(v.get(0).getContent().trim());
                List<Projects> projects = projectsMapper.select(query);
                for (Projects p : projects){
                    p.setShortName(updateName);
                    projectsMapper.updateByPrimaryKey(p);
                }
            }
        });

        LOGGER.info("handler data ending..............");
    }

    private String checkEdus(List<String> edus, String content) {
        List<String> eds = new ArrayList<>();
          for (String edu : edus){
              if (content.indexOf(edu)>-1){
                  eds.add(edu);
              }
          }
          if (eds.size()>0){
              return Arrays.toString(eds.toArray());
          }

        return null;
    }

    private String checkCity(String content) {
        if (content.indexOf("（")>-1){
            return content.substring(content.indexOf("（")+1,content.indexOf("）"));
        }else {
            if (content.length()>1) {
                if (CheckChinese.isChinese(content.substring(0, 1))) {
                    return content.substring(0, 2);
                } else {
                    return "";
                }
            }
        }
        return null;
    }

    private void saveInvestmentInstitutionsProject(Integer projectId, InvestmentInstitutions query, String s) {
        List<InvestmentInstitutions> ii;
        InvestmentInstitutions investmentInstitutions2;
        InvestmentInstitutionsProject iip = new InvestmentInstitutionsProject();
        query.setShortName(s);
        ii = investmentInstitutionsMapper.select(query);
        if (null == ii || ii.size() < 1){
            investmentInstitutions2 = new InvestmentInstitutions();
            investmentInstitutions2.setShortName(s);
            try {
                investmentInstitutionsMapper.insert(investmentInstitutions2);
            } catch (Exception e) {
                e.printStackTrace();
            }

            iip.setInvestmentInstitutionsId(investmentInstitutions2.getId());
        }else {
            iip.setInvestmentInstitutionsId(ii.get(0).getId());
        }
        iip.setProjectId(projectId); // 项目ID（新的ID）

        try {
            InvestmentInstitutionsProject iipOld = investmentInstitutionsProjectMapper.selectOne(iip);
            if (null == iipOld) {
                investmentInstitutionsProjectMapper.insert(iip);
            }
        } catch (Exception e) {
           LOGGER.error(e.getMessage());
        }
    }

    public static void main(String[] args) {

//        DateTimeFormatter formatter = DateTimeFormat.forPattern();
//        formatter.parseDateTime("Sun Jan 01 00:00:00 CST 2017").toDate();
//        DateTime dt = new DateTime("Sun Jan 01 00:00:00 CST 2017");
    }
}
