package com.bl.cricketLeague.model;

import com.opencsv.bean.CsvBindByName;

public class BowlerCSVFile {

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    @CsvBindByName(column = "Wkts", required = true)
    public double wicket;

    @CsvBindByName(column = "SR", required = true)
    public double strikeRate;

//    public BowlerCSVFile(String player, double average) {
//        this.player = player;
//        this.average = average;
//    }
//
//    public BowlerCSVFile() {
//    }
}
