package com.bl.cricketLeague.DAO.model;

import com.bl.cricketLeague.DAO.exception.CricketAnalyserException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class OpenCSVBuilder<E> implements ICSVBuilder {

    @Override
    public List<E> getCSVFileList(Reader reader, Class csvClass) throws CricketAnalyserException {
        return this.getCsvToBean(reader, csvClass).parse();
    }

    @Override
    public Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CricketAnalyserException {
        return this.getCsvToBean(reader, csvClass).iterator();
    }

    private CsvToBean getCsvToBean(Reader reader, Class csvClass) throws CricketAnalyserException {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            return csvToBeanBuilder.build();
        } catch (IllegalStateException e) {
            throw new CricketAnalyserException(e.getMessage(), CricketAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }
    }
}