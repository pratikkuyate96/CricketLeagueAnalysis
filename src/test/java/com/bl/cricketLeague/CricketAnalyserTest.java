package com.bl.cricketLeague;

import com.bl.cricketLeague.DAO.exception.CricketAnalyserException;
import com.bl.cricketLeague.DAO.model.BatsManCSVFile;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.junit.Assert;
import org.junit.Test;
import com.bl.cricketLeague.DAO.service.CricketAnalyser;

public class CricketAnalyserTest {

    String BATSMEN_DATA = "./src/test/resources/IPL2019FactsheetMostRuns.csv";

    @Test
    public void givenWhenCricketData_ShouldReturn_TopBattingAverage() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData( BATSMEN_DATA );
            String sortedCensusData = cricketAnalyser.getFieldWiseData( "average" );
            BatsManCSVFile[] censusCSV = new Gson().fromJson( sortedCensusData, BatsManCSVFile[].class );
            Assert.assertEquals( "MS Dhoni", censusCSV[0].player );
        } catch (CricketAnalyserException | JsonSyntaxException e) {
        }
    }
}
