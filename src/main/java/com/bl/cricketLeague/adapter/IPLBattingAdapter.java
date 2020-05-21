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

//    @Override
//    public Map<String, CricketDAO> loadCricketData (String csvFilePath) {
//        return super.loadCricketData (BatsManCSVFile.class, csvFilePath);
//    }

    @Override
    public Map<String, CricketDAO> loadCricketData(String... csvFilePath) {
        Map<String, CricketDAO> iplMap = super.loadCricketData(BatsManCSVFile.class, csvFilePath[0]);
        if (csvFilePath.length > 1)
            this.loadCricketData(iplMap, csvFilePath[1]);
        return iplMap;
    }

    private Map<String, CricketDAO> loadCricketData(Map<String, CricketDAO> ipldtoMap, String csvFilePath) {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<BowlerCSVFile> csvIterator = csvBuilder.getCSVFileIterator(reader, BowlerCSVFile.class);
            Iterable<BowlerCSVFile> csvIterable = () -> csvIterator;
            StreamSupport.stream(csvIterable.spliterator(), false).filter(stat -> ipldtoMap.get(stat.player) != null)
                    .forEach(stat -> {
                        ipldtoMap.get(stat.player).average = stat.average;
                        ipldtoMap.get(stat.player).average = stat.wicket;
                    });
            return ipldtoMap;
        } catch (IOException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.CSV_FILE_PROBLEM);
        }
    }

}
