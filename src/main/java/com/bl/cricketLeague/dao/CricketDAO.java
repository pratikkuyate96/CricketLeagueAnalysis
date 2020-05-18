package com.bl.cricketLeague.dao;

import com.bl.cricketLeague.model.BatsManCSVFile;
import com.bl.cricketLeague.model.BowlerCSVFile;

public class CricketDAO {

    public String player;
    public double average;
    public double strikeRate;
    public double six;
    public double fours;
    public double ballsFaced;
    public int runs;

    public CricketDAO(BatsManCSVFile cricketDAO) {
        player = cricketDAO.player;
        average = cricketDAO.average;
        strikeRate = cricketDAO.strikeRate;
        six = cricketDAO.sixs;
        fours = cricketDAO.fours;
        ballsFaced = cricketDAO.ballsFaced;
        runs = cricketDAO.runs;
    }

    public CricketDAO(BowlerCSVFile bowlerCSVFile) {
        player = bowlerCSVFile.player;
        average = bowlerCSVFile.average;
    }
}