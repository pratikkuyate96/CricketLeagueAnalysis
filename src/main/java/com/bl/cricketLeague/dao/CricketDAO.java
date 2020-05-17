package com.bl.cricketLeague.dao;

import com.bl.cricketLeague.model.BatsManCSVFile;

public class CricketDAO {

    public String player;
    public double average;
    public double strikeRate;
    public double six;
    public double fours;
    public double ballsFaced;
    public int runs;

    public CricketDAO(String player, double average, double strikeRate, double six, double fours,double ballsFaced,int runs) {
        this.player = player;
        this.average = average;
        this.strikeRate = strikeRate;
        this.six = six;
        this.fours = fours;
        this.ballsFaced = ballsFaced;
        this.runs=runs;
    }

    public CricketDAO(BatsManCSVFile cricketDAO) {

    }
}