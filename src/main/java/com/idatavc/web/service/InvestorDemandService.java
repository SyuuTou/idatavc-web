package com.idatavc.web.service;

import com.idatavc.web.MyCell;

import java.util.List;
import java.util.Map;

/**
 * Created by lanhaijulang on 2018/2/4.
 */
public interface InvestorDemandService {

    void demand(Map<Integer, List<MyCell>> data);

}
