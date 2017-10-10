package com.idatavc.web.service;

import com.idatavc.web.MyCell;

import java.util.List;
import java.util.Map;

public interface ProjectsService {
    void saveProject(Map<Integer, List<MyCell>> data);
}
