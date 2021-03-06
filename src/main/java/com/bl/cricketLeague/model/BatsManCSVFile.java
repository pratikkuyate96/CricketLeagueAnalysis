package com.bl.cricketLeague.model;

import com.opencsv.bean.CsvBindByName;

public class BatsManCSVFile {
    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    @CsvBindByName(column = "SR", required = true)
    public double strikeRate;

    @CsvBindByName(column = "6s", required = true)
    public double sixs;

    @CsvBindByName(column = "4s", required = true)
    public double fours;

    @CsvBindByName(column = "BF", required = true)
    public double ballsFaced;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    public BatsManCSVFile(String player,double battingaverage,double strikeRate,double fours,double ballsFaced,int runs,double sixs) {
        this.player = player;
        this.average = battingaverage;
        this.strikeRate = strikeRate;
        this.sixs = sixs;
        this.fours = fours;
        this.ballsFaced=ballsFaced;
        this.runs=runs;
    }

    @Override
    public String toString() {
        return "BatsManCSVFile{" +
                "player='" + player + '\'' +
                ", average=" + average +
                ", strikeRate=" + strikeRate +
                ", sixs=" + sixs +
                ", fours=" + fours +
                '}';
    }

    public BatsManCSVFile(String player, double average, double strikeRate, double sixs,double fours,double ballsFaced,int runs) {
        this.player = player;
        this.average = average;
        this.strikeRate = strikeRate;
        this.sixs = sixs;
        this.fours = fours;
        this.ballsFaced = ballsFaced;
        this.runs = runs;
    }

    public BatsManCSVFile(String player, double battingaverage, double strikeRate, double sixs, double fours, int runs){

    }
}