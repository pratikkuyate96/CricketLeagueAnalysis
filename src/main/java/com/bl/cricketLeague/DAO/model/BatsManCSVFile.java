package com.bl.cricketLeague.DAO.model;

import com.opencsv.bean.CsvBindByName;

public class BatsManCSVFile {
    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    @CsvBindByName(column = "SR", required = true)
    public double strikeRate;



    public BatsManCSVFile(String player, double average,double strikeRate) {
        this.player = player;
        this.average = average;
        this.strikeRate = strikeRate;
    }

    @Override
    public String toString() {
        return "BatsManCSVFile{" +
                "player='" + player + '\'' +
                ", average=" + average +
                '}';
    }

}
