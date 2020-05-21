package com.bl.cricketLeague.service;

import com.bl.cricketLeague.dao.CricketDAO;

public class GetAverage implements java.util.Comparator<com.bl.cricketLeague.dao.CricketDAO>  {

    @Override
    public int compare(CricketDAO p1, CricketDAO p2) {
        int avg = (int) ((p1.battingaverage + p1.ballingAvg) - (p2.battingaverage + p2.ballingAvg));
        return avg;
    }
}
