package com.idatavc.web.mapper;

import com.idatavc.web.model.FoundersEducation;
import com.idatavc.web.utils.OwnerMapper;

import java.util.List;

public interface FoundersEducationMapper extends OwnerMapper<FoundersEducation> {
    List<String> selectEdus();

}