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
    private OriginalDataMapper originalDataMapper;

    @Autowired
    private InvestmentInstitutionTeamMapper investmentInstitutionTeamMapper;

    @Override
    public void demand(Map<Integer, List<MyCell>> data) {

        LOGGER.info("beginning..............");
        data.forEach((k,v) -> {
            LOGGER.info("insert current" + k +  "value" );

            OriginalData originalData = new OriginalData();
            originalData.setPreferenceAndPrecautions(v.get(0).getContent().trim());
            originalData.setCreateWechatDate(v.get(1).getContent().trim());
            originalData.setWechatNumber(v.get(2).getContent().trim());
            originalData.setInstitutionName(v.get(3).getContent().trim());
            originalData.setChargePeople(v.get(4).getContent().trim());
            originalData.setInstitutionDesc(v.get(5).getContent().trim());
            originalData.setFundSize(v.get(6).getContent().trim());
            originalData.setInstitutionLevel(v.get(7).getContent().trim());
            originalData.setInstitutionClassify(v.get(8).getContent().trim());
            originalData.setInstitutionInternalOrganization(v.get(9).getContent().trim());
            originalData.setInstitutionSource(v.get(10).getContent().trim());
            originalData.setDataFullYn(0);
            originalData.setUserName(v.get(12).getContent().trim());
            originalData.setCompanyDuties(v.get(13).getContent().trim());
            Integer workCard = 0;
            if ("是".equals(v.get(14).getContent().trim())){
                workCard = 1;
            }
            originalData.setWorkCard(workCard);
            originalData.setRgzlAttention(v.get(15).getContent().trim());
            originalData.setRgzlDetail(v.get(16).getContent().trim());
            originalData.setZnzzAttention(v.get(17).getContent().trim());
            originalData.setZnzzDetail(v.get(18).getContent().trim());
            originalData.setQcjthwlAttention(v.get(19).getContent().trim());
            originalData.setQcjthwlDetail(v.get(20).getContent().trim());
            originalData.setAqkjAttention(v.get(21).getContent().trim());
            originalData.setAqkjDetail(v.get(22).getContent().trim());
            originalData.setXpbdtAttention(v.get(23).getContent().trim());
            originalData.setXpbdtDetail(v.get(24).getContent().trim());
            originalData.setJrkjAttention(v.get(25).getContent().trim());
            originalData.setJrkjDetail(v.get(26).getContent().trim());
            originalData.setJykjAttention(v.get(27).getContent().trim());
            originalData.setJykjDetail(v.get(28).getContent().trim());
            originalData.setJzylAttention(v.get(29).getContent().trim());
            originalData.setJzylDetail(v.get(30).getContent().trim());
            originalData.setQyfwAttention(v.get(31).getContent().trim());
            originalData.setQyfwDetail(v.get(32).getContent().trim());
            originalData.setXfsjAttention(v.get(33).getContent().trim());
            originalData.setXfsjDetail(v.get(34).getContent().trim());
            originalData.setWycmAttention(v.get(35).getContent().trim());
            originalData.setWycmDetail(v.get(36).getContent().trim());
            originalData.setTmtAttention(v.get(37).getContent().trim());
            originalData.setXclAttenton(v.get(38).getContent().trim());
            originalData.setXnyAttention(v.get(39).getContent().trim());
            originalData.setWlwAttention(v.get(40).getContent().trim());
            originalData.setQklAttention(v.get(41).getContent().trim());
            originalData.setHlwAttention(v.get(42).getContent().trim());
            originalData.setHlwDetail(v.get(43).getContent().trim());
            originalData.setArVrAttention(v.get(44).getContent().trim());
            originalData.setOther(v.get(45).getContent().trim());
            originalData.setAngerRound(v.get(46).getContent().trim());
            originalData.setPreARound(v.get(47).getContent().trim());
            originalData.setaRound(v.get(48).getContent().trim());
            originalData.setbRound(v.get(49).getContent().trim());
            originalData.setcRound(v.get(50).getContent().trim());
            originalData.setBinggoRound(v.get(51).getContent().trim());
            originalData.setdRound(v.get(52).getContent().trim());
            originalData.setPreIpoRound(v.get(53).getContent().trim());
            originalData.setCity(v.get(54).getContent().trim());
            originalData.setOneInvestmentCount(v.get(55).getContent().trim());
            originalData.setInvestmentNumFuture(v.get(56).getContent().trim());
            originalData.setInvestmentPrepare(v.get(57).getContent().trim());

            originalDataMapper.insertSelective(originalData);


        });
        LOGGER.info("ending...........");
    }

}
