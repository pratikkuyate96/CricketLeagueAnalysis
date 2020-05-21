package com.bl.cricketLeague.adapter;

import com.bl.cricketLeague.dao.CricketDAO;
import com.bl.cricketLeague.exception.CricketAnalyserException;
import com.bl.cricketLeague.service.CricketAnalyser;

import java.util.Map;

public class IPLAdapterFactory {
    public static <E> Map<String, CricketDAO> getIPLAdapter(CricketAnalyser.BatsOrBall batsOrBall, String... csvFilePath) {
        if (batsOrBall.equals(CricketAnalyser.BatsOrBall.BATTING))
            return new IPLBattingAdapter().loadCricketData (csvFilePath);
        if (batsOrBall.equals(CricketAnalyser.BatsOrBall.BALLING))
            return new IPLBowlerAdapter().loadCricketData (csvFilePath);
        else
            throw new CricketAnalyserException("Invalid type!", CricketAnalyserException.ExceptionType.INVALID_TYPE);
    }
}
