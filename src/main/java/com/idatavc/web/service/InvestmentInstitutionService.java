package com.idatavc.web.service;

import com.idatavc.web.MyCell;

import java.util.List;
import java.util.Map;

public interface InvestmentInstitutionService {
    void save(int i, Map<Integer, List<MyCell>> data);

    void compare(Map<Integer, List<MyCell>> data);

    void member(Map<Integer, List<MyCell>> data);

    void updateLogo(Map<Integer, List<MyCell>> data);

    void handlerData(Map<Integer, List<MyCell>> data);
}
