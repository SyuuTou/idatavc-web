package com.idatavc.web.controller;

import com.idatavc.web.ExcelPoiHelper;
import com.idatavc.web.MyCell;
import com.idatavc.web.model.InvestorDemand;
import com.idatavc.web.service.InvestmentInstitutionService;
import com.idatavc.web.service.InvestorDemandService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by lanhaijulang on 2018/2/4.
 */
@Controller
@RequestMapping("/d")
public class InvestorDemandController {

    @RequestMapping(value = "/demand")
    public String initD(){
        return "welcome_d";
    }

    private String fileLocation;

    @Resource(name = "excelPoiHelper")
    private ExcelPoiHelper excelPoiHelper;

    @Resource
    private InvestorDemandService investorDemandService;

    @PostMapping("/demandExcelFile")
    public String uploadDemandExcelFile(Model model, MultipartFile file) throws IOException {
        fileLocation = file.getOriginalFilename();

        InputStream in = file.getInputStream();
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        fileLocation = path.substring(0, path.length() - 1) + file.getOriginalFilename();
        FileOutputStream f = new FileOutputStream(fileLocation);
        int ch = 0;
        while ((ch = in.read()) != -1) {
            f.write(ch);
        }
        f.flush();
        f.close();

        Map<Integer, List<MyCell>> data
                = excelPoiHelper.readExcel(new File(fileLocation));

        investorDemandService.demand(data);

        return "excel";
    }

}


