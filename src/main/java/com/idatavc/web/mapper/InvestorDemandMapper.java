package com.idatavc.web.mapper;

import com.idatavc.web.model.InvestorDemand;
import com.idatavc.web.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

public interface InvestorDemandMapper extends OwnerMapper<InvestorDemand> {
    InvestorDemand selectByInvestorId(@Param("investorId")Integer investorId);
}