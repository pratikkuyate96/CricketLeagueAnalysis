package com.bl.cricketLeague.dao;

import com.bl.cricketLeague.model.BatsManCSVFile;
import com.bl.cricketLeague.model.BowlerCSVFile;

public class CricketDAO {

    public String player;
//    public double average;
    public double strikeRate;
    public double sixs;
    public double fours;
    public double ballsFaced;
    public int runs;
    public double economy;
    public double fiveWicket;
    public double fourWicket;
    public double wicket;
    public double battingaverage;
    public double ballingAvg;

    public CricketDAO(BatsManCSVFile batsManCSVFile) {
        player = batsManCSVFile.player;
        battingaverage = batsManCSVFile.average;
        strikeRate = batsManCSVFile.strikeRate;
        sixs = batsManCSVFile.sixs;
        fours = batsManCSVFile.fours;
        ballsFaced = batsManCSVFile.ballsFaced;
        runs = batsManCSVFile.runs;
    }

    public CricketDAO(BowlerCSVFile bowlerCSVFile) {
        player = bowlerCSVFile.player;
        ballingAvg = bowlerCSVFile.average;
        strikeRate = bowlerCSVFile.strikeRate;
        economy = bowlerCSVFile.economy;
        fiveWicket=bowlerCSVFile.fiveWicket;
        fourWicket=bowlerCSVFile.fourWicket;
        wicket = bowlerCSVFile.wicket;

    }

}