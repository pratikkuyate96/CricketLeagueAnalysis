package com.bl.cricketLeague.service;

import com.bl.cricketLeague.dao.CricketDAO;

public class GetAllRounder implements java.util.Comparator<com.bl.cricketLeague.dao.CricketDAO>{

    @Override
    public int compare(CricketDAO p1, CricketDAO p2) {
        int total = (int) ((p1.runs * p1.wicket) - (p2.runs * p2.wicket));
        return total;
    }
}
