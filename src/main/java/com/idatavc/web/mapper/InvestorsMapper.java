package com.idatavc.web.mapper;

import com.idatavc.web.model.Investors;
import com.idatavc.web.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

public interface InvestorsMapper extends OwnerMapper<Investors> {
    Investors selectByInstitutionIdAndName(@Param("name")String name, @Param("institutionId") Integer institutionId);
}