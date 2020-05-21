package com.bl.cricketLeague.service;

import com.bl.cricketLeague.dao.CricketDAO;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public enum SortField {
    AVG, STRIKING_RATES,SIX_FOURS,AVG_SR, AVG_RUNS;
    static Map<SortField, Comparator<CricketDAO>> sortMap;

    public static void sortField() {
        sortMap = new HashMap<>();
        Comparator<CricketDAO> avgComparator =
                Comparator.comparing(iplBatsmanDAO -> iplBatsmanDAO.strikeRate, Comparator.reverseOrder());
        sortMap.put(AVG, avgComparator);
        Comparator<CricketDAO> strikeRateComparator =
                Comparator.comparing(iplBatsmanDAO -> iplBatsmanDAO.strikeRate, Comparator.reverseOrder());
        sortMap.put(SortField.STRIKING_RATES, strikeRateComparator);
    }

}