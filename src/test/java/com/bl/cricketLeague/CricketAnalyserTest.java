package com.bl.cricketLeague;

import com.bl.cricketLeague.exception.CricketAnalyserException;
import com.bl.cricketLeague.model.BatsManCSVFile;
import com.bl.cricketLeague.model.BowlerCSVFile;
import com.bl.cricketLeague.service.SortField;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import com.bl.cricketLeague.service.CricketAnalyser;

public class CricketAnalyserTest {

    private static final String IPL_MOST_RUNS_FILE_PATH = "src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_MOST_BALLS_FILE_PATH = "src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Test
    public void givenWhenCricketData_ShouldReturn_TopBattingAverage() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.BatsOrBall.BATTING);
            cricketAnalyser.loadIPLBatsmenData(IPL_MOST_RUNS_FILE_PATH);
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
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.BatsOrBall.BATTING);
            cricketAnalyser.loadIPLBatsmenData(IPL_MOST_RUNS_FILE_PATH);
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
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.BatsOrBall.BATTING);
            cricketAnalyser.loadIPLBatsmenData(IPL_MOST_RUNS_FILE_PATH);
            String sortedCensusData = cricketAnalyser.getFieldWiseData(SortField.SIX_FOURS_STRIKE);
            BatsManCSVFile[] censusCSV = new Gson().fromJson( sortedCensusData, BatsManCSVFile[].class );
            Assert.assertEquals( "Andre Russell", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLData_WhenGivenSixAndFours_ShouldReturnPlayerWithHighestStrikingRates() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.BatsOrBall.BATTING);
            cricketAnalyser.loadIPLBatsmenData(IPL_MOST_RUNS_FILE_PATH);
            String sortedCensusData = cricketAnalyser.getFieldWiseData(SortField.SIX_FOURS_STRIKE);
            BatsManCSVFile[] censusCSV = new Gson().fromJson( sortedCensusData, BatsManCSVFile[].class );
            Assert.assertEquals( "Andre Russell", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLData_WhenGivenAvg_StrikingRates_ShouldReturnPlayerWithHighestStrikingRates() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.BatsOrBall.BATTING);
            cricketAnalyser.loadIPLBatsmenData(IPL_MOST_RUNS_FILE_PATH);
            String sortedCensusData = cricketAnalyser.getFieldWiseData(SortField.AVG_SR);
            BatsManCSVFile[] censusCSV = new Gson().fromJson( sortedCensusData, BatsManCSVFile[].class );
            Assert.assertEquals( "David Warner", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void  givenIPLData_WhenGivenAvgRuns_ShouldReturnPlayerWithHighestStrikingRates() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.BatsOrBall.BATTING);
            cricketAnalyser.loadIPLBatsmenData(IPL_MOST_RUNS_FILE_PATH);
            String sortedCensusData = cricketAnalyser.getFieldWiseData(SortField.AVG_SR_RUNS);
            BatsManCSVFile[] censusCSV = new Gson().fromJson( sortedCensusData, BatsManCSVFile[].class );
            Assert.assertEquals( "David Warner", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void  givenIPLData_WhenGivenBowlingAverage_ShouldReturnCorrectRecord() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.BatsOrBall.BALLING);
            cricketAnalyser.loadIPLBatsmenData(IPL_MOST_BALLS_FILE_PATH);
            String sortedCensusData = cricketAnalyser.getFieldWiseData(SortField.AVG);
            BowlerCSVFile[] censusCSV = new Gson().fromJson( sortedCensusData, BowlerCSVFile[].class );
            Assert.assertEquals( "Anukul Roy", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLData_WhenGivenBowlingStrikingRates_ShouldReturnCorrectRecord() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.BatsOrBall.BALLING);
            cricketAnalyser.loadIPLBatsmenData(IPL_MOST_BALLS_FILE_PATH);
            String sortedCensusData = cricketAnalyser.getFieldWiseData(SortField.STRIKING_RATES);
            BowlerCSVFile[] censusCSV = new Gson().fromJson( sortedCensusData, BowlerCSVFile[].class );
            Assert.assertEquals( "Khaleel Ahmed", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLData_WhenGivenBestEconomyRates_ShouldReturnCorrectRecord() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.BatsOrBall.BALLING);
            cricketAnalyser.loadIPLBatsmenData(IPL_MOST_BALLS_FILE_PATH);
            String sortedCensusData = cricketAnalyser.getFieldWiseData(SortField.ECONOMY);
            BowlerCSVFile[] censusCSV = new Gson().fromJson( sortedCensusData, BowlerCSVFile[].class );
            Assert.assertEquals( "Shivam Dube", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void  givenIPLData_WhenGivenStrikingRatesWith4sand5s_ShouldReturnCorrectRecord() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.BatsOrBall.BALLING);
            cricketAnalyser.loadIPLBatsmenData(IPL_MOST_BALLS_FILE_PATH);
            String sortedCensusData = cricketAnalyser.getFieldWiseData(SortField.WICKETS_AND_STRIKERATE);
            BowlerCSVFile[] censusCSV = new Gson().fromJson( sortedCensusData, BowlerCSVFile[].class );
            Assert.assertEquals( "Kagiso Rabada", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void  givenIPLData_WhenGivenStrikingRatesWithAvg_ShouldReturnCorrectRecord() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.BatsOrBall.BALLING);
            cricketAnalyser.loadIPLBatsmenData(IPL_MOST_BALLS_FILE_PATH);
            String sortedCensusData = cricketAnalyser.getFieldWiseData(SortField.AVG_SR);
            BowlerCSVFile[] censusCSV = new Gson().fromJson( sortedCensusData, BowlerCSVFile[].class );
            Assert.assertEquals( "Anukul Roy", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void  givenIPLData_WhenGivenWicketsWithAvg_ShouldReturnCorrectRecord() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.BatsOrBall.BALLING);
            cricketAnalyser.loadIPLBatsmenData(IPL_MOST_BALLS_FILE_PATH);
            String sortedCensusData = cricketAnalyser.getFieldWiseData(SortField.WICKET_AND_AVG);
            BowlerCSVFile[] censusCSV = new Gson().fromJson( sortedCensusData, BowlerCSVFile[].class );
            Assert.assertEquals( " Imran Tahir", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLData_WhenGivenBestAvgOfBatsmanAndBowler_ShouldReturnCorrectPlayer() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.BatsOrBall.BALLING);
            cricketAnalyser.loadIPLBatsmenData(IPL_MOST_BALLS_FILE_PATH);
            String sortedCensusData = cricketAnalyser.getFieldWiseData(SortField.BEST_BATTING_BOWLING_AVERAGE);
            BowlerCSVFile[] censusCSV = new Gson().fromJson( sortedCensusData, BowlerCSVFile[].class );
            Assert.assertEquals( "Andre Russell", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void  givenIplData_WhenGivenRunsAndWickets_ShouldReturnCorrectPlayer() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.BatsOrBall.BALLING);
            cricketAnalyser.loadCricketData(CricketAnalyser.BatsOrBall.BALLING, IPL_MOST_RUNS_FILE_PATH, IPL_MOST_BALLS_FILE_PATH);
            cricketAnalyser.loadIPLBatsmenData(IPL_MOST_BALLS_FILE_PATH);
            String sortedCensusData = cricketAnalyser.getFieldWiseData(SortField.BEST_BATTING_BOWLING_AVERAGE);
            BowlerCSVFile[] censusCSV = new Gson().fromJson( sortedCensusData, BowlerCSVFile[].class );
            Assert.assertEquals( "Andre Russell", censusCSV[censusCSV.length-1].player );
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

}