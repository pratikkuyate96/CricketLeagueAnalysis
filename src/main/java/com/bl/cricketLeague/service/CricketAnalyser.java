package com.bl.cricketLeague.service;

import com.bl.cricketLeague.adapter.IPLAdapter;
import com.bl.cricketLeague.adapter.IPLAdapterFactory;
import com.bl.cricketLeague.dao.CricketDAO;
import com.bl.cricketLeague.exception.CricketAnalyserException;
import com.bl.cricketLeague.model.BatsManCSVFile;
import com.bl.cricketLeague.model.BowlerCSVFile;
import com.google.gson.Gson;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import java.util.*;

public class CricketAnalyser {

        public enum BatsOrBall {BATTING, BALLING}

        Map<SortField, Comparator<BatsManCSVFile>> sortMap;
        Map<String, CricketDAO> daoMap;
        List<BatsManCSVFile> daoList;

    public CricketAnalyser() {
        this.daoMap = new HashMap<>();
        this.sortMap = new HashMap<>();
        this.sortMap.put(SortField.AVG, Comparator.comparing(cricketDAO -> cricketDAO.average));
        this.sortMap.put(SortField.STRIKING_RATES, Comparator.comparing(cricketDAO -> cricketDAO.strikeRate));
        this.sortMap.put(SortField.SIX_FOURS, Comparator.comparing(cricketDAO -> cricketDAO.sixs + cricketDAO.fours));
        this.sortMap.put(SortField.SIX_FOURS, Comparator.comparing(cricketDAO -> cricketDAO.sixs + cricketDAO.fours / cricketDAO.ballsFaced * 100));
        this.sortMap.put(SortField.AVG_SR, Comparator.comparing(cricketDAO -> cricketDAO.average * cricketDAO.strikeRate / 100));
        this.sortMap.put(SortField.AVG_SR, Comparator.comparing(cricketDAO -> cricketDAO.runs));
    }

    public int loadIPLBatsmenData(BatsOrBall batsOrBall, String csvFilePath) {
        daoMap = IPLAdapterFactory.getIPLAdapter(batsOrBall, csvFilePath);
        return daoMap.size();
    }

    public int loadIPLBowlerData(BatsOrBall batsOrBall, String csvFilePath) {
        daoMap = IPLAdapter.loadCricketData(BowlerCSVFile.class, csvFilePath);
        return daoMap.size();
    }

        public String getFieldWiseData (SortField sortField) {
        daoList = daoMap.values().stream().collect(Collectors.toList());
        if (daoMap == null || daoMap.size() == 0) {
            throw new CricketAnalyserException("No Census data available", CricketAnalyserException.ExceptionType.NO_CRICKET_DATA);
        }
        daoList.stream().sorted(this.sortMap.get(sortField).reversed()).collect(Collectors.toList());
        String sortedStateCensusJson = new Gson().toJson(daoList);
        return sortedStateCensusJson;
    }
}