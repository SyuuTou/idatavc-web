package com.idatavc.web.service;

import com.idatavc.web.MyCell;

import java.util.List;
import java.util.Map;

public interface ProjectsService {
    void saveProject(Integer taskID, Map<Integer, List<MyCell>> data);

    void resolveProjects(Map<Integer, List<MyCell>> data);

    void resolveCity();

    void handlerProjectShortName(Map<Integer, List<MyCell>> data);
}
