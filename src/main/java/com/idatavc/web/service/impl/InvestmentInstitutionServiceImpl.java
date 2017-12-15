package com.idatavc.web.service.impl;

import com.idatavc.web.MyCell;
import com.idatavc.web.mapper.InvestmentInstitutionsAddressMapper;
import com.idatavc.web.mapper.InvestmentInstitutionsMapper;
import com.idatavc.web.mapper.InvestmentInstitutionsTypeMapper;
import com.idatavc.web.model.InvestmentInstitutions;
import com.idatavc.web.model.InvestmentInstitutionsAddress;
import com.idatavc.web.model.InvestmentInstitutionsType;
import com.idatavc.web.service.InvestmentInstitutionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InvestmentInstitutionServiceImpl implements InvestmentInstitutionService{

    private static final Logger LOGGER = LoggerFactory.getLogger(InvestmentInstitutionService.class);

    @Autowired
    private InvestmentInstitutionsMapper investmentInstitutionsMapper;
    @Autowired
    private InvestmentInstitutionsAddressMapper investmentInstitutionsAddressMapper;
    @Autowired
    private InvestmentInstitutionsTypeMapper investmentInstitutionsTypeMapper;
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
            investmentInstitutionsTypeMapper.insert(investmentInstitutionsType);
            String[] adrs = v.get(6).getContent().split("\\\n");
            InvestmentInstitutionsAddress investmentInstitutionsAddress = new InvestmentInstitutionsAddress();
            investmentInstitutionsAddress.setInvestmentInstitutionId(investmentInstitutions.getId());
            investmentInstitutionsAddress.setPhoneNumber(v.get(4).getContent());
            investmentInstitutionsAddress.setEmail(v.get(5).getContent());

            for (String adr : adrs){
                investmentInstitutionsAddress.setDetailAddress(adr);
                investmentInstitutionsAddressMapper.insert(investmentInstitutionsAddress);
            }


            LOGGER.info("{}",1);
        });
        LOGGER.info("Investment be import . Ending.....");
    }
}
