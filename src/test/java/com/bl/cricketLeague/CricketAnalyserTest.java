package com.bl.cricketLeague;

import com.bl.cricketLeague.exception.CricketAnalyserException;
import com.bl.cricketLeague.model.BatsManCSVFile;
import com.bl.cricketLeague.service.SortField;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.bl.cricketLeague.service.CricketAnalyser;

public class CricketAnalyserTest {

    String BATSMEN_DATA = "./src/test/resources/IPL2019FactsheetMostRuns.csv";

    CricketAnalyser cricketAnalyser;

    @Before
    public void setUp() throws Exception {
        cricketAnalyser = new CricketAnalyser();
    }

    @Test
    public void givenWhenCricketData_ShouldReturn_TopBattingAverage() {
        try {
            cricketAnalyser.loadCricketData( BATSMEN_DATA );
            String sortedData = cricketAnalyser.getFieldWiseData(SortField.AVG );
            BatsManCSVFile[] censusCSV = new Gson().fromJson( sortedData, BatsManCSVFile[].class );
            Assert.assertEquals( "MS Dhoni", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenWhenCricketData_ShouldReturn_TopStrikeRate() {
        try {
            cricketAnalyser.loadCricketData( BATSMEN_DATA );
            String sortedCensusData = cricketAnalyser.getFieldWiseData(SortField.STRIKING_RATES );
            BatsManCSVFile[] censusCSV = new Gson().fromJson( sortedCensusData, BatsManCSVFile[].class );
            Assert.assertEquals( "Ishant Sharma", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLData_WhenGivenSixAndFours_ShouldReturnCorrectRecord() {
        try {
            cricketAnalyser.loadCricketData( BATSMEN_DATA );
            String sortedCensusData = cricketAnalyser.getFieldWiseData(SortField.SIX_FOURS);
            BatsManCSVFile[] censusCSV = new Gson().fromJson( sortedCensusData, BatsManCSVFile[].class );
            Assert.assertEquals( "Andre Russell", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLData_WhenGivenSixAndFours_ShouldReturnPlayerWithHighestStrikingRates() {
        try {
            cricketAnalyser.loadCricketData( BATSMEN_DATA );
            String sortedCensusData = cricketAnalyser.getFieldWiseData(SortField.SIX_FOURS);
            BatsManCSVFile[] censusCSV = new Gson().fromJson( sortedCensusData, BatsManCSVFile[].class );
            Assert.assertEquals( "David Warner", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

}
