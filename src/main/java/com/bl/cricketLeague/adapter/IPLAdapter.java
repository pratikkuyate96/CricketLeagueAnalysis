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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;
import java.nio.file.Paths;

public abstract class IPLAdapter {

    public abstract Map<String, CricketDAO> loadCricketData (String... csvFilePath);

    public static <E> Map<String, CricketDAO> loadCricketData (Class<E> iplClass, String csvFilePath) {
        Map<String, CricketDAO> daoMap = new HashMap<>();

        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> csvIterator = icsvBuilder.getCSVFileIterator(reader, iplClass);
            Iterable<E> csvIterable = () -> csvIterator;

            if (iplClass.getName() == "com.bl.cricketLeague.model.BatsManCSVFile") {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(BatsManCSVFile.class::cast).
                        forEach(csvFile -> daoMap.put(csvFile.player, new CricketDAO(csvFile)));
            } else if (iplClass.getName() == "com.bl.cricketLeague.model.BowlerCSVFile") {
                StreamSupport.stream(csvIterable.spliterator(), false).
                        map(BowlerCSVFile.class::cast).
                        forEach(csvFile -> daoMap.put(csvFile.player, new CricketDAO(csvFile)));
            }
            return daoMap;

        } catch (IOException e) {
            throw new CricketAnalyserException(e.getMessage(), CricketAnalyserException.ExceptionType.CSV_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CricketAnalyserException(e.getMessage(), CricketAnalyserException.ExceptionType.CSV_FILE_PROBLEM);
        }
    }

}