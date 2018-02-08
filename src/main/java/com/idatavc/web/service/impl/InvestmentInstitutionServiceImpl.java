package com.idatavc.web.service.impl;

import com.idatavc.web.MyCell;
import com.idatavc.web.mapper.*;
import com.idatavc.web.model.*;
import com.idatavc.web.service.InvestmentInstitutionService;
import com.idatavc.web.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class InvestmentInstitutionServiceImpl implements InvestmentInstitutionService{

    private static final Logger LOGGER = LoggerFactory.getLogger(InvestmentInstitutionService.class);

    @Autowired
    private InvestmentInstitutionsMapper investmentInstitutionsMapper;
    @Autowired
    private InvestmentInstitutionsAddressMapper investmentInstitutionsAddressMapper;
    @Autowired
    private InvestmentInstitutionsTypeMapper investmentInstitutionsTypeMapper;
    @Autowired
    private InvestmentInstitutionTeamMapper investmentInstitutionTeamMapper;
    @Autowired
    private InvestmentInstitutionsMemberWorkMapper investmentInstitutionsMemberWorkMapper;
    @Autowired
    private InvestmentInstitutionsMemberEducationMapper investmentInstitutionsMemberEducationMapper;
    @Autowired
    private InvestmentInstitutionsProjectMapper investmentInstitutionsProjectMapper;
    @Autowired
    private ProjectFinancingLogMapper projectFinancingLogMapper;
    @Autowired
    private InvestorDemandMapper investorDemandMapper;
    @Autowired
    private InvestorCityMapper investorCityMapper;
    @Autowired
    private InvestorDemandSegmentationMapper investorDemandSegmentationMapper;
    @Autowired
    private InvestorDemandStageMapper investorDemandStageMapper;
    @Autowired
    private InvestorInvestmentCaseMapper investorInvestmentCaseMapper;
    @Autowired
    private InvestorsMapper investorsMapper;
    @Autowired
    private DatasOperationManageMapper datasOperationManageMapper;
    @Autowired
    private MetaProjectStageMapper metaProjectStageMapper;

    @Override
    public void save(int i, Map<Integer, List<MyCell>> data) {

        LOGGER.info("Investment be import . Beginning.....");

        data.forEach((k, v) -> {
            InvestmentInstitutions investmentInstitutions = new InvestmentInstitutions();
            investmentInstitutions.setShortName(v.get(0).getContent().trim());
            List<InvestmentInstitutions> result = investmentInstitutionsMapper.select(investmentInstitutions);
            Integer iiId = null;
            if (null == result || result.size() == 0){
                investmentInstitutions.setComment(v.get(1).getContent().trim());
                investmentInstitutions.setHomeUrl(v.get(2).getContent().trim());
                investmentInstitutionsMapper.insert(investmentInstitutions);
            }else{
                investmentInstitutions = result.get(0);
                investmentInstitutions.setComment(v.get(1).getContent().trim());
                investmentInstitutions.setHomeUrl(v.get(2).getContent().trim());
                investmentInstitutionsMapper.updateByPrimaryKeySelective(investmentInstitutions);
            }
            InvestmentInstitutionsType investmentInstitutionsType = new InvestmentInstitutionsType();
            investmentInstitutionsType.setInvestmentInstitutionsId(investmentInstitutions.getId());
            investmentInstitutionsType.setType(v.get(3).getContent());
            try {
                investmentInstitutionsTypeMapper.insert(investmentInstitutionsType);
            } catch (Exception e) {

            }




            String[] adrs = v.get(6).getContent().split("\\\n");
            InvestmentInstitutionsAddress investmentInstitutionsAddress = new InvestmentInstitutionsAddress();
            investmentInstitutionsAddress.setInvestmentInstitutionId(investmentInstitutions.getId());
            investmentInstitutionsAddress.setPhoneNumber(v.get(4).getContent());
            investmentInstitutionsAddress.setEmail(v.get(5).getContent());

            for (String adr : adrs){
                investmentInstitutionsAddress.setDetailAddress(adr);
                investmentInstitutionsAddress.setId(null);
                investmentInstitutionsAddressMapper.insert(investmentInstitutionsAddress);
            }





            LOGGER.info("{}",k);
        });
        LOGGER.info("Investment be import . Ending.....");
    }

    @Override
    public void compare(Map<Integer, List<MyCell>> data) {
        AtomicInteger num = new AtomicInteger();
//        data.forEach((k, v) -> {
//            checkData(v.get(0).getContent().trim(), data);
//
//        });


        try {
            //创建excel工作簿
            Workbook wb = new HSSFWorkbook();
            //创建第一个sheet（页），命名为 new sheet
            Sheet sheet = wb.createSheet("new sheet");
            AtomicReference<Row> row = new AtomicReference<>();
            Cell cell = null;
            AtomicInteger index = new AtomicInteger();
            int j = 0;
            String amountd = "";
//            for (Map.Entry<Integer, List<MyCell>> entry : data.entrySet()){
//                row = sheet.createRow( index);
//                row.get().createCell(0).setCellValue(v.get(0).getContent());
//                row.get().createCell(1).setCellValue(v.get(1).getContent());
//                index.getAndIncrement();
//                LOGGER.info("{},{}",v.get(0).getContent(),v.get(1).getContent());
//            }

            data.forEach((k,v) -> {
                row.set(sheet.createRow( index.get()));
                row.get().createCell(0).setCellValue(v.get(0).getContent());
                row.get().createCell(1).setCellValue(v.get(1).getContent());
                String newFile = MD5Util.md5Encode(v.get(0).getContent(),"utf-8");
                new File("/Users/zhhu/Downloads/LOGO/"+v.get(1).getContent()).renameTo(new File("/Users/zhhu/Downloads/LOGO/"+newFile+".jpg"));
                row.get().createCell(2).setCellValue(newFile+".jpg");
                index.getAndIncrement();
                LOGGER.info("{},{}",v.get(0).getContent(),v.get(1).getContent());
            });

            File forlder = new File("/Users/zhhu/Downloads/LOGO");
            for(String file : forlder.list()){

            }


            //创建一个文件 命名为workbook.xls
            FileOutputStream fileOut = new FileOutputStream("workbook4.xls");
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
    public void member(Map<Integer, List<MyCell>> data) {
        this.LOGGER.info("beginning ........");
        data.forEach((k,v) -> {
            this.LOGGER.info("{}",k);
            InvestmentInstitutions query = new InvestmentInstitutions();
            query.setShortName(v.get(0).getContent().trim());
            Integer investmentInstitutionsId = null;
            List<InvestmentInstitutions> investmentInstitutionsList = investmentInstitutionsMapper.select(query);
            if (null != investmentInstitutionsList && investmentInstitutionsList.size() > 0){
                investmentInstitutionsId = investmentInstitutionsList.get(0).getId();

                InvestmentInstitutionTeam investmentInstitutionTeam = new InvestmentInstitutionTeam();
                investmentInstitutionTeam.setActualName(v.get(1).getContent().trim());
                investmentInstitutionTeam.setCompanyDuties(v.get(2).getContent().trim());
                investmentInstitutionTeam.setMemberDesc(v.get(3).getContent().trim());
                investmentInstitutionTeam.setInvestmentInstitutionId(investmentInstitutionsId);
                String teamType = v.get(4).getContent().trim();
                if (teamType.equals("管理团队")){
                investmentInstitutionTeam.setMetaIitTypeId(1);
                }else if (teamType.equals("投资团队")){
                    investmentInstitutionTeam.setMetaIitTypeId(2);

                }else if (teamType.equals("投后团队")){
                    investmentInstitutionTeam.setMetaIitTypeId(3);

                }else if (teamType.equals("检查团队")){
                    investmentInstitutionTeam.setMetaIitTypeId(4);
                }
                investmentInstitutionTeam.setWorkYn(1);
                investmentInstitutionTeam.setInvestmentCase(v.get(10).getContent());
                investmentInstitutionTeam.setPicture(v.get(11).getContent());
                investmentInstitutionTeam.setInvestmentSegmentation(v.get(5).getContent());
                investmentInstitutionTeam.setInvestmentStage(v.get(6).getContent());
                investmentInstitutionTeam.setCity(v.get(7).getContent());
                investmentInstitutionTeamMapper.insert(investmentInstitutionTeam);

                InvestmentInstitutionsMemberEducation investmentInstitutionsMemberEducation = null;
                InvestmentInstitutionsMemberWork investmentInstitutionsMemberWork = null;
                String works = v.get(8).getContent().trim();
                String edus = v.get(9).getContent().trim();

                if (StringUtils.isNotEmpty(works)){
                    for (String work : works.split("、")){
                        investmentInstitutionsMemberWork = new InvestmentInstitutionsMemberWork();
                        investmentInstitutionsMemberWork.setMemberId(investmentInstitutionTeam.getId());
                        investmentInstitutionsMemberWork.setWorkExperience(work);
                        investmentInstitutionsMemberWorkMapper.insert(investmentInstitutionsMemberWork);
                    }
                }

                if (StringUtils.isNotEmpty(edus)){
                    for (String edu : edus.split("、")){
                        investmentInstitutionsMemberEducation = new InvestmentInstitutionsMemberEducation();
                        investmentInstitutionsMemberEducation.setMemberId(investmentInstitutionTeam.getId());
                        investmentInstitutionsMemberEducation.setEducationExperience(edu);
                        investmentInstitutionsMemberEducationMapper.insert(investmentInstitutionsMemberEducation);
                    }
                }

                //投资人
                Investors investors = new Investors();
                investors.setName(v.get(1).getContent().trim());
                LOGGER.info("投资人：{}", v.get(1).getContent().trim());
                investors.setInvestmentInstitutionsId(investmentInstitutionsId);
                Investors result1 = investorsMapper.selectByInstitutionIdAndName(v.get(2).getContent().trim(), investmentInstitutionsId);
                Integer investorId = null;
                if (null == result1){
                    investors.setPosition(v.get(2).getContent().trim());
                    investorsMapper.insert(investors);
                    investorId = investors.getId();
                }else{
                    investors.setId(result1.getId());
                    investors.setPosition(v.get(2).getContent().trim());
                    investorsMapper.updateByPrimaryKeySelective(investors);
                    investorId = result1.getId();
                }

                DatasOperationManage datasOperationManage = new DatasOperationManage();
                datasOperationManage.setDataId(investors.getId());
                datasOperationManage.setDataType("INVESTOR");
                DatasOperationManage result2 = datasOperationManageMapper.selectByPrimaryKey(datasOperationManage);
                if(null == result2){
//                    datasOperationManage.setWechatGroupId(v.get(0).getContent().trim());
                    datasOperationManageMapper.insert(datasOperationManage);
                }else{
//                    datasOperationManage.setWechatGroupId(v.get(0).getContent().trim());
                    datasOperationManageMapper.updateByPrimaryKeySelective(datasOperationManage);
                }


                InvestorDemand investorDemand = new InvestorDemand();
                investorDemand.setAppid(1);
                investorDemand.setInvestorId(investorId);
                investorDemand.setCreatTime(DateTime.now().toDate());

                investorDemandMapper.insert(investorDemand);


                InvestorDemandStage investorDemandStage = null;
                for (String stage : v.get(6).getContent().split("、")){
                    investorDemandStage = new InvestorDemandStage();
                    investorDemandStage.setAppid(1);
                    investorDemandStage.setInvestorDemandId(investorDemand.getId());
                    MetaProjectStage metaProjectStage = new MetaProjectStage();
                    metaProjectStage.setName(stage);
                    metaProjectStage = metaProjectStageMapper.selectOne(metaProjectStage);
                    if (null != metaProjectStage) {
                        investorDemandStage.setMetaProjectStageId(metaProjectStage.getId());
                    }
                    LOGGER.info("阶段：{}", stage);
                    investorDemandStageMapper.insert(investorDemandStage);
                }

                InvestorDemandSegmentation investorDemandSegmentation = null;

                for (String segmentation : v.get(5).getContent().split("、")){
                    investorDemandSegmentation = new InvestorDemandSegmentation();
                    investorDemandSegmentation.setAppid(1);
                    investorDemandSegmentation.setInvestorDemandId(investorDemand.getId());
                    investorDemandSegmentation.setSegmentation(segmentation);
                    this.LOGGER.info("领域：{}",segmentation);
                    investorDemandSegmentationMapper.insert(investorDemandSegmentation);
                }


                InvestorCity investorCity = null;

                for (String city : v.get(7).getContent().split("、")){
                    investorCity = new InvestorCity();
                    investorCity.setCity(city);
                    this.LOGGER.info("常驻城市：{}",city);
                    investorCity.setId(investorId);
                    try {
                        investorCityMapper.insert(investorCity);
                    } catch (Exception e) {

                    }
                }

                InvestorInvestmentCase investorInvestmentCase = null;

                for (String investorCase : v.get(10).getContent().split("、")){
                    investorInvestmentCase = new InvestorInvestmentCase();
                    investorInvestmentCase.setInvestmentCase(investorCase.trim());
                    investorInvestmentCase.setInvestorId(investorId);
                    this.LOGGER.info("案例：{}",investorCase);
                    investorInvestmentCaseMapper.insert(investorInvestmentCase);
                }






            }
        });
        this.LOGGER.info("ending ........");
    }

    @Transactional
    @Override
    public void updateLogo(Map<Integer, List<MyCell>> data) {
        data.forEach((k,v) -> {
            InvestmentInstitutions query = new InvestmentInstitutions();
            query.setShortName(v.get(0).getContent().trim());

            List<InvestmentInstitutions> investmentInstitutionsList = investmentInstitutionsMapper.select(query);

            for (InvestmentInstitutions investmentInstitutions : investmentInstitutionsList){
                investmentInstitutions.setLogo(v.get(2).getContent().trim());
                investmentInstitutionsMapper.updateByPrimaryKey(investmentInstitutions);
            }

        });
    }

    @Transactional
    @Override
    public void handlerData(Map<Integer, List<MyCell>> data) {
        data.forEach((k,v) -> {

            String flag = v.get(2).getContent();
            if (StringUtils.isNotEmpty(flag)){
                LOGGER.info(flag);
                InvestmentInstitutions query = new InvestmentInstitutions();
                query.setShortName(v.get(0).getContent().trim());
                List<InvestmentInstitutions> investmentInstitutionsList = investmentInstitutionsMapper.select(query);
                for (InvestmentInstitutions investmentInstitutions : investmentInstitutionsList){
                    if (flag.trim().equals("去掉")){
                        investmentInstitutionsMapper.delete(investmentInstitutions);
                        LOGGER.info("delete {}",flag);
                    }else{
                        investmentInstitutions.setShortName(v.get(2).getContent().trim());
                        investmentInstitutionsMapper.updateByPrimaryKey(investmentInstitutions);
                        if (StringUtils.isNotEmpty(v.get(3).getContent())){
                            InvestmentInstitutionsProject queryII = new InvestmentInstitutionsProject();
                            queryII.setInvestmentInstitutionsId(investmentInstitutions.getId());
                            ProjectFinancingLog projects = null;
                            List<InvestmentInstitutionsProject> institutionsProjects = investmentInstitutionsProjectMapper.select(queryII);
                            for (InvestmentInstitutionsProject iip : institutionsProjects){
                                LOGGER.info("update {}", flag);
                                projects = projectFinancingLogMapper.selectByPrimaryKey(iip.getProjectId());
                                projects.setInvestmentInstitutionsList(projects.getInvestmentInstitutionsList()+",补充相关机构："+v.get(3).getContent());
                                projectFinancingLogMapper.updateByPrimaryKey(projects);
                            }
                        }
                    }
                }
            }

        });
        LOGGER.info("Handler data ending...............");
    }

    private int checkData(String myCell, Map<Integer, List<MyCell>> data) {
        AtomicInteger num = new AtomicInteger();
        data.forEach((k, v) -> {

            if (myCell.length() < v.get(0).getContent().length()&& v.get(0).getContent().indexOf(myCell)> -1){
                v.get(1).setContent(myCell);
            }
        });
        return num.get();
    }

    public static void main(String[] args) {
        System.out.println("红杉中国".indexOf("红杉"));
    }
}
