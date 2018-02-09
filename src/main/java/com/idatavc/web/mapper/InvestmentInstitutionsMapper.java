package com.idatavc.web.mapper;

import com.idatavc.web.model.InvestmentInstitutions;
import com.idatavc.web.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

public interface InvestmentInstitutionsMapper extends OwnerMapper<InvestmentInstitutions> {

    InvestmentInstitutions selectByShortName(@Param("shortName") String shortName);
}