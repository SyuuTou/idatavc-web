package com.idatavc.web.controller;

import com.idatavc.web.dto.CommonDto;
import com.idatavc.web.service.ImportDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by caochuangui on 2018/2/11.
 */
@RestController
public class dataController extends GenericController{

    @Resource
    private ImportDataService importDataService;

    @GetMapping("/importdata")
    public CommonDto<String> importData(){
        CommonDto<String> result = new CommonDto<>();

        try {
            result = importDataService.importData();
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setData(null);
            result.setMessage("服务器端发生错误");
            result.setStatus(502);
        }

        return result;
    }

}
