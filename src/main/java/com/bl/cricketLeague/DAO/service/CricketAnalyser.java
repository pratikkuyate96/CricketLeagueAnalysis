package com.bl.cricketLeague.DAO.service;

import com.bl.cricketLeague.DAO.CricketDAO;
import com.bl.cricketLeague.DAO.exception.CricketAnalyserException;
import com.bl.cricketLeague.DAO.model.BatsManCSVFile;
import com.bl.cricketLeague.DAO.model.CSVBuilderFactory;
import com.bl.cricketLeague.DAO.model.ICSVBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CricketAnalyser {

    List<CricketDAO> cricketList;
    Map<String, CricketDAO> cricketMap;

    public CricketAnalyser() {
        this.cricketList = new ArrayList<>();
        this.cricketMap = new HashMap<>();
    }

    public int loadCricketData(String csvFilePath) throws CricketAnalyserException {
        try {
            Reader reader = Files.newBufferedReader( Paths.get( csvFilePath ) );
            ICSVBuilder csvBuilderInterface = CSVBuilderFactory.createCSVBuilder();
            List<BatsManCSVFile> csvFileList = csvBuilderInterface.getCSVFileList
                    ( reader, BatsManCSVFile.class );
            csvFileList.forEach( list -> cricketMap.put( list.player, new CricketDAO( list ) ) );
            return cricketMap.size();
        } catch (RuntimeException e) {
            throw new CricketAnalyserException( e.getMessage(),
                    CricketAnalyserException.ExceptionType.WRONG_DATA );
        } catch (IOException e) {
            throw new CricketAnalyserException( e.getMessage(),
                    CricketAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM );
        }
    }

    public String getFieldWiseData(String fieldType) throws CricketAnalyserException {
        if (cricketMap == null || cricketMap.size() == 0) {
            throw new CricketAnalyserException( "No Cricket Data",
                    CricketAnalyserException.ExceptionType.NO_CRICKET_DATA );
        }
        cricketList = new ArrayList( cricketMap.values() );
        Comparator<CricketDAO> cricketComparator = new CricketFactory().getCurrentSort( fieldType );
        List<CricketDAO> sortedList = cricketList.stream()
                .sorted( cricketComparator.reversed() )
                .collect( Collectors.toList() );
        return new Gson().toJson( sortedList );
    }
}