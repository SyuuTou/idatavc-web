package com.idatavc.web.controller;

import com.idatavc.web.ExcelPoiHelper;
import com.idatavc.web.MyCell;
import com.idatavc.web.service.InvestmentInstitutionService;
import com.idatavc.web.service.ProjectsService;
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

@Controller
@RequestMapping("/i")
public class InvestmentController {

    @RequestMapping(value = "/invest")
    public String init(){

        return "welcome_i";
    }

    @RequestMapping(value = "/index")
    public String initM(){

        return "welcome_m";
    }

    private String fileLocation;

    @Resource(name = "excelPoiHelper")
    private ExcelPoiHelper excelPoiHelper;

    @Resource
    private InvestmentInstitutionService investmentInstitutionService;


    @PostMapping("/memberExcelFile")
    public String uploadMemberFile(Model model, MultipartFile file) throws IOException {
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
        investmentInstitutionService.member(data);

//        investmentInstitutionService.updateLogo(data);
//        investmentInstitutionService.handlerData(data);
        return "excel";
    }

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
//        Map<Integer, List<MyCell>> temp1 = new HashMap<>();
//        Map<Integer, List<MyCell>> temp2 = new HashMap<>();
//        Map<Integer, List<MyCell>> temp3 = new HashMap<>();
//        Map<Integer, List<MyCell>> temp4 = new HashMap<>();
//        Map<Integer, List<MyCell>> temp5 = new HashMap<>();
//        Map<Integer, List<MyCell>> temp6 = new HashMap<>();
//        Map<Integer, List<MyCell>> temp7 = new HashMap<>();
//        Map<Integer, List<MyCell>> temp8 = new HashMap<>();
//        Map<Integer, List<MyCell>> temp9 = new HashMap<>();
//        Map<Integer, List<MyCell>> temp10 = new HashMap<>();
//        Map<Integer, List<MyCell>> temp11 = new HashMap<>();
//        Map<Integer, List<MyCell>> temp12 = new HashMap<>();
//        data.keySet().forEach(k -> {
//            switch (k % 12) {//分割成11份
//                case 0 :
//                    temp1.put(k, data.get(k));
//                    break;
//                case 1 :
//                    temp2.put(k, data.get(k));
//                    break;
//                case 2 :
//                    temp3.put(k, data.get(k));
//                    break;
//                case 3 :
//                    temp4.put(k, data.get(k));
//                    break;
//                case 4 :
//                    temp5.put(k, data.get(k));
//                    break;
//                case 5 :
//                    temp6.put(k, data.get(k));
//                    break;
//                case 6 :
//                    temp7.put(k, data.get(k));
//                    break;
//                case 7 :
//                    temp8.put(k, data.get(k));
//                    break;
//                case 8 :
//                    temp9.put(k, data.get(k));
//                    break;
//                case 9 :
//                    temp10.put(k, data.get(k));
//                    break;
//                case 10 :
//                    temp11.put(k, data.get(k));
//                    break;
//                case 11 :
//                    temp12.put(k, data.get(k));
//                    break;
//
//                default:
//
//                    break;
//            }
//        });

//        List<Map<Integer,List<MyCell> >> datas = new ArrayList<>(12);
////        datas.add(temp1);
////        datas.add(temp2);
////        datas.add(temp3);
////        datas.add(temp4);
////        datas.add(temp5);
////        datas.add(temp6);
////        datas.add(temp7);
////        datas.add(temp8);
////        datas.add(temp9);
////        datas.add(temp10);
////        datas.add(temp11);
////        datas.add(temp12);
        investmentInstitutionService.save(0,data);
//        ExecutorService executorService = Executors.newFixedThreadPool(12);
//        for(int i = 0; i< 12; i++){
//            final int taskID = i;
//            executorService.execute(new Runnable() {
//                public void run() {
//                    projectsService.saveProject(taskID,datas.get(taskID));
//                }
//            });
//        }
//        executorService.shutdown();

//        model.addAttribute("message", "File: " + file.getOriginalFilename()
//                + " has been uploaded successfully!");
        return "excel";
    }
}
