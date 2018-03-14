package com.idatavc.web.mapper;

import com.idatavc.web.model.OriginalDataSegmentation;
import com.idatavc.web.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OriginalDataSegmentationMapper extends OwnerMapper<OriginalDataSegmentation> {

    List<OriginalDataSegmentation> selectByDataId(@Param("dataId") Integer dataId);
}