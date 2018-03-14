package com.idatavc.web.mapper;

import com.idatavc.web.model.OriginalDataCity;
import com.idatavc.web.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OriginalDataCityMapper extends OwnerMapper<OriginalDataCity> {

    List<OriginalDataCity> selectByDataId(@Param("dataId") Integer dataId);
}