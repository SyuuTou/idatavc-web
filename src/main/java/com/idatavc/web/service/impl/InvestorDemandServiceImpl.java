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
 * Created by lRanhaijulang on 2018/2/4.
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
    private OriginalDataMapper originalDataMapper;

    @Autowired
    private InvestmentInstitutionTeamMapper investmentInstitutionTeamMapper;

    @Override
    public void demand(Map<Integer, List<MyCell>> data) {

        LOGGER.info("beginning..............");
        data.forEach((k,v) -> {
            LOGGER.info("insert current" + k +  "value" );

            OriginalData originalData = new OriginalData();
            if(k<1000){
                originalData.setId((k+1));
            }else{
                originalData.setId((k+49));
            }

            OriginalData originalData1 = originalDataMapper.selectByPrimaryKey(originalData);

            String dataCase = v.get(0).getContent().trim();

            if(dataCase == null || "".equals(dataCase)){
                originalData1.setInvestmentPrepare(null);
            }else{
                String dataCase2 = null;
                if(dataCase.contains("案例：")){
                    dataCase2 = dataCase.substring(dataCase.lastIndexOf("案例：") + 3);
                }else if(dataCase.contains("案例:")){
                    dataCase2 = dataCase.substring(dataCase.lastIndexOf("案例：") + 4);
                }
                originalData1.setInvestmentPrepare(dataCase2);

            }

            originalDataMapper.updateByPrimaryKeySelective(originalData1);
        });
        LOGGER.info("ending...........");
    }

}
