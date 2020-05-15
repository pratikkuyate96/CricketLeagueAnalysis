package com.bl.cricketLeague.DAO;

import com.bl.cricketLeague.DAO.model.BatsManCSVFile;

public class CricketDAO {

    public String player;
    public double average;

    public CricketDAO(BatsManCSVFile playerObj) {

        this.average = playerObj.average;
        this.player = playerObj.player;

    }
    
}