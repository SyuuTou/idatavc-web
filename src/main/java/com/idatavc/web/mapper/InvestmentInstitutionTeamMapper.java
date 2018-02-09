package com.idatavc.web.mapper;

import com.idatavc.web.model.InvestmentInstitutionTeam;
import com.idatavc.web.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

public interface InvestmentInstitutionTeamMapper extends OwnerMapper<InvestmentInstitutionTeam> {

    InvestmentInstitutionTeam selectByInvestorId(@Param("investorId") Integer investorId);

}