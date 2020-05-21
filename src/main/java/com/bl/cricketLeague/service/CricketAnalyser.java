package com.bl.cricketLeague.service;

import com.bl.cricketLeague.adapter.IPLAdapter;
import com.bl.cricketLeague.adapter.IPLAdapterFactory;
import com.bl.cricketLeague.dao.CricketDAO;
import com.bl.cricketLeague.exception.CricketAnalyserException;
import com.bl.cricketLeague.model.BowlerCSVFile;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;


public class CricketAnalyser {

    public enum BatsOrBall {
        BATTING, BALLING
    }
        Map<String, CricketDAO> daoMap = null;
        List<CricketDAO> daoList = null;
        public BatsOrBall batsOrBall;

    public CricketAnalyser(BatsOrBall batsOrBall) {
        SortField.sortField();
        this.batsOrBall = batsOrBall;
    }

    public int loadCricketData(BatsOrBall batsOrBall, String... csvFilePath) {
        daoMap = new IPLAdapterFactory().getIPLAdapter(batsOrBall, csvFilePath);
        return daoMap.size();
    }

    public int loadIPLBatsmenData(String csvFilePath) {
        daoMap = IPLAdapterFactory.getIPLAdapter(batsOrBall, csvFilePath);
        return daoMap.size();
    }

    public int loadIPLBowlerData(BatsOrBall batsOrBall, String csvFilePath) {
        daoMap = IPLAdapter.loadCricketData(BowlerCSVFile.class, csvFilePath);
        return daoMap.size();
    }

    public String getFieldWiseData (SortField sortField) {
            daoList = daoMap.values().stream().collect(Collectors.toList());

            if (daoList == null || daoList.size() == 0)
                throw new CricketAnalyserException("No Census data available", CricketAnalyserException.ExceptionType.NO_CRICKET_DATA);
            List list = this.sort(sortField.sortMap.get(sortField).reversed());

            String sortedStateCensusJson = new Gson().toJson(daoList);
            return sortedStateCensusJson;
    }

    private ArrayList sort(Comparator<CricketDAO> comparator) {
        return (ArrayList) daoList.stream().sorted(comparator).map(cricketDAO -> cricketDAO.getCricketDTO(batsOrBall)).collect(Collectors.toList());

    }
}
