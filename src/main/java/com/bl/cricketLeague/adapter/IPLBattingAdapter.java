package com.bl.cricketLeague.adapter;

import com.bl.cricketLeague.dao.CricketDAO;
import com.bl.cricketLeague.model.BatsManCSVFile;

import java.util.Map;

public class IPLBattingAdapter  extends IPLAdapter {

    @Override
    public Map<String, CricketDAO> loadCricketData (String csvFilePath) {
        return super.loadCricketData (BatsManCSVFile.class, csvFilePath);
    }
}
