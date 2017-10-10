package com.idatavc.web.service.impl;

import com.idatavc.web.MyCell;
import com.idatavc.web.mapper.*;
import com.idatavc.web.model.*;
import com.idatavc.web.service.ProjectsService;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

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
    @Transactional
    @Override
    public void saveProject(Map<Integer, List<MyCell>> data) {

         int i = 1;
        data.forEach((k,v) ->{

            final Projects projects =new Projects();
            final ProjectFinancingLog projectFinancingLog = new ProjectFinancingLog();
//            final Investors investors = new Investors();
            final InvestmentInstitutions investmentInstitutions = new InvestmentInstitutions();
            final Founders founders = new Founders();
            final FoundersEducation foundersEducation = new FoundersEducation();
            final FoundersWork foundersWork = new FoundersWork();
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
            String index0 = v.get(0).getContent();
            if (!"".equals(index0)){
                projects.setSerialNumber(Integer.valueOf(index0.substring(0,index0.indexOf("."))));
            }
            investmentInstitutions.setShortName(v.get(1).getContent());

//            investmentInstitutions.setCommet(v.get(2).getContent());
            projects.setShortName(v.get(3).getContent());
            projects.setFullName(v.get(4).getContent());
            projects.setKernelDesc(v.get(5).getContent());
            projects.setCommet(v.get(6).getContent());
            projects.setUrl(v.get(7).getContent());
            String establishedTime = v.get(8).getContent();
            if (!"".equals(establishedTime)){

                projects.setEstablishedTime(formatter.parseDateTime(establishedTime).toDate());
            }
            projects.setSegmentation(v.get(9).getContent()); //细分领域




            projects.setItemLabel(v.get(10).getContent()); //项目标签
//            v.get(11)地域
            projects.setTerritory(v.get(11).getContent());
            projects.setAddress(v.get(12).getContent());
            projects.setCreateTime(DateTime.now().toDate());
            String city = v.get(11).getContent();
            MetaRegion queryMR = new MetaRegion();
            queryMR.setQuid(0);
            queryMR.setShengid(0);
                queryMR.setMing(city+"区");
            MetaRegion metaRegion = metaRegionMapper.selectOne(queryMR);
            if (null == metaRegion) {
               queryMR.setMing(city+"市");
               metaRegion = metaRegionMapper.selectOne(queryMR);
            }
            if (null == metaRegion) {
                queryMR.setMing(city);
               metaRegion = metaRegionMapper.selectOne(queryMR);
            }
            if (null != metaRegion) {
                if (metaRegion.getGid() == 45055) {
                    projects.setContinent("亚洲");
                    projects.setCountry("中国");
                    metaRegion = metaRegionMapper.selectByPrimaryKey(metaRegion.getShengid());
                    if (null == metaRegion){
                        projects.setProvince(city);
                    }else {
                        projects.setProvince(metaRegionMapper.selectByPrimaryKey(metaRegion.getShengid()).getMing());
                    }
                    projects.setCity(city);
                }else{
                    projects.setContinent(metaRegionMapper.selectByPrimaryKey(metaRegion.getGid()).getMing());
                    projects.setCountry(metaRegionMapper.selectByPrimaryKey(metaRegion.getGid()).getMing());
                    projects.setCity(metaRegionMapper.selectByPrimaryKey(metaRegion.getGid()).getMing());

                }


            }
//            projects.setCity(v.get(11).getContent());
            if (null != investmentInstitutions.getShortName()&& !"".equals(investmentInstitutions.getShortName())) {
                InvestmentInstitutions queryOne = investmentInstitutionsMapper.selectOne(investmentInstitutions);
                if (null == queryOne) {

                    investmentInstitutionsMapper.insert(investmentInstitutions);
                    projects.setInvestmentInstitutionsId(investmentInstitutions.getId());
                } else {
                    projects.setInvestmentInstitutionsId(queryOne.getId());
                }
            }
            Projects queryP = projectsMapper.selectOne(projects);
            Integer projectId = null;
            if (null == queryP) {
                projectsMapper.insert(projects);
                projectFinancingLog.setProjectId(projects.getId());
                projectId = projects.getId();
            }else{
                projectFinancingLog.setProjectId(queryP.getId());
                projectId = queryP.getId();
            }


//            DateTimeFormatter formatDate = DateTimeFormat.forPattern("yyyy-MM-dd");
            String financingTimeCell = v.get(13).getContent();
            projectFinancingLog.setFinancingTime("".equals(financingTimeCell) ? null : formatter.parseDateTime(financingTimeCell.length() < 9 ? financingTimeCell + "-01" : financingTimeCell).toDate());
            projectFinancingLog.setStage(v.get(14).getContent());
            String amount = v.get(15).getContent();
            if ("".equals(amount)){
                amount = "0";
            }
            projectFinancingLog.setAmount(new BigDecimal(amount));
            projectFinancingLog.setCurrency(v.get(16).getContent().equals("人民币")? 0 : 1);
            String stockRight = v.get(17).getContent();
            if ("".equals(stockRight)){
                stockRight = "0";
            }
            projectFinancingLog.setStockRight(new BigDecimal(stockRight));
            String pr = v.get(18).getContent();
            if (pr.indexOf("未透露") > 0){
                projectFinancingLog.setPrAmount(new BigDecimal("100"));
            }
            if (pr.indexOf("十") > 0){
                projectFinancingLog.setPrAmount(new BigDecimal("20"));
            }
            if (pr.indexOf("百") > 0){
                projectFinancingLog.setPrAmount(new BigDecimal("200"));
            }
            if (pr.indexOf("千") > 0){
                projectFinancingLog.setPrAmount(new BigDecimal("1000"));
            }
            if (pr.indexOf("亿") > 0){
                projectFinancingLog.setPrAmount(new BigDecimal("5000"));
            }

            String totalAmount = v.get(19).getContent();
            if (totalAmount.indexOf("未透露") > 0){
                projectFinancingLog.setTotalAmount(new BigDecimal("100"));
            }else
            if (totalAmount.indexOf("十") >= 0){
                projectFinancingLog.setTotalAmount(new BigDecimal("20"));
            }else
            if (totalAmount.indexOf("百") >= 0){
                projectFinancingLog.setTotalAmount(new BigDecimal("200"));
            }else
            if (totalAmount.indexOf("千") >= 0){
                projectFinancingLog.setTotalAmount(new BigDecimal("1000"));
            }else
            if (totalAmount.indexOf("亿") >= 0){
                projectFinancingLog.setTotalAmount(new BigDecimal("5000"));
            }else
            if (null != totalAmount&&!"".equals(totalAmount)){
                projectFinancingLog.setTotalAmount(new BigDecimal(totalAmount));
            }
            projectFinancingLog.setShareDivest(v.get(20).getContent());
            String valuation = v.get(21).getContent();
            if ("".equals(valuation) || null == valuation){
                valuation = "0";
            }
            if (valuation.indexOf("万") > 0) {
                valuation = valuation.substring(0,valuation.indexOf("万"));
            }

            projectFinancingLog.setValuation(new BigDecimal(null == valuation?"0": valuation));
            projectFinancingLog.setInvestmentInstitutionsList(v.get(22).getContent());
            projectFinancingLog.setProportionList(v.get(23).getContent());
            projectFinancingLog.setCreateTime(DateTime.now().toDate());
            projectFinancingLog.setStatus(2);
            if (null == projectFinancingLogMapper.selectOne(projectFinancingLog)) {
                projectFinancingLogMapper.insert(projectFinancingLog);
            }

            founders.setName(v.get(24).getContent());
            founders.setPosition(v.get(25).getContent());
            founders.setIntroduction(v.get(26).getContent());
            founders.setEducationalBackgroundDesc(v.get(28).getContent());
            founders.setWorkingBackgroundDesc(v.get(30).getContent());
            founders.setEntrepreneurialExperience(v.get(31).getContent());
            founders.setCreateTime(DateTime.now().toDate());
            founders.setYn(1);
            founders.setProjectId(projectId);
            Founders queryF = foundersMapper.selectOne(founders);
            if (null == queryF) {
                foundersMapper.insert(founders);
                foundersEducation.setFounderId(founders.getId());
                foundersWork.setFounderId(founders.getId());
            }else{
                foundersEducation.setFounderId(queryF.getId());
                foundersWork.setFounderId(queryF.getId());
            }

            String[] foundersEducations = v.get(27).getContent().split("、");
            foundersEducation.setFounderId(founders.getId());
            for(String fe : foundersEducations){
                foundersEducation.setEducationExperience(fe);
                foundersEducationMapper.insert(foundersEducation);

            }

            String[] foundersWorks = v.get(29).getContent().split("、");
            foundersWork.setFounderId(founders.getId());
            for (String fw : foundersWorks){
                foundersWork.setWorkExperience(fw);
                foundersWorkMapper.insert(foundersWork);
            }

            String[] seStr = v.get(9).getContent().split("、");
            ProjectSegmentation projectSegmentation = new ProjectSegmentation();
            projectSegmentation.setProjectId(projectId);
            for(String s : seStr){
                projectSegmentation.setSegmentationName(s);
                projectSegmentationMapper.insert(projectSegmentation);
            }

        });
    }

    public static void main(String[] args) {

//        DateTimeFormatter formatter = DateTimeFormat.forPattern();
//        formatter.parseDateTime("Sun Jan 01 00:00:00 CST 2017").toDate();
//        DateTime dt = new DateTime("Sun Jan 01 00:00:00 CST 2017");


    }
}
