package com.bl.cricketLeague.dao;

import com.bl.cricketLeague.model.BatsManCSVFile;
import com.bl.cricketLeague.model.BowlerCSVFile;
import com.bl.cricketLeague.service.CricketAnalyser;

public class CricketDAO {

    public String player;
    public double strikeRate;
    public double sixs;
    public double fours;
    public double ballsFaced;
    public int runs;
    public double economy;
    public double fiveWicket;
    public double fourWicket;
    public double wicket;
    public double average;
    public double battingaverage;
    public double ballingAvg;

    public CricketDAO(BatsManCSVFile batsManCSVFile) {
        player = batsManCSVFile.player;
        average = batsManCSVFile.average;
        battingaverage = batsManCSVFile.average;
        strikeRate = batsManCSVFile.strikeRate;
        sixs = batsManCSVFile.sixs;
        fours = batsManCSVFile.fours;
        ballsFaced = batsManCSVFile.ballsFaced;
        runs = batsManCSVFile.runs;
    }

    public CricketDAO(BowlerCSVFile bowlerCSVFile) {
        player = bowlerCSVFile.player;
        average = bowlerCSVFile.average;
        ballingAvg = bowlerCSVFile.average;
        strikeRate = bowlerCSVFile.strikeRate;
        economy = bowlerCSVFile.economy;
        fiveWicket=bowlerCSVFile.fiveWicket;
        fourWicket=bowlerCSVFile.fourWicket;
        wicket = bowlerCSVFile.wicket;

    }
    public Object getCricketDTO(CricketAnalyser.BatsOrBall batsOrBall) {
        if (batsOrBall.equals(CricketAnalyser.BatsOrBall.BATTING)) {
            return new BatsManCSVFile(player, battingaverage, strikeRate, sixs, fours, runs);
        } else {
            return new BowlerCSVFile(player, ballingAvg, strikeRate, economy, fiveWicket, fourWicket, wicket);
        }
    }

}