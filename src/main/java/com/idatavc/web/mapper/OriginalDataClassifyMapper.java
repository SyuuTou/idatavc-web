package com.idatavc.web.mapper;

import com.idatavc.web.model.OriginalDataClassify;
import com.idatavc.web.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OriginalDataClassifyMapper extends OwnerMapper<OriginalDataClassify> {

    List<OriginalDataClassify> selectClassifyByDataId(@Param("dataId") Integer dataId);
}