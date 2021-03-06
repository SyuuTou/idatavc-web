package com.idatavc.web.mapper;

import com.idatavc.web.model.OriginalDataStage;
import com.idatavc.web.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OriginalDataStageMapper extends OwnerMapper<OriginalDataStage> {

    List<OriginalDataStage> selectByDataId(@Param("dataId") Integer dataId);
}