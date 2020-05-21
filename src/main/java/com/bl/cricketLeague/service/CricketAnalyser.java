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

        Map<SortField, Comparator<CricketDAO>> sortMap;
        Map<String, CricketDAO> daoMap;
        List<CricketDAO> daoList = null;

    public CricketAnalyser() {
        this.daoMap = new HashMap<>();
        this.sortMap = new HashMap<>();
        this.sortMap.put(SortField.AVG, Comparator.comparing(cricketDAO -> cricketDAO.average));
        this.sortMap.put(SortField.STRIKING_RATES, Comparator.comparing(cricketDAO -> cricketDAO.strikeRate));
        this.sortMap.put(SortField.SIX_FOURS, Comparator.comparing(cricketDAO -> cricketDAO.sixs + cricketDAO.fours));
        this.sortMap.put(SortField.SIX_FOURS, Comparator.comparing(cricketDAO -> cricketDAO.sixs + cricketDAO.fours / cricketDAO.ballsFaced * 100));
        this.sortMap.put(SortField.AVG_SR, Comparator.comparing(cricketDAO -> cricketDAO.average * cricketDAO.strikeRate / 100));
        this.sortMap.put(SortField.AVG_SR, Comparator.comparing(cricketDAO -> cricketDAO.runs));
        this.sortMap.put(SortField.ECONOMY, Comparator.comparing(cricketDAO -> cricketDAO.economy));
        Comparator<CricketDAO> maxWicketsAndStrikeRate = Comparator.comparing(iplData -> iplData.fourWicket + iplData.fiveWicket);
        this.sortMap.put(SortField.WICKETS_AND_STRIKERATE, maxWicketsAndStrikeRate.thenComparing(iplData -> iplData.strikeRate));
        Comparator<CricketDAO> maxWicketsAndStrikeRates = Comparator.comparing(cricketDAO -> cricketDAO.wicket);
        this.sortMap.put(SortField.WICKET_AND_AVG, maxWicketsAndStrikeRates.thenComparing(cricketDAO -> cricketDAO.strikeRate));

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
            if (daoList == null || daoList.size() == 0)
                throw new CricketAnalyserException("No Census data available", CricketAnalyserException.ExceptionType.NO_CRICKET_DATA);
            daoList.stream().sorted(this.sortMap.get(sortField).reversed()).collect(Collectors.toList());
            String sortedStateCensusJson = new Gson().toJson(daoList);
            return sortedStateCensusJson;
    }

}
