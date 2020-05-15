package com.bl.cricketLeague.DAO.model;

import com.opencsv.bean.CsvBindByName;

public class BatsManCSVFile {
    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    public BatsManCSVFile(String player, double average) {
        this.player = player;
        this.average = average;
    }

    @Override
    public String toString() {
        return "BatsManCSVFile{" +
                "player='" + player + '\'' +
                ", average=" + average +
                '}';
    }

}
