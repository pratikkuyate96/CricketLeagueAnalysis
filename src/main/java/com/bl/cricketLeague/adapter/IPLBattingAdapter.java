package com.bl.cricketLeague.adapter;

import censusanalyser.model.CSVBuilderFactory;
import censusanalyser.model.ICSVBuilder;
import com.bl.cricketLeague.dao.CricketDAO;
import com.bl.cricketLeague.exception.CricketAnalyserException;
import com.bl.cricketLeague.model.BatsManCSVFile;
import com.bl.cricketLeague.model.BowlerCSVFile;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IPLBattingAdapter  extends IPLAdapter {

    @Override
    public Map<String, CricketDAO> loadCricketData(String... csvFilePath) {
        Map<String, CricketDAO> map = super.loadCricketData(BatsManCSVFile.class, csvFilePath[0]);
        if (csvFilePath.length > 1)
            this.loadCricketData(map, csvFilePath[1]);
        return map;
    }

    private Map<String, CricketDAO> loadCricketData(Map<String, CricketDAO> daoMap, String csvFilePath) {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<BowlerCSVFile> csvIterator = csvBuilder.getCSVFileIterator(reader, BowlerCSVFile.class);
            Iterable<BowlerCSVFile> csvIterable = () -> csvIterator;
            StreamSupport.stream(csvIterable.spliterator(), false).filter(csvFile -> daoMap.get(csvFile.player) != null)
                    .forEach(csvFile -> {
                        daoMap.get(csvFile.player).ballingAvg = csvFile.average;
                        daoMap.get(csvFile.player).average = csvFile.wicket;
                    });
            return daoMap;
        } catch (IOException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.CSV_FILE_PROBLEM);
        }
    }

}
