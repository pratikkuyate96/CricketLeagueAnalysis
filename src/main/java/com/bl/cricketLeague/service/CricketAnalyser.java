package com.bl.cricketLeague.service;

import com.bl.cricketLeague.csvparser.CSVBuilderFactory;
import com.bl.cricketLeague.dao.CricketDAO;
import com.bl.cricketLeague.csvparser.ICSVBuilder;
import com.bl.cricketLeague.exception.CricketAnalyserException;
import com.bl.cricketLeague.model.BatsManCSVFile;
import com.google.gson.Gson;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CricketAnalyser {
        Map<SortField, Comparator<BatsManCSVFile>> sortMap;
        Map<String, CricketDAO> daoMap;
        List<BatsManCSVFile> daoList;

    public CricketAnalyser() {
        this.daoMap = new HashMap<>();
        this.sortMap = new HashMap<>();
        this.sortMap.put(SortField.AVG, Comparator.comparing(cricketDAO -> cricketDAO.average));
        this.sortMap.put(SortField.STRIKING_RATES, Comparator.comparing(cricketDAO -> cricketDAO.strikeRate));
        this.sortMap.put(SortField.SIX_FOURS, Comparator.comparing(cricketDAO -> cricketDAO.sixs + cricketDAO.fours));
        this.sortMap.put(SortField.SIX_FOURS, Comparator.comparing(cricketDAO -> cricketDAO.sixs + cricketDAO.fours / cricketDAO.ballsFaced * 100));
        this.sortMap.put(SortField.AVG_SR, Comparator.comparing(cricketDAO -> cricketDAO.average * cricketDAO.strikeRate / 100));
    }

        public int loadCricketData (String csvFilePath){
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<BatsManCSVFile> csvIterator = csvBuilder.getCSVFileIterator(reader, BatsManCSVFile.class);
            Iterable<BatsManCSVFile> csvIterable = () -> csvIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .forEach(cricketDAO -> daoMap.put(cricketDAO.player, new CricketDAO(cricketDAO)));
            return daoMap.size();
        } catch (IOException e) {
            throw new CricketAnalyserException("No such file", CricketAnalyserException.ExceptionType.NO_CRICKET_DATA);
        } catch (RuntimeException e) {
            throw new CricketAnalyserException("No Census data available", CricketAnalyserException.ExceptionType.NO_CRICKET_DATA);
        }
    }

        public String getFieldWiseData (SortField sortField) {
        if (daoMap == null || daoMap.size() == 0) {
            throw new CricketAnalyserException("No Census data available", CricketAnalyserException.ExceptionType.NO_CRICKET_DATA);
        }
        daoList.stream().sorted(this.sortMap.get(sortField).reversed()).collect(Collectors.toList());
        String sortedStateCensusJson = new Gson().toJson(daoList);
        return sortedStateCensusJson;
    }
}