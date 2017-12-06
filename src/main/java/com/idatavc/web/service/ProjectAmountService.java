package com.idatavc.web.service;

import com.idatavc.web.dto.CommonDto;
import org.springframework.stereotype.Service;

@Service
public interface ProjectAmountService {
    CommonDto<String> projectAmoutAdd();
    CommonDto<String> projetcAllAmountAdd();
}
