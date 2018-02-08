package com.idatavc.web.service.impl;

import com.idatavc.web.MyCell;
import com.idatavc.web.mapper.*;
import com.idatavc.web.model.*;
import com.idatavc.web.service.InvestmentInstitutionService;
import com.idatavc.web.service.InvestorDemandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by lanhaijulang on 2018/2/4.
 */
@Service
public class InvestorDemandServiceImpl implements InvestorDemandService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvestmentInstitutionService.class);

    @Autowired
    private InvestmentInstitutionsMapper investmentInstitutionsMapper;

    @Autowired
    private InvestorsMapper investorsMapper;

    @Autowired
    private DatasOperationManageMapper datasOperationManageMapper;

    @Autowired
    private InvestmentInstitutionTeamMapper investmentInstitutionTeamMapper;

    @Override
    public void demand(Map<Integer, List<MyCell>> data) {

        LOGGER.info("beginning..............");
        data.forEach((k,v) -> {
            LOGGER.info("insert current" + k +  "value" );
            InvestmentInstitutions investmentInstitutions = new InvestmentInstitutions();
            investmentInstitutions.setShortName(v.get(1).getContent().trim());
            List<InvestmentInstitutions> result = investmentInstitutionsMapper.select(investmentInstitutions);
            if (null == result || result.size() == 0){
                investmentInstitutionsMapper.insert(investmentInstitutions);
            }else{
                investmentInstitutionsMapper.updateByPrimaryKeySelective(investmentInstitutions);

            }

            Investors investors = new Investors();
            investors.setName(v.get(2).getContent().trim());
            investors.setInvestmentInstitutionsId(investmentInstitutions.getId());
            Investors result1 = investorsMapper.selectByInstitutionIdAndName(v.get(2).getContent().trim(), investmentInstitutions.getId());
            Integer investorId = null;
            if (null == result1){
                investors.setPosition(v.get(3).getContent().trim());
                investorsMapper.insert(investors);
                investorId = investors.getId();
            }else{
                investors.setId(result1.getId());
                investors.setPosition(v.get(3).getContent().trim());
                investorsMapper.updateByPrimaryKeySelective(investors);
                investorId = result1.getId();
            }

            DatasOperationManage datasOperationManage = new DatasOperationManage();
            datasOperationManage.setDataId(investors.getId());
            datasOperationManage.setDataType("INVESTOR");
            DatasOperationManage result2 = datasOperationManageMapper.selectByPrimaryKey(datasOperationManage);
            if(null == result2){
                datasOperationManage.setWechatGroupId(v.get(0).getContent().trim());
                datasOperationManageMapper.insert(datasOperationManage);
            }else{
                datasOperationManage.setWechatGroupId(v.get(0).getContent().trim());
                datasOperationManageMapper.updateByPrimaryKeySelective(datasOperationManage);
            }

            InvestmentInstitutionTeam investmentInstitutionTeamRecord = new InvestmentInstitutionTeam();
            investmentInstitutionTeamRecord.setInvestmentInstitutionId(investmentInstitutions.getId());
            investmentInstitutionTeamRecord.setInvestorId(investorId);
            List<InvestmentInstitutionTeam> investmentInstitutionTeams = investmentInstitutionTeamMapper.select(investmentInstitutionTeamRecord);

            if (null == investmentInstitutionTeams && investmentInstitutionTeams.size() <=0){
                investmentInstitutionTeamMapper.insert(investmentInstitutionTeamRecord);
            }


        });
        LOGGER.info("ending...........");
    }

}
