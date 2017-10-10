package com.idatavc.web.controller;

import com.idatavc.web.ExcelPoiHelper;
import com.idatavc.web.MyCell;
import com.idatavc.web.service.ProjectsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Controller
public class PoiController {

    @GetMapping("/")
    public String init(){

        return "welcome";
    }

    private String fileLocation;

    @Resource(name = "excelPoiHelper")
    private ExcelPoiHelper excelPoiHelper;

    @Resource(name = "projectsService")
    private ProjectsService projectsService;

    @PostMapping("/uploadExcelFile")
    public String uploadFile(Model model, MultipartFile file) throws IOException {
        fileLocation = file.getOriginalFilename();
//        if (fileLocation != null) {
//            if (fileLocation.endsWith(".xlsx") || fileLocation.endsWith(".xls")) {
//                Map<Integer, List<MyCell>> data
//                        = excelPoiHelper.readExcel(fileLocation);
//                model.addAttribute("data", data);
//            } else {
//                model.addAttribute("message", "Not a valid excel file!");
//            }
//        } else {
//            model.addAttribute("message", "File missing! Please upload an excel file.");
//        }

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


        projectsService.saveProject(data);

        model.addAttribute("message", "File: " + file.getOriginalFilename()
                + " has been uploaded successfully!");
        return "excel";
    }
}
