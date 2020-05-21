package com.bl.cricketLeague.adapter;

import com.bl.cricketLeague.dao.CricketDAO;
import com.bl.cricketLeague.model.BowlerCSVFile;

import java.util.Map;

public class IPLBowlerAdapter extends IPLAdapter {

    @Override
    public Map<String, CricketDAO> loadCricketData (String... csvFilePath) {
        return super.loadCricketData (BowlerCSVFile.class, csvFilePath[0]);
    }
}
